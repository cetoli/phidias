package br.ufrj.nce.labase.attention.bonecocolorido;

import java.awt.Graphics;
import java.awt.Point;

import br.ufrj.nce.labase.phidias.toolkit.GameBoard;
import br.ufrj.nce.labase.phidias.toolkit.graphic.StaticImage;
import br.ufrj.nce.labase.phidias.toolkit.mapping.Mapping;

public class BonecoColoridoGameBoard extends GameBoard {

	private static final String IMAGES_PACKAGE = "br.ufrj.nce.labase.attention.bonecocolorido.images"; 
	
	public void initGame(){
		this.setBackgroundImage("background.gif");
		
		// mapeamento impossível para identificar peças errôneas
		Mapping erroMapping = Mapping.getMapping(new Point(999,999));

		this.setBackgroundImage("plano_de_fundo.gif");
		
		this.addGraphicPrintable(new StaticImage(new Point(487,15), this.getImageName("silhuetacerta.gif")));

		this.createSprite(new Point(327,272), "peca1-2.gif", Mapping.getMapping(new Point(619,102)));
		this.createSprite(new Point(178,110), "peca2.gif", Mapping.getMapping(new Point(618,213)));
		this.createSprite(new Point(397,28), "peca3.gif", Mapping.getMapping(new Point(553,189)));
		this.createSprite(new Point(277,299), "peca5.gif", Mapping.getMapping(new Point(679,189)));
		this.createSprite(new Point(365,119), "peca7.gif", Mapping.getMapping(new Point(594,297)));
		this.createSprite(new Point(165,266), "peca9.gif", Mapping.getMapping(new Point(646,301)));
		this.createSprite(new Point(228,72), "peca6.gif", Mapping.getMapping(new Point(698,241)));
		this.createSprite(new Point(332,26), "peca4.gif", Mapping.getMapping(new Point(542,241)));
		this.createSprite(new Point(95,368), "peca8.gif", Mapping.getMapping(new Point(570,345)));
		this.createSprite(new Point(278,243), "peca10.gif", Mapping.getMapping(new Point(668,347)));
		//Aqui estão os distratores//
		this.createSprite(new Point(117,239), "peca6-2.gif", erroMapping);
		this.createSprite(new Point(40,354), "peca6-2.gif", erroMapping);
		this.createSprite(new Point(22,8), "peca1.gif", erroMapping);
		this.createSprite(new Point(112,138), "peca9-2.gif", erroMapping);
		this.createSprite(new Point(44,252), "peca7-2.gif", erroMapping);
		this.createSprite(new Point(25,144), "peca5-2.gif", erroMapping);
		this.createSprite(new Point(223,293), "peca3-2.gif", erroMapping);
		this.createSprite(new Point(177,26), "peca10-2.gif", erroMapping);
		
		this.spriteManager.setSnapToDestination(true);
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
