package br.ufrj.nce.labase.attention.boneco;

import java.awt.Point;

import br.ufrj.nce.labase.phidias.toolkit.graphic.StaticImage;
import br.ufrj.nce.labase.tools.MappingTool;

public class BonecoMappingTool extends MappingTool {

	private static final String IMAGES_PACKAGE = "br.ufrj.nce.labase.games.attention.boneco.images"; 

	public void initGame() {
			this.setBackgroundImage("background.gif");
			this.addGraphicPrintable(new StaticImage(new Point(530, 48), this.createImage(getImageName("silhueta.gif"))));
			
			this.createSprite(new Point(37,29), "cabeca.gif");
			this.createSprite(new Point(196, 45), "bracoesquerdo.gif");
			this.createSprite(new Point(310,67), "bracodireito.gif");
			this.createSprite(new Point(41,197), "peca-1.gif");
			this.createSprite(new Point(171,197), "peca-2.gif");
			this.createSprite(new Point(250,197), "peca-3.gif");
			this.createSprite(new Point(150,197), "peca-4.gif");
			this.createSprite(new Point(160,317), "peca-5.gif");
			this.createSprite(new Point(210,317), "pernadireita.gif");
			this.createSprite(new Point(230,317), "pernaesquerda.gif");
			this.createSprite(new Point(370,317), "tronco.gif");
	}

	@Override
    public String getImagesPackageName() {
    	return IMAGES_PACKAGE ;
    }
	
	public String getImagefile(){
		return "background.gif";
	}

}
