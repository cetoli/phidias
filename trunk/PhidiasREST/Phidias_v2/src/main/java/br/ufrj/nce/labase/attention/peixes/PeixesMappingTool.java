package br.ufrj.nce.labase.attention.peixes;

import java.awt.Point;

import br.ufrj.nce.labase.tools.MappingTool;

public class PeixesMappingTool extends MappingTool {

	private static final String IMAGES_PACKAGE = "br.ufrj.nce.labase.attention.peixes.images"; 

	public void initGame() {
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
	}

	@Override
    public String getImagesPackageName() {
    	return IMAGES_PACKAGE ;
    }
	
	public String getImagefile(){
		return "background.gif";
	}

}
