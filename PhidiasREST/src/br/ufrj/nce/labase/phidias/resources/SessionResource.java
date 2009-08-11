package br.ufrj.nce.labase.phidias.resources;

import java.io.InputStream;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
import br.ufrj.nce.labase.phidias.persistence.model.Game;
import br.ufrj.nce.labase.phidias.persistence.model.Session;


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
			
			Session session = new Session();
			
			PatientDAO pDAO = new PatientDAO();
			session.setPatient(pDAO.findByName(patient));

			GameDAO gDAO = new GameDAO();
			session.setGame(gDAO.findById(Game.class, gameId));

			session.setSessionStartDate(new Date());
			session.setStatus(WAITING_FOR_ATTENDANT);

			SessionDAO sesDao = new SessionDAO();
			
			// Apaga sessões antigas do jogador
			sesDao.removeDeadSessions(patient);

			//create a new session
			sesDao.create(session);
			
			EntityManagerHelper.getInstance().commitTransaction();

			return Response.ok(getResult(), MediaType.APPLICATION_XML).build();
		} catch (Exception e) {
			EntityManagerHelper.getInstance().rollbackTransaction();
			
			throw new WebApplicationException(e,
					Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
}
