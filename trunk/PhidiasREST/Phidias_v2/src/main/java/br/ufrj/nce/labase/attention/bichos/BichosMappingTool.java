package br.ufrj.nce.labase.attention.bichos;



import java.awt.Point;

import br.ufrj.nce.labase.tools.MappingTool;

public class BichosMappingTool extends MappingTool {

	private static final String IMAGES_PACKAGE = "br.ufrj.nce.labase.attention.bichos.images"; 

	public void initGame() {
		this.setBackgroundImage("background.gif");
//		this.setBackgroundImage("jogo_completo.gif");
			//                         (-25, -36)
			this.createSprite(new Point(382,111), "abelha1.gif");
			this.createSprite(new Point(182,277), "aranha-1.gif");
			this.createSprite(new Point(43,334), "avestruz.gif");
			this.createSprite(new Point(190,325), "avestruz1.gif");
			this.createSprite(new Point(455,150), "bambi1.gif");
			this.createSprite(new Point(94,200), "bicho.gif");
			this.createSprite(new Point(276,206), "bicho2.gif");
			this.createSprite(new Point(140,63), "camelo1.gif");
			this.createSprite(new Point(39,240), "canguru.gif");
			
			this.createSprite(new Point(256,247), "caracol.gif");
			this.createSprite(new Point(405,353), "caranguejo1.gif");
			this.createSprite(new Point(326,225), "coelho-1.gif");
			this.createSprite(new Point(440,28), "coruja-1.gif");
			this.createSprite(new Point(90,136), "elefante1.gif");
			this.createSprite(new Point(352,174), "esquilo-1.gif");
			
			this.createSprite(new Point(240,120), "esquilo2.gif");
			this.createSprite(new Point(214,269), "foca1.gif");
			this.createSprite(new Point(418,202), "galo-1.gif");
			this.createSprite(new Point(97,306), "gamba-1.gif");
			this.createSprite(new Point(324,179), "guaxinim.gif");
			this.createSprite(new Point(30,18), "girafa1.gif");
			this.createSprite(new Point(38,178), "leao1.gif");
			this.createSprite(new Point(388,134), "lobo-1.gif");
			this.createSprite(new Point(285,50), "mico-1.gif");
			this.createSprite(new Point(272,374), "minhoca-1.gif");
			this.createSprite(new Point(140,326), "minoca2.gif");
			this.createSprite(new Point(111,368), "porco.gif");
			this.createSprite(new Point(132,355), "jacare1.gif");
			this.createSprite(new Point(108,42), "morcego-1.gif");
			this.createSprite(new Point(91,256), "onca.gif");
			this.createSprite(new Point(257,136), "panda-1.gif");
			this.createSprite(new Point(372,12), "passarinho-1.gif");
			this.createSprite(new Point(315,300), "peixe-1.gif");
			this.createSprite(new Point(434,367), "rato-1.gif");
			this.createSprite(new Point(175,202), "rino-1.gif");
			this.createSprite(new Point(154,297), "tartaruga-1.gif");
			this.createSprite(new Point(346,366), "tatu.gif");
			this.createSprite(new Point(335,99), "urso1.gif");
			this.createSprite(new Point(193,132), "veado1.gif");
			this.createSprite(new Point(75,92), "zebra-1.gif");
			this.createSprite(new Point(1,1), "botao_sem_sombra.gif");

	}

	@Override
    public String getImagesPackageName() {
    	return IMAGES_PACKAGE ;
    }
	
	public String getImagefile(){
		return "background.gif";
	}

}
