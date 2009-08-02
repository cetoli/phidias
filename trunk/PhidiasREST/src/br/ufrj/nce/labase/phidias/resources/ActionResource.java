package br.ufrj.nce.labase.phidias.resources;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

@Path("/acao")
public class ActionResource {

//	@Path("/{sessionId}{phaseId}/{actionType}/{movements}/")
	@POST
	@Consumes ("text/xml")
	public Response createAction(InputStream actionData) {
		try {
			System.out.println(actionData);

			return Response.created(URI.create("/acao")).build();
		} catch (Exception e) {
			throw new WebApplicationException(e,
					Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GET()
	@Produces("text/xml")
	public StreamingOutput getActions() {
		
		return new StreamingOutput() {
			public void write(OutputStream outputStream) {
				PrintWriter out = new PrintWriter(outputStream);
				out.println("<?xml version=\"1.0\"?>");
				out.println("<resultado>");
				out.println("<valor>OK</valor>");
				out.println("</resultado>");
				
				out.close();
			}
		};
	}
}
