package br.ufrj.nce.labase.phidias.swing;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Point2D;

/**
 * 
 * Basic class for static images, that don't interact with mouse events.
 * @author Diogo Gomes
 *
 */
public class StaticImage implements GraphicPrintable {
	
	private Image image;
	
	private Point2D coordinate;
	
	public StaticImage(Point2D coordinate, Image image){
		this.coordinate = coordinate;
		this.image = image;
	}

	public void print(Graphics graphic) {
		graphic.drawImage(image, (int) coordinate.getX(), (int) coordinate.getY(), null);
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Point2D getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Point2D coordinate) {
		this.coordinate = coordinate;
	}

}
