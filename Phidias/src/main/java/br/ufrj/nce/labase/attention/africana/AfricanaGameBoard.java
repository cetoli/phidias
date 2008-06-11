package br.ufrj.nce.labase.attention.africana;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;

import br.ufrj.nce.labase.phidias.toolkit.PickingGameBoard;

public class AfricanaGameBoard extends PickingGameBoard {

	private static final String IMAGES_PACKAGE = "br.ufrj.nce.labase.attention.africana.images"; 
	
	public void initPickingGame(){
		this.setBackgroundImage("plano-de-fundo-2.gif");

		this.createSprite(new Point(29,30), "peca1.gif");
		this.createSprite(new Point(44,162), "peca2.gif");
		this.createSprite(new Point(35,192), "peca3.gif");
		this.createSprite(new Point(33,266), "peca4.gif");
		this.createSprite(new Point(35,330), "peca5.gif");
		this.createSprite(new Point(89,158), "peca6.gif");
		this.createSprite(new Point(101,233), "peca7.gif");
		this.createSprite(new Point(119,289), "peca8.gif");
		this.createSprite(new Point(132,229), "peca9.gif");
		this.createSprite(new Point(120,343), "peca10.gif");
		this.createSprite(new Point(112,29), "peca11.gif");
		this.createSprite(new Point(156,160), "peca12.gif");
		this.createSprite(new Point(183,345), "peca13.gif");
		this.createSprite(new Point(221,301), "peca14.gif");
		this.createSprite(new Point(248,265), "peca15.gif");
		this.createSprite(new Point(192,31), "peca16.gif");
		this.createSprite(new Point(253,15), "peca17.gif");
		this.createSprite(new Point(229,94), "peca18.gif");
		this.createSprite(new Point(233,143), "peca19.gif");
		this.createSprite(new Point(245,216), "peca20.gif");
		this.createSprite(new Point(239,345), "peca21.gif");
		this.createSprite(new Point(265,325), "peca22.gif");
		this.createSprite(new Point(305,260), "peca23.gif");
		this.createSprite(new Point(298,149), "peca24.gif");
		this.createSprite(new Point(271,77), "peca25.gif");
		this.createSprite(new Point(316,29), "peca26.gif");
		this.createSprite(new Point(363,85), "peca27.gif");
		this.createSprite(new Point(416,35), "peca28.gif");
		this.createSprite(new Point(381,142), "peca29.gif");
		this.createSprite(new Point(385,198), "peca30.gif");
		this.createSprite(new Point(364,311), "peca31.gif");
		this.createSprite(new Point(424,315), "peca32.gif");
		this.createSprite(new Point(363,336), "peca33.gif");
		
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
