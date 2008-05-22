package br.ufrj.nce.labase.attention.montapecas;

import java.awt.Point;

import br.ufrj.nce.labase.phidias.swing.GameBoard;
import br.ufrj.nce.labase.phidias.swing.Mapping;
import br.ufrj.nce.labase.phidias.swing.Sprite;
import br.ufrj.nce.labase.phidias.swing.StaticImage;
import br.ufrj.nce.labase.phidias.swing.images.Images;

public class MontaPecasGameBoard extends GameBoard {
	
	@Override
	public void init() {
		this.setBackgroundImage(Images.createImage("jmonta_pedacos.jpg"));
		
		this.addGraphicPrintable(new StaticImage(new Point(604, 190), Images.createImage("montapedacos_silueta.gif")));

		this.addSprite(new Sprite(new Point(80,230), "montapedacos_cabeca.gif", Mapping.getMapping(new Point(637, 208))));
		this.addSprite(new Sprite(new Point(130,230), "montapedacos_tronco.gif", Mapping.getMapping(new Point(637, 262))));
		this.addSprite(new Sprite(new Point(195,230), "montapedacos_braco_direito.gif", Mapping.getMapping(new Point(618, 267))));
		this.addSprite(new Sprite(new Point(235,230), "montapedacos_braco_esquerdo.gif", Mapping.getMapping(new Point(663, 265))));
		this.addSprite(new Sprite(new Point(274,230), "montapedacos_perna_direita.gif", Mapping.getMapping(new Point(655, 364))));
		this.addSprite(new Sprite(new Point(315,230), "montapedacos_perna_esquerda.gif", Mapping.getMapping(new Point(625, 364))));
		this.spriteManager.setSnapToDestination(true);
		super.init();
	}

}
