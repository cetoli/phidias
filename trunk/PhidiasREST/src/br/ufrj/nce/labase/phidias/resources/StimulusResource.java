package br.ufrj.nce.labase.phidias.resources;

import java.io.OutputStream;
import java.io.PrintWriter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.StreamingOutput;

import br.ufrj.nce.labase.phidias.persistence.dao.SessionDAO;
import br.ufrj.nce.labase.phidias.persistence.dao.SessionGamePhaseStimulusTypeDAO;
import br.ufrj.nce.labase.phidias.persistence.model.SessionGamePhaseStimulusType;

@Path("/stimulus")
public class StimulusResource extends BaseResource {
	@GET
	@Path("{phaseId}/{patient}")
	@Produces("text/xml")
	public StreamingOutput getNextStimulus(@PathParam ("phaseId") Integer phaseId, @PathParam ("patient") String patient) {
		SessionDAO sDAO = new SessionDAO();
		int sessionId = sDAO.findCurrentPatientSessionId(patient);
		
		SessionGamePhaseStimulusTypeDAO sgpstDAO = new SessionGamePhaseStimulusTypeDAO();
		final SessionGamePhaseStimulusType stimulus = sgpstDAO.getNextStimulus(sessionId, phaseId);
		
		if (stimulus != null) {
			stimulus.setStimulusSent(true);
			sgpstDAO.updateStimulus(stimulus);
		}
		
		return new StreamingOutput() {
			public void write(OutputStream outputStream) {
				PrintWriter out = new PrintWriter(outputStream);
				out.println("<?xml version=\"1.0\"?>");
				out.println("<estimulo>");
				if (stimulus != null) {
					out.println("<texto>" + stimulus.getStimulusText() + "</texto>");
				} else {
					out.println("<texto></texto>");
				}
				
				out.println("</estimulo>");
				out.close();
			}
		};
	}
}
