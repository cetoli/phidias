package br.ufrj.nce.labase.attention.montapecas;

import java.awt.Point;

import br.ufrj.nce.labase.phidias.swing.GameBoard;
import br.ufrj.nce.labase.phidias.swing.Mapping;
import br.ufrj.nce.labase.phidias.swing.StaticImage;

public class MontaPecasGameBoard extends GameBoard {

	private static final String IMAGES_PACKAGE = "br.ufrj.nce.labase.attention.montapecas.images"; 

	@Override
	public void initGame() {
		this.setScreenSize(800, 600);
		this.setBackgroundImage("jmonta_pedacos.jpg");
		
		this.addGraphicPrintable(new StaticImage(new Point(604, 190), this.getImageName("montapedacos_silueta.gif")));

		this.createSprite(new Point(80,230), "montapedacos_cabeca.gif", Mapping.getMapping(new Point(637, 208)));
		this.createSprite(new Point(130,230), "montapedacos_tronco.gif", Mapping.getMapping(new Point(637, 262)));
		this.createSprite(new Point(195,230), "montapedacos_braco_direito.gif", Mapping.getMapping(new Point(618, 267)));
		this.createSprite(new Point(235,230), "montapedacos_braco_esquerdo.gif", Mapping.getMapping(new Point(663, 265)));
		this.createSprite(new Point(274,230), "montapedacos_perna_direita.gif", Mapping.getMapping(new Point(655, 364)));
		this.createSprite(new Point(315,230), "montapedacos_perna_esquerda.gif", Mapping.getMapping(new Point(625, 364)));
		this.spriteManager.setSnapToDestination(true);
		this.getSpriteManager().setSpriteHoverEnabled(true);

	}

	@Override
	public String getImagesPackageName() {
		return IMAGES_PACKAGE;
	}
}
