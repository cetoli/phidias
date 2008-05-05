package br.ufrj.nce.labase.elastico;

import java.awt.Image;
import java.awt.Point;

public class Carta {
	private Point coordenada;

	private Image image;

	public Point getCoordenada() {
		return coordenada;
	}

	public void setCoordenada(Point coordenada) {
		this.coordenada = coordenada;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Carta(Point coordenada, Image image) {
		super();
		this.coordenada = coordenada;
		this.image = image;
	}

	
}
