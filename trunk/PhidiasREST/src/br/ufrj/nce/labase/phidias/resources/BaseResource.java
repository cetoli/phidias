package br.ufrj.nce.labase.phidias.resources;

import java.io.OutputStream;
import java.io.PrintWriter;

import javax.ws.rs.core.StreamingOutput;

import org.w3c.dom.NodeList;

import com.sun.org.apache.xerces.internal.dom.ChildNode;

public class BaseResource {
	protected StreamingOutput getResult() {
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
	
	protected String getNodeValue(NodeList nodes, String nodeName) {
		String retValue = null;
		
		for (int i = 0; i < nodes.getLength(); i++) {
			ChildNode childElement = (ChildNode)nodes.item(i);
			
			if (nodeName.equals(childElement.getNodeName())) {
				retValue = childElement.getTextContent();
			}
		}
		
		return retValue;
	}
	
	protected NodeList getChildNodes(NodeList nodes, String nodeName) {
		NodeList retValue = null;
		
		for (int i = 0; i < nodes.getLength(); i++) {
			ChildNode childElement = (ChildNode)nodes.item(i);
			
			if (nodeName.equals(childElement.getNodeName())) {
				retValue = childElement.getChildNodes();
			}
		}
		
		return retValue;
	}
}
