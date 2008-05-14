package br.ufrj.nce.labase.elastico;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Carta extends Sprite {

	private boolean escolhida;
	private double initialX, initialY;

	public Carta(SpriteManager spriteManager, Point2D coordinate, BufferedImage image) {
		super(spriteManager, coordinate, image);
	}

	public boolean isEscolhida() {
		return escolhida;
	}

	public void setEscolhida(boolean escolhida) {
		this.escolhida = escolhida;
	}

	public double getInitialX() {
		return initialX;
	}

	public void setInitialX(double initialX) {
		this.initialX = initialX;
	}

	public double getInitialY() {
		return initialY;
	}

	public void setInitialY(double initialY) {
		this.initialY = initialY;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.setPosXY(e.getX() - this.initialX, e.getY() - this.initialY);		
		//System.out.println(this.hasCollision());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.initialX = e.getX() - this.getPosX();
		this.initialY = e.getY() - this.getPosY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
