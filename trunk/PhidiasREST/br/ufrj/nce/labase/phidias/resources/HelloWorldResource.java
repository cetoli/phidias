package br.ufrj.nce.labase.phidias.resources;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

//The Java class will be hosted at the URI path "/helloworld"
@Path("/helloworld")
public class HelloWorldResource {
	// The Java method will process HTTP GET requests
	@GET
	// The Java method will produce content identified by the MIME Media
	// type "text/plain"
	@Produces("text/plain")
	public String getClichedMessage() {
		// Return some cliched textual content
		return "Hello World";
	}

}

/**
URL: http://localhost:8080/PhidiasREST/helloworld

TODO:
Usar JAXB: https://jaxb.dev.java.net/
Exemplo CRUD: http://www.suryasuravarapu.com/2009/03/rest-crud-with-jax-rs-jersey.html
*/