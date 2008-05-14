package br.ufrj.nce.labase.elastico;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.util.LinkedList;

public class Elastico extends GraphicPrintElement {

	private Point coordenadaInicial;

	private LinkedList<Point> coordenadas;

	private boolean finished;

	public LinkedList<Point> getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(LinkedList<Point> coordenadas) {
		this.coordenadas = coordenadas;
	}

	public void addCoordenada(Point coordenada) {
		if (this.getBody() != null)
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
				this.setBody(new Polygon());
				for (Point ponto : this.coordenadas)
					((Polygon) this.getBody()).addPoint((int) ponto.getX(), (int) ponto.getY());

				this.finished = true;
			}

		}

	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean concluido) {
		this.finished = concluido;
	}

	public void print(Graphics2D g2d) {
		if (this.isFinished()) {
			g2d.setStroke(new BasicStroke(5.0f));
			g2d.setPaint(this.getColor());
			g2d.draw(this.getBody());
		} else {
			if (this.getCoordenadas() != null) {
				Point[] pontos = new Point[this.getCoordenadas().size()];
				this.getCoordenadas().toArray(pontos);
				for (int i = 0; i < pontos.length - 1; i++) {
					g2d.setStroke(new BasicStroke(5.0f));
					g2d.setPaint(this.getColor());
					g2d.draw(new Line2D.Double(pontos[i].getX(), pontos[i].getY(), pontos[i + 1].getX(), pontos[i + 1].getY()));
				}
			}
		}

	}
}
