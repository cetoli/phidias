package br.ufrj.nce.labase.elastico;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Elastico {

	private Point coordenadaInicial;

	private LinkedList<Point> coordenadas;

	private Polygon poligono;

	private Color color;

	private boolean concluido;

	public LinkedList<Point> getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(LinkedList<Point> coordenadas) {
		this.coordenadas = coordenadas;
	}

	public Polygon getPoligono() {
		return poligono;
	}

	public void setPoligono(Polygon poligono) {
		this.poligono = poligono;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void addCoordenada(Point coordenada) {
		if (this.poligono != null)
			return;

		if (this.coordenadas == null)
			this.coordenadas = new LinkedList<Point>();
		// Se for a primeira coordenada atribui a variavel que armazena a
		// variavel de origem
		if (this.coordenadas.size() == 0)
			this.coordenadaInicial = coordenada;

		// Trata se a coordenada não foi inserida
		if (this.coordenadas.indexOf(coordenada) == -1) {
			this.coordenadas.add(coordenada);
		} else {
			// Trata se a coordenada inserida é a coordenada de origem e gera o
			// poligono caso tenha mais de 2 pontos
			if (this.coordenadas.size() > 2 && coordenada.equals(coordenadaInicial)) {
				this.poligono = new Polygon();
				for (Point ponto : this.coordenadas)
					this.poligono.addPoint((int) ponto.getX(), (int) ponto.getY());

				this.concluido = true;
			}

		}

	}

	public boolean isConcluido() {
		return concluido;
	}

	public void setConcluido(boolean concluido) {
		this.concluido = concluido;
	}
}
