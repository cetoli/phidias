package br.ufrj.nce.labase.attention.bichos;

import java.awt.Point;
import java.awt.geom.Point2D;

import br.ufrj.nce.labase.phidias.toolkit.sprite.NPC;
import br.ufrj.nce.labase.phidias.toolkit.PickingGameBoard;

public class BichosGameBoard extends PickingGameBoard{

	private static final String IMAGES_PACKAGE = "br.ufrj.nce.labase.attention.bichos.images"; 
		
	public void initPickingGame(){
		this.setScreenSize(800, 600);
		this.setBackgroundImage("background.gif");

		this.createSprite(new Point(391,128), "abelha1.gif");
		this.createSprite(new Point(192,297), "aranha-1.gif");
		this.createSprite(new Point(80,360), "avestruz.gif");
		this.createSprite(new Point(480,186), "bambi1.gif");
		this.createSprite(new Point(125,240), "bicho.gif");
		this.createSprite(new Point(165,99), "camelo1.gif");
		this.createSprite(new Point(64,275), "canguru.gif");
		this.createSprite(new Point(252,259), "caracol.gif");
		this.createSprite(new Point(434,362), "caranguejo1.gif");
		this.createSprite(new Point(352,237), "coelho-1.gif");
		this.createSprite(new Point(464,62), "coruja-1.gif");
		this.createSprite(new Point(147,169), "elefante1.gif");
		this.createSprite(new Point(253,136), "esquilo-1.gif");
		this.createSprite(new Point(381,194), "esquilo2.gif");
		this.createSprite(new Point(237,292), "foca1.gif");
		this.createSprite(new Point(429,233), "galo-1.gif");
		this.createSprite(new Point(110,323), "gamba-1.gif");
		this.createSprite(new Point(55,100), "girafa1.gif");
		this.createSprite(new Point(228,372), "jacare1.gif");
		this.createSprite(new Point(63,206), "leao1.gif");
		this.createSprite(new Point(426,155), "lobo-1.gif");
		this.createSprite(new Point(310,94), "mico-1.gif");
		this.createSprite(new Point(284,391), "minhoca-1.gif");
		this.createSprite(new Point(165,349), "minoca2.gif");
		this.createSprite(new Point(137,44), "morcego-1.gif");
		this.createSprite(new Point(133,277), "onca.gif");
		this.createSprite(new Point(294,270), "panda-1.gif");
		this.createSprite(new Point(397,48), "passarinho-1.gif");
		this.createSprite(new Point(327,317), "peixe-1.gif");
		this.createSprite(new Point(459,383), "rato-1.gif");
		this.createSprite(new Point(193,229), "rino-1.gif");
		this.createSprite(new Point(173,326), "tartaruga-1.gif");
		this.createSprite(new Point(248,215), "bicho2.gif");
		this.createSprite(new Point(396,383), "tatu.gif");
		this.createSprite(new Point(338,198), "guaxinim.gif");
		this.createSprite(new Point(142,387), "porco.gif");
		this.createSprite(new Point(350,137), "urso1.gif");
		this.createSprite(new Point(218,168), "veado1.gif");
		this.createSprite(new Point(100,128), "zebra-1.gif");
		
		this.getSpriteManager().setSpriteHoverEnabled(true);
		
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