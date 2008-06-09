package br.ufrj.nce.labase.phidias.toolkit.graphic;

import java.awt.Color;
import java.awt.Shape;

/**
 * Used for representing non-sprite elements and Geometric Graphics 2D Objects like Rectangle, Ellipse
 * and Polygons filled by a Color and should be referenced into the Gameboad.
 * 
 * @author Andre Moraes
 * 
 */
public abstract class GraphicPrintElement implements GraphicPrintable {

	private Shape body;
	private Color color;
	private boolean enabled;

	public GraphicPrintElement(Color color, Shape body) {
		super();
		this.color = color;
		this.body = body;
	}

	public Shape getBody() {
		return body;
	}

	public Color getColor() {
		return color;
	}

	public void setBody(Shape body) {
		this.body = body;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}