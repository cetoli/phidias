package br.ufrj.nce.labase.elastico;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;

import br.ufrj.nce.labase.criaconto.images.Images;

public abstract class Sprite {
	private static final int SIZE = 5;

	private BufferedImage image;

	// protected vars
	private Point2D coordinate;
	
	private int width, height;
	private SpriteManager spriteManager;

	// a sprite is updated and drawn only when active
	public Sprite(SpriteManager spriteManager, Point2D coordinate, int w, int h, String imagePath) {
		this.coordinate = coordinate;
		width = w;
		height = h;
		setImage(imagePath);
		this.spriteManager = spriteManager;
	}

	public Sprite(SpriteManager spriteManager, Point2D coordinate, int w, int h, BufferedImage image) {
		this.coordinate = coordinate;
		width = w;
		height = h;
		this.image = image;
		this.spriteManager = spriteManager;
	}

	public Sprite(SpriteManager spriteManager, Point2D coordinate, BufferedImage image) {
		this.coordinate = coordinate;
		if (image != null) {
			width = image.getWidth();
			height = image.getHeight();
		}
		this.image = image;
		this.spriteManager = spriteManager;
	}

	public Sprite(SpriteManager spriteManager, Point2D coordinate, String imagePath) {
		this.coordinate = coordinate;
		setImage(imagePath);
		this.spriteManager = spriteManager;
	}
	
	public BufferedImage getImage(){
		return this.image;
	}

	public void setImage(String imagePath) {
		image = Images.getBufferedImage(imagePath);

		if (image == null) {
			System.out.println("No sprite image for " + imagePath);
			width = SIZE;
			height = SIZE;
		} else {
			width = image.getWidth();
			height = image.getHeight();
		}
	}

	public Rectangle2D getBody() {
		return new Rectangle2D.Double(this.coordinate.getX(), this.coordinate.getY(), width, height);
	}

	public boolean hasCollision() {
		return (this.spriteManager.spritesCollided(this) != null);
	}

	public List<Sprite> objectsCollided() {
		return this.spriteManager.spritesCollided(this);
	}
	
	public double getPosX() {
		return coordinate.getX();
	}

	public double getPosY() {
		return coordinate.getY();
	}

	public void setPosXY(double x, double y){
		this.coordinate.setLocation(x, y);
	}

	public abstract void mouseDragged(MouseEvent e);

	public abstract void mouseExited(MouseEvent e);

	public abstract void mouseMoved(MouseEvent e);

	public abstract void mouseEntered(MouseEvent e);

	public abstract void mouseClicked(MouseEvent e);

	public abstract void mouseReleased(MouseEvent e);

	public abstract void mousePressed(MouseEvent e);
}
