package br.ufrj.nce.labase.phidias.resources;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.StreamingOutput;

import br.ufrj.nce.labase.phidias.persistence.dao.ActionTypeDAO;
import br.ufrj.nce.labase.phidias.persistence.model.ActionType;

@Path("/tipoacao")
public class ActionTypeResource extends BaseResource {

	@GET()
	@Path("/jogo/{gameId}")
	@Produces("text/xml")
	public StreamingOutput getGameActionTypes(@PathParam ("gameId") Long gameId) {
		ActionTypeDAO actionTypeDAO = new ActionTypeDAO();
		final List<ActionType> actionTypeList = actionTypeDAO.getGameActionTypes(gameId);
		
		return new StreamingOutput() {
			public void write(OutputStream outputStream) {
				PrintWriter out = new PrintWriter(outputStream);
				out.println("<?xml version=\"1.0\"?>");
				out.println("<tipoacoes>");

				for (ActionType actionType : actionTypeList) {
					out.print("  <tipoacao id=\"" + actionType.getId()
							+ "\" nome=\"" + actionType.getDescription() + "\"/>");
				}
				
				out.println("</tipoacoes>");
				
				out.close();
			}
		};
	}
}
