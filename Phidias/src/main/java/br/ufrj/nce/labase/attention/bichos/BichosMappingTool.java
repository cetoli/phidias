package br.ufrj.nce.labase.attention.bichos;



import java.awt.Point;

import br.ufrj.nce.labase.tools.MappingTool;

public class BichosMappingTool extends MappingTool {

	private static final String IMAGES_PACKAGE = "br.ufrj.nce.labase.attention.bichos.images"; 

	public void initGame() {
		this.setBackgroundImage("background.gif");
//		this.setBackgroundImage("jogo_completo.gif");
			//                         (-25, -36)
			this.createSprite(new Point(341,56), "abelha1.gif");
			this.createSprite(new Point(142,225), "aranha-1.gif");
			this.createSprite(new Point(55,324), "avestruz.gif");
			this.createSprite(new Point(455,150), "bambi1.gif");
			this.createSprite(new Point(99,205), "bicho.gif");
			this.createSprite(new Point(140,63), "camelo1.gif");
			this.createSprite(new Point(39,240), "canguru.gif");
			this.createSprite(new Point(227,223), "caracol.gif");
			this.createSprite(new Point(420,345), "caranguejo1.gif");
			this.createSprite(new Point(326,225), "coelho-1.gif");
			this.createSprite(new Point(440,28), "coruja-1.gif");
			this.createSprite(new Point(90,136), "elefante1.gif");
			this.createSprite(new Point(352,174), "esquilo-1.gif");
			
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

	}

	@Override
    public String getImagesPackageName() {
    	return IMAGES_PACKAGE ;
    }
	
	public String getImagefile(){
		return "background.gif";
	}

}
