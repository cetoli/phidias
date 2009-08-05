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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.org.apache.xerces.internal.dom.ChildNode;


@Path("/acao")
public class ActionResource {
	
	@POST
	@Consumes("text/xml")
	public Response createAction(InputStream actionData) {
		try {
			DocumentBuilder documentBuilder =
			DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = documentBuilder.parse(actionData);
			document.getDocumentElement().normalize();
				
			NodeList nodeList = document.getElementsByTagName("login");
			Node loginRoot = nodeList.item(0);
			
			if (loginRoot.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) loginRoot;
				NodeList childNodes = element.getChildNodes();
				
				for (int i = 0; i < childNodes.getLength(); i++) {
					ChildNode childElement = (ChildNode)childNodes.item(i);
					String nodeName = childElement.getNodeName();
					if (!"#text".equals(nodeName)) {
						String textContent = childElement.getTextContent();
						
						System.out.println(nodeName);
						System.out.println(textContent);
					}
				}
			}
			
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
