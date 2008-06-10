package br.ufrj.nce.labase.attention.bichos;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;

import br.ufrj.nce.labase.phidias.toolkit.sprite.NPC;
import br.ufrj.nce.labase.phidias.toolkit.PickingGameBoard;

public class BichosGameBoard extends PickingGameBoard{

	private static final String IMAGES_PACKAGE = "br.ufrj.nce.labase.attention.bichos.images"; 
		
	public void initPickingGame(){
		this.setBackgroundImage("background.gif");

		this.createSprite(new Point(240,120), "esquilo2.gif");
		this.createSprite(new Point(212,256), "foca1.gif");
		this.createSprite(new Point(404,200), "galo-1.gif");
		this.createSprite(new Point(90,306), "gamba-1.gif");
		this.createSprite(new Point(30,19), "girafa1.gif");
		this.createSprite(new Point(146,355), "jacare1.gif");
		this.createSprite(new Point(38,170), "leao1.gif");
		this.createSprite(new Point(401,120), "lobo-1.gif");
		this.createSprite(new Point(285,58), "mico-1.gif");
		this.createSprite(new Point(260,354), "minhoca-1.gif");
		this.createSprite(new Point(113,361), "minoca2.gif");
		this.createSprite(new Point(120,30), "morcego-1.gif");
		this.createSprite(new Point(108,241), "onca.gif");
		this.createSprite(new Point(256,136), "panda-1.gif");
		this.createSprite(new Point(372,12), "passarinho-1.gif");
		this.createSprite(new Point(315,300), "peixe-1.gif");
		this.createSprite(new Point(444,372), "rato-1.gif");
		this.createSprite(new Point(175,202), "rino-1.gif");
		this.createSprite(new Point(144,310), "tartaruga-1.gif");
		this.createSprite(new Point(223,270), "bicho2.gif");
		this.createSprite(new Point(365,368), "tatu.gif");
		this.createSprite(new Point(313,162), "guaxinim.gif");
		this.createSprite(new Point(117,351), "porco.gif");
		this.createSprite(new Point(325,101), "urso1.gif");
		this.createSprite(new Point(193,132), "veado1.gif");
		this.createSprite(new Point(75,92), "zebra-1.gif");
		
		
//		this.setNpc(new NPC(this.getSpriteManager(), new Point(600, 160), this.getImageName("NPC.gif")));

//		this.npcSayText("Testando estímulos do NPC");
		
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