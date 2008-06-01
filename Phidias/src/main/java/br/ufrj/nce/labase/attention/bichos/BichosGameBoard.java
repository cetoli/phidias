package br.ufrj.nce.labase.attention.bichos;

import java.awt.Point;
import java.awt.geom.Point2D;

import br.ufrj.nce.labase.phidias.toolkit.PickingGameBoard;
import br.ufrj.nce.labase.phidias.toolkit.sprite.NPC;

public class BichosGameBoard extends PickingGameBoard{

	private static final String IMAGES_PACKAGE = "br.ufrj.nce.labase.attention.bichos.images"; 
		
	public void initPickingGame(){
		this.setScreenSize(800, 600);
		this.setBackgroundImage("background.gif");

		this.createSprite(new Point(62,275), "peca-1.gif");
		this.createSprite(new Point(134,276), "peca-2.gif");
		this.createSprite(new Point(65,210), "peca-3.gif");
		this.createSprite(new Point(25,290), "peca-4.gif");
		this.createSprite(new Point(120,270), "peca-5.gif");
		this.createSprite(new Point(250,30), "peca-6.gif");
		this.createSprite(new Point(150,330), "peca-7.gif");
		this.createSprite(new Point(160,170), "peca-8.gif");
		this.createSprite(new Point(210,250), "peca-9.gif");
		this.createSprite(new Point(230,100), "peca-10.gif");
		this.createSprite(new Point(370,250), "peca-11.gif");
		this.createSprite(new Point(470,290), "peca-12.gif");
		this.createSprite(new Point(430,230), "peca-13.gif");
		this.createSprite(new Point(420,130), "peca-14.gif");
		this.createSprite(new Point(290,280), "peca-15.gif");
		this.createSprite(new Point(280,200), "peca-16.gif");
		this.createSprite(new Point(320,140), "peca-17.gif");
		
	//	this.getSpriteManager().setSpriteHoverEnabled(true);
		
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

}
