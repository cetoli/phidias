package br.ufrj.nce.labase.attention.boneco;

import java.awt.Graphics;
import java.awt.Point;

import br.ufrj.nce.labase.phidias.toolkit.GameBoard;
import br.ufrj.nce.labase.phidias.toolkit.graphic.StaticImage;
import br.ufrj.nce.labase.phidias.toolkit.mapping.Mapping;

public class BonecoGameboard extends GameBoard {

	private static final String IMAGES_PACKAGE = "br.ufrj.nce.labase.attention.boneco.images"; 
	
	public void initGame(){
		this.setBackgroundImage("background.gif");
		
		// mapeamento impossível para identificar peças errôneas
		Mapping erroMapping = Mapping.getMapping(new Point(999,999));

		this.addGraphicPrintable(new StaticImage(new Point(530, 48), this.getImageName("silhueta.gif")));
		
		this.createSprite(new Point(37,29), "cabeca.gif", Mapping.getMapping(new Point(629, 99)));
		this.createSprite(new Point(267,145), "bracoesquerdo.gif", Mapping.getMapping(new Point(700, 212)));
		this.createSprite(new Point(196,45), "bracodireito.gif", Mapping.getMapping(new Point(556, 211)));
		this.createSprite(new Point(27,147), "peca-1.gif", erroMapping);
		this.createSprite(new Point(316,28), "peca-2.gif", erroMapping);
		this.createSprite(new Point(197,250), "peca-3.gif", erroMapping);
		this.createSprite(new Point(293,289), "peca-4.gif", erroMapping);
		this.createSprite(new Point(417,332), "peca-5.gif", erroMapping);
		this.createSprite(new Point(390,186), "pernadireita.gif", Mapping.getMapping(new Point(585, 326)));
		this.createSprite(new Point(100,156), "pernaesquerda.gif", Mapping.getMapping(new Point(676, 327)));
		this.createSprite(new Point(40,270), "tronco.gif", Mapping.getMapping(new Point(628, 210)));
		this.spriteManager.setSnapToDestination(true);
		
//		this.setNpc(new NPC(this.getSpriteManager(), new Point(600, 160), this.getImageName("NPC.gif")));

	//	this.npcSayText("Testando estímulos do NPC");
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePhaseFour() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePhaseOne() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePhaseSeven() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePhaseSix() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePhaseThree() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePhaseTwo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paintGameBoard(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
