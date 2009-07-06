package br.ufrj.nce.labase.attention.bonecocolorido;



import java.awt.Point;

import br.ufrj.nce.labase.phidias.toolkit.graphic.StaticImage;
import br.ufrj.nce.labase.phidias.toolkit.mapping.Mapping;
import br.ufrj.nce.labase.tools.MappingTool;

public class BonecoColoridoMappingTool extends MappingTool {

	private static final String IMAGES_PACKAGE = "br.ufrj.nce.labase.attention.bonecocolorido.images"; 

	public void initGame() {
		this.setBackgroundImage("plano_de_fundo.gif");
		
//		this.addGraphicPrintable(new StaticImage(new Point(487,15), this.getImageName("silhuetacerta.gif")));

		this.createSprite(new Point(487,15), "silhuetacerta.gif");
		this.createSprite(new Point(327,272), "peca1-2.gif");
		this.createSprite(new Point(178,110), "peca2.gif");
		this.createSprite(new Point(397,28), "peca3.gif");
		this.createSprite(new Point(277,299), "peca5.gif");
		this.createSprite(new Point(365,119), "peca7.gif");
		this.createSprite(new Point(165,266), "peca9.gif");
		this.createSprite(new Point(228,72), "peca6.gif");
		this.createSprite(new Point(332,26), "peca4.gif");
		this.createSprite(new Point(95,368), "peca8.gif");
		this.createSprite(new Point(278,243), "peca10.gif");
		//Aqui estão os distratores//
		this.createSprite(new Point(117,239), "peca6-2.gif");
		this.createSprite(new Point(40,354), "peca6-2.gif");
		this.createSprite(new Point(22,8), "peca1.gif");
		this.createSprite(new Point(112,138), "peca9-2.gif");
		this.createSprite(new Point(44,252), "peca7-2.gif");
		this.createSprite(new Point(25,144), "peca5-2.gif");
		this.createSprite(new Point(223,293), "peca3-2.gif");
		this.createSprite(new Point(177,26), "peca10-2.gif");
	}

	@Override
    public String getImagesPackageName() {
    	return IMAGES_PACKAGE ;
    }
	
	public String getImagefile(){
		return "background.gif";
	}

}
