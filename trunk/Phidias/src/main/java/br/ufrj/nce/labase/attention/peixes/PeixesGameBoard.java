package br.ufrj.nce.labase.attention.peixes;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;

import br.ufrj.nce.labase.phidias.toolkit.PickingGameBoard;

public class PeixesGameBoard extends PickingGameBoard {

	private static final String IMAGES_PACKAGE = "br.ufrj.nce.labase.attention.peixes.images"; 
	
	public void initPickingGame(){
		this.setBackgroundImage("castelo.gif");

		this.setBackgroundImage("aquario.gif");

		this.createSprite(new Point(93,51), "peca1.gif");
		this.createSprite(new Point(36,199), "peca2.gif");
		this.createSprite(new Point(180,202), "peca3.gif");
		this.createSprite(new Point(348,223), "peca4.gif");
		this.createSprite(new Point(110,256), "peca5.gif");
		this.createSprite(new Point(178,294), "peca6.gif");
		this.createSprite(new Point(297,327), "peca7.gif");
		this.createSprite(new Point(371,290), "peca8.gif");
//		this.createSprite(new Point(210,250), "peca9.gif");
		this.createSprite(new Point(290,76), "peca10.gif");
		
	//	this.getSpriteManager().setSpriteHoverEnabled(true);
		
	//	this.setNpc(new NPC(this.getSpriteManager(), new Point(600, 160), this.getImageName("NPC.gif")));

	//	this.npcSayText("Testando estímulos do NPC");
		
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
