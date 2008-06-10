package br.ufrj.nce.labase.attention.conjuntos;

import java.awt.Point;

import br.ufrj.nce.labase.phidias.toolkit.graphic.StaticImage;
import br.ufrj.nce.labase.tools.MappingTool;

public class ConjuntosMappingTool extends MappingTool {

	private static final String IMAGES_PACKAGE = "br.ufrj.nce.labase.attention.conjuntos.images"; 

	public void initGame() {
			this.setBackgroundImage("fundo_final.gif");
			this.addGraphicPrintable(new StaticImage(new Point(530, 48), getImageName("silhueta.gif")));
			
			this.createSprite(new Point(37,29), "peca-11.gif");
			this.createSprite(new Point(159, 16), "peca-12.gif");
			this.createSprite(new Point(283,22), "peca-13.gif");
			this.createSprite(new Point(408,23), "peca-14.gif");
			this.createSprite(new Point(530,21), "peca-15.gif");
			this.createSprite(new Point(635,21), "peca-16.gif");
			this.createSprite(new Point(488,350), "peca-17.gif");
			this.createSprite(new Point(32,357), "peca-18.gif");
			this.createSprite(new Point(200,340), "peca-20.gif");
			this.createSprite(new Point(343,345), "peca-22.gif");
	}

	@Override
    public String getImagesPackageName() {
    	return IMAGES_PACKAGE ;
    }
	
	public String getImagefile(){
		return "background.gif";
	}

}
