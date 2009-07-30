package br.ufrj.nce.labase.phidias.resources;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.StreamingOutput;

import br.ufrj.nce.labase.phidias.persistence.dao.PatientDAO;
import br.ufrj.nce.labase.phidias.persistence.model.Patient;

//The Java class will be hosted at the URI path "/helloworld"
@Path("/")
public class HelloWorldResource {
	// The Java method will process HTTP GET requests
//	@GET
	// The Java method will produce content identified by the MIME Media
	// type "text/plain"
//	@Produces("text/plain")
//	public String getClichedMessage() {
//		PatientDAO pDAO = new PatientDAO();
//		pDAO.findAll();
//		
//		// Return some cliched textual content
//		return "Hello World";
//	}
	@GET
	@Produces("text/xml")
	@Path("/paciente")
	public StreamingOutput getClichedMessage() {
		PatientDAO pDAO = new PatientDAO();
		final List<Patient> resultList = pDAO.findAll();
		
		return new StreamingOutput() {
			public void write(OutputStream outputStream) {
				PrintWriter out = new PrintWriter(outputStream);
				out.println("<?xml version=\"1.0\"?>");
				out.println("<pacientes>");

				for (Patient patient : resultList) {
					out.print("  <paciente id=\"" + patient.getId()
							+ "\" nome=\"" + patient.getName() + "\"/>");
				}
				
				out.println("</pacientes>");
				out.close();
			}
		};
	}
}



/**
URL: http://localhost:8080/PhidiasREST/helloworld

TODO:
Usar JAXB: https://jaxb.dev.java.net/
Exemplo CRUD: http://www.suryasuravarapu.com/2009/03/rest-crud-with-jax-rs-jersey.html
*/