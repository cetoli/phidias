package br.ufrj.nce.labase.attention.bichosestilizados;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;

import br.ufrj.nce.labase.phidias.toolkit.PickingGameBoard;

public class BichosEstilizadosGameBoard extends PickingGameBoard{

	private static final String IMAGES_PACKAGE = "br.ufrj.nce.labase.attention.bichosestilizados.images"; 
	
	public void initPickingGame(){
		this.setBackgroundImage("fundo.gif");

		this.createSprite(new Point(240,40), "c1.gif");
		this.createSprite(new Point(60,50), "c2.gif");
		this.createSprite(new Point(65,210), "c3.gif");
		this.createSprite(new Point(240,220), "c4.gif");
		
		this.getSpriteManager().setSpriteHoverEnabled(true);
	}
	
	@Override
	protected Point2D getImageCoordinate() {
		return new Point(600, 20);
	}

	@Override
	public String getImagesPackageName() {
		return IMAGES_PACKAGE;
	}

	@Override
	public int getScreenHeight() {
		return 600;
	}

	@Override
	public int getScreenWidth() {
		return 800;
	}

	@Override
	public void handlePhaseFive() {
	}

	@Override
	public void handlePhaseFour() {
	}

	@Override
	public void handlePhaseOne() {
	}

	@Override
	public void handlePhaseSeven() {
	}

	@Override
	public void handlePhaseSix() {
	}

	@Override
	public void handlePhaseThree() {
	}

	@Override
	public void handlePhaseTwo() {
	}

	@Override
	public void paintGameBoard(Graphics g) {
	}

	
}
