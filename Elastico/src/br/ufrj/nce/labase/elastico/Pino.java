package br.ufrj.nce.labase.elastico;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

public class Pino {
	private Color color;
	private Ellipse2D elemento;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Ellipse2D getElemento() {
		return elemento;
	}

	public void setElemento(Ellipse2D elemento) {
		this.elemento = elemento;
	}
}
