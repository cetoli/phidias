package br.ufrj.nce.labase.attention.castelo;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;

import br.ufrj.nce.labase.phidias.toolkit.PickingGameBoard;
import br.ufrj.nce.labase.phidias.toolkit.sprite.NPC;

public class CasteloGameBoard extends PickingGameBoard {

	private static final String IMAGES_PACKAGE = "br.ufrj.nce.labase.attention.castelo.images"; 
	
	public void initPickingGame(){
		this.setBackgroundImage("castelo.gif");

		this.createSprite(new Point(45,150), "peca1.gif");
		this.createSprite(new Point(15,200), "peca2.gif");
		this.createSprite(new Point(65,210), "peca3.gif");
		this.createSprite(new Point(25,290), "peca4.gif");
		this.createSprite(new Point(120,270), "peca5.gif");
		this.createSprite(new Point(250,30), "peca6.gif");
		this.createSprite(new Point(150,330), "peca7.gif");
		this.createSprite(new Point(160,170), "peca8.gif");
		this.createSprite(new Point(210,250), "peca9.gif");
		this.createSprite(new Point(230,100), "peca10.gif");
		this.createSprite(new Point(370,250), "peca11.gif");
		this.createSprite(new Point(470,290), "peca12.gif");
		this.createSprite(new Point(430,230), "peca13.gif");
		this.createSprite(new Point(420,130), "peca14.gif");
		this.createSprite(new Point(290,280), "peca15.gif");
		this.createSprite(new Point(280,200), "peca16.gif");
		this.createSprite(new Point(320,140), "peca17.gif");
		
	//	this.getSpriteManager().setSpriteHoverEnabled(true);
		
		this.setNpc(new NPC(this.getSpriteManager(), new Point(600, 160), this.getImageName("NPC.gif")));

		this.npcSayText("Testando est�mulos do NPC");
		
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
