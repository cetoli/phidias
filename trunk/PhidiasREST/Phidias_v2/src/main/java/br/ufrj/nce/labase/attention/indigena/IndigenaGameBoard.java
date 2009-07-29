package br.ufrj.nce.labase.attention.indigena;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;

import br.ufrj.nce.labase.phidias.toolkit.PickingGameBoard;

public class IndigenaGameBoard extends PickingGameBoard{

	private static final String IMAGES_PACKAGE = "br.ufrj.nce.labase.attention.indigena.images"; 
	
	public void initPickingGame(){
		this.setBackgroundImage("fundo.GIF");

		this.createSprite(new Point(53,52), "i1.GIF");
		this.createSprite(new Point(230,40), "i2.GIF");
		this.createSprite(new Point(185,165), "i3.GIF");
		this.createSprite(new Point(313,177), "i4.GIF");
		this.createSprite(new Point(60,178), "i5.GIF");
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
