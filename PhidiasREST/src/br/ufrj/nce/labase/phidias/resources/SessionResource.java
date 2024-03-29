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
import br.ufrj.nce.labase.phidias.persistence.dao.GameDAO;
import br.ufrj.nce.labase.phidias.persistence.dao.PatientDAO;
import br.ufrj.nce.labase.phidias.persistence.dao.SessionDAO;
import br.ufrj.nce.labase.phidias.persistence.dao.SessionGamePhaseDAO;
import br.ufrj.nce.labase.phidias.persistence.model.Game;
import br.ufrj.nce.labase.phidias.persistence.model.Patient;
import br.ufrj.nce.labase.phidias.persistence.model.Session;
import br.ufrj.nce.labase.phidias.persistence.model.SessionGamePhase;


@Path("/sessao")
public class SessionResource extends BaseResource {
	private final int WAITING_FOR_ATTENDANT = 0;
	
	@POST
	@Consumes("text/xml")
	@Produces("text/xml")
	public Response createAction(@Context UriInfo uriInfo, InputStream actionData) {
		try {
			DocumentBuilder documentBuilder = DocumentBuilderFactory
					.newInstance().newDocumentBuilder();
			Document document = documentBuilder.parse(actionData);
			document.getDocumentElement().normalize();
				
			Element rootElement = (Element) document.getElementsByTagName("sessao").item(0);
			NodeList rootNodes = rootElement.getChildNodes();
				
			Integer gameId = Integer.valueOf(getNodeValue(rootNodes, "jogo"));
			String patient = getNodeValue(rootNodes, "jogador");
			
			EntityManagerHelper.getInstance().startTransaction();
			
			PatientDAO pDAO = new PatientDAO();
			Patient patientEntity = pDAO.findByName(patient);
			
			Session session = new Session();
			
			session.setPatient(patientEntity);

			GameDAO gDAO = new GameDAO();
			session.setGame(gDAO.findById(Game.class, gameId));

			session.setSessionStartDate(new Date());
			session.setStatus(WAITING_FOR_ATTENDANT);

			SessionDAO sesDao = new SessionDAO();
			
			// Apaga sess�es antigas do jogador
			//kill sessions not finalized.
			List<Session> deadSessions = sesDao.findDeadSession(patientEntity.getId());
			for (Session sessDead : deadSessions) {
				sessDead.setSessionEndDate(new Date());
				sesDao.update(sessDead);
			}

			//create a new session
			sesDao.create(session);
			
			SessionGamePhaseDAO sgpDAO = new SessionGamePhaseDAO();
			sgpDAO.create(new SessionGamePhase(1, session.getId()));
			
			EntityManagerHelper.getInstance().commitTransaction();

			return Response.ok(getResult(), MediaType.APPLICATION_XML).build();
		} catch (Exception e) {
			EntityManagerHelper.getInstance().rollbackTransaction();
			
			throw new WebApplicationException(e,
					Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GET
	@Path("{patient}")
	@Produces("text/xml")
	public StreamingOutput getPhase(@PathParam ("patient") String patient) {
		SessionDAO sDAO = new SessionDAO();
		int sessionId = sDAO.findCurrentPatientSessionId(patient);
		
		final int phaseId = sDAO.getPhaseId(sessionId);
		
		return new StreamingOutput() {
			public void write(OutputStream outputStream) {
				PrintWriter out = new PrintWriter(outputStream);
				out.println("<?xml version=\"1.0\"?>");
				out.println("<fase>");
				out.println("<valor>" + phaseId + "</valor>");
				out.println("</fase>");
				out.close();
			}
		};
	}
}
