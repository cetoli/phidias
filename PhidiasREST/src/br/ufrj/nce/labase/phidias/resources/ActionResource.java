package br.ufrj.nce.labase.phidias.resources;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.core.UriInfo;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.ufrj.nce.labase.phidias.persistence.EntityManagerHelper;
import br.ufrj.nce.labase.phidias.persistence.dao.ActionDAO;
import br.ufrj.nce.labase.phidias.persistence.dao.ActionMovementDAO;
import br.ufrj.nce.labase.phidias.persistence.dao.ActionTypeDAO;
import br.ufrj.nce.labase.phidias.persistence.dao.GameDAO;
import br.ufrj.nce.labase.phidias.persistence.dao.PatientDAO;
import br.ufrj.nce.labase.phidias.persistence.dao.PieceDAO;
import br.ufrj.nce.labase.phidias.persistence.dao.SessionDAO;
import br.ufrj.nce.labase.phidias.persistence.dao.SessionGamePhaseDAO;
import br.ufrj.nce.labase.phidias.persistence.model.Action;
import br.ufrj.nce.labase.phidias.persistence.model.ActionMovement;
import br.ufrj.nce.labase.phidias.persistence.model.ActionMovementPK;
import br.ufrj.nce.labase.phidias.persistence.model.ActionType;
import br.ufrj.nce.labase.phidias.persistence.model.Game;
import br.ufrj.nce.labase.phidias.persistence.model.Session;
import br.ufrj.nce.labase.phidias.persistence.model.SessionGamePhase;
import br.ufrj.nce.labase.phidias.persistence.model.SessionGamePhaseId;


@Path("/acao")
public class ActionResource extends BaseResource {
	private final int PLAYING_GAME = 1;
	
	@POST
	@Consumes("text/xml")
	@Produces("text/xml")
	public Response createAction(@Context UriInfo uriInfo, InputStream actionData) {
		try {
			DocumentBuilder documentBuilder = DocumentBuilderFactory
					.newInstance().newDocumentBuilder();
			Document document = documentBuilder.parse(actionData);
			document.getDocumentElement().normalize();
				
			Element rootElement = (Element) document.getElementsByTagName("action").item(0);
			NodeList rootNodes = rootElement.getChildNodes();
				
			Integer gameId = Integer.valueOf(getNodeValue(rootNodes, "jogo"));
			String patient = getNodeValue(rootNodes, "jogador");
			Integer phaseId = Integer.valueOf(getNodeValue(rootNodes, "fasejogo"));
			Integer actionTypeId = Integer.valueOf(getNodeValue(rootNodes, "tipoacao"));
			
			NodeList movementNodes = getChildNodes(rootNodes, "movimento");
			Integer movementId = Integer.valueOf(getNodeValue(movementNodes, "id"));
			String pieceName = getNodeValue(movementNodes, "peca");
			
			EntityManagerHelper.getInstance().startTransaction();
			
			// Cria uma nova ação
			Action action = new Action();
			action.setValidMove(true);
			action.setMoveTime(0);

			//Pega sessão ativa do jogador
			SessionDAO sDAO = new SessionDAO();
			Integer sessionId = sDAO.findCurrentPatientSessionId(patient);
			
			if (sessionId == null) {
				Session session = createNewSession(gameId, patient);
				sessionId = session.getId(); 
			}
			
			SessionGamePhase gamePhase = null;
			synchronized (EntityManagerHelper.getInstance()) {
				SessionGamePhaseDAO sgpDAO = new SessionGamePhaseDAO();
				gamePhase = sgpDAO.findById(SessionGamePhase.class, new SessionGamePhaseId(phaseId, sessionId));
				if (gamePhase == null)
					gamePhase = sgpDAO.merge(new SessionGamePhase(phaseId, sessionId));				
			}

			action.setSessionGamePhase(gamePhase);

			ActionTypeDAO atDAO = new ActionTypeDAO();
			action.setActionType(atDAO.findById(ActionType.class, actionTypeId));

			ActionDAO aDAO = new ActionDAO();
			aDAO.create(action);
			
			// Cria o relacionamento entre a ação, movimento e peça
			ActionMovement actionMovement = new ActionMovement();
			ActionMovementPK actionMovementPK = new ActionMovementPK();
			actionMovement.setPk(actionMovementPK);
			
			PieceDAO pDAO = new PieceDAO();
			
			actionMovement.setGameId(gameId);
			Integer pieceId = pDAO.getPieceId(pieceName);
			if (pieceId != null) {
				actionMovement.setPieceId(pieceId);
			}
			actionMovementPK.setActionId(action.getId());
			actionMovementPK.setMovementId(movementId);
			
			ActionMovementDAO amDAO = new ActionMovementDAO();
			amDAO.create(actionMovement);

			EntityManagerHelper.getInstance().commitTransaction();

			return Response.ok(getResult(), MediaType.APPLICATION_XML).build();
		} catch (Exception e) {
			EntityManagerHelper.getInstance().rollbackTransaction();
			
			throw new WebApplicationException(e,
					Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	private Session createNewSession(Integer gameId, String patient) {
		Session session = new Session();
		
		PatientDAO pDAO = new PatientDAO();
		session.setPatient(pDAO.findByName(patient));

		GameDAO gDAO = new GameDAO();
		session.setGame(gDAO.findById(Game.class, gameId));

		session.setSessionStartDate(new Date());
		session.setStatus(PLAYING_GAME);

		SessionDAO sesDao = new SessionDAO();

		//create a new session
		sesDao.create(session);
		
		return session;
	}

	@GET()
	@Path("{phaseId}/{sessionId}")
	@Produces("text/xml")
	public StreamingOutput getActions(@PathParam ("phaseId") String phaseId, @PathParam ("sessionId") String sessionId) {
		EntityManagerHelper.getInstance().startTransaction();
		
		ActionDAO aDAO = new ActionDAO();
		final List<Action> events = aDAO.listActions(Integer.valueOf(sessionId), Integer.valueOf(phaseId));

		for (Action action : events)
			action.setSentToAttendant(true);
		
		EntityManagerHelper.getInstance().commitTransaction();
		EntityManagerHelper.getInstance().closeEntityManager();
		
		return new StreamingOutput() {
			public void write(OutputStream outputStream) {
				PrintWriter out = new PrintWriter(outputStream);
				out.println("<?xml version=\"1.0\"?>");
				out.println("<acaolista>");
				for (Action action : events)
				{
					out.println("<acao>");
					out.println("<id>" + action.getId() + "</id>");
					out.println("<tipo>" + action.getActionType().getDescription() + "</tipo>");
					
					for (ActionMovement actionMovement : action.getActionMovements()) {
						out.println("<jogada>");
						out.println("<nome>" + actionMovement.getMovement().getMovementDesc() + "</nome>");
						if (actionMovement.getPiece() != null) {
							out.println("<peca>" + actionMovement.getPiece().getPieceName() + "</peca>");							
						} else {
							out.println("<peca></peca>");
						}
						out.println("</jogada>");
					}
					
					out.println("</acao>");
				}
				out.println("</acaolista>");
				
				out.close();
			}
		};
	}
}
