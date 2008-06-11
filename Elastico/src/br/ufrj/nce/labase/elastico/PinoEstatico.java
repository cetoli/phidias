package br.ufrj.nce.labase.elastico;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

import br.ufrj.nce.labase.phidias.toolkit.graphic.StaticImage;

public class PinoEstatico extends StaticImage {

	@Override
	public Image getImage() {
		if (!this.isSelected())
			return super.getImage();
		else
			return new RescaleOp(2.0f, 0, null).filter((BufferedImage) super.getImage(), null);
	}

	private boolean selected;

	public PinoEstatico(Point2D coordinate, Image image) {
		super(coordinate, image);
		// TODO Auto-generated constructor stub
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Rectangle2D getBody() {
		return new Rectangle2D.Double(this.getCoordinate().getX(), this.getCoordinate().getY(), this.getImage().getWidth(null), this.getImage().getHeight(null));
	}

	@Override
	public void print(Graphics2D graphic) {
		// TODO Auto-generated method stub
		super.print(graphic);

		if (this.isSelected()) {
			graphic.setStroke(new BasicStroke(4.0f));
			graphic.setPaint(Color.RED);
			graphic.draw(this.getBody());
			graphic.setStroke(new BasicStroke(1.0f));
		}
	}
}
