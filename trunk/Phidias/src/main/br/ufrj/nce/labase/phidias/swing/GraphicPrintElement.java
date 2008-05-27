package br.ufrj.nce.labase.phidias.swing;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public abstract class GraphicPrintElement implements GraphicPrintable{

	private Color color;
	private Shape body;

	public GraphicPrintElement() {
		super();
	}

	public Shape getBody() {
		return body;
	}

	public void setBody(Shape body) {
		this.body = body;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}