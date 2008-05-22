package br.ufrj.nce.labase.attention.castelo;

import java.awt.Point;
import java.awt.geom.Point2D;

import br.ufrj.nce.labase.attention.PickingGameBoard;
import br.ufrj.nce.labase.phidias.swing.Sprite;
import br.ufrj.nce.labase.phidias.swing.images.Images;

public class CasteloGameBoard extends PickingGameBoard {

	
	public void init(){
		this.setBackgroundImage(Images.createImage("castelo.gif"));

		this.addSprite(new Sprite(new Point(45,150), "peca1.gif"));
		this.addSprite(new Sprite(new Point(15,200), "peca2.gif"));
		this.addSprite(new Sprite(new Point(65,210), "peca3.gif"));
		this.addSprite(new Sprite(new Point(25,290), "peca4.gif"));
		this.addSprite(new Sprite(new Point(120,270), "peca5.gif"));
		this.addSprite(new Sprite(new Point(250,30), "peca6.gif"));
		this.addSprite(new Sprite(new Point(150,330), "peca7.gif"));
		this.addSprite(new Sprite(new Point(160,170), "peca8.gif"));
		this.addSprite(new Sprite(new Point(210,250), "peca9.gif"));
		this.addSprite(new Sprite(new Point(230,100), "peca10.gif"));
		this.addSprite(new Sprite(new Point(370,250), "peca11.gif"));
		this.addSprite(new Sprite(new Point(470,290), "peca12.gif"));
		this.addSprite(new Sprite(new Point(430,230), "peca13.gif"));
		this.addSprite(new Sprite(new Point(420,130), "peca14.gif"));
		this.addSprite(new Sprite(new Point(290,280), "peca15.gif"));
		this.addSprite(new Sprite(new Point(280,200), "peca16.gif"));
		this.addSprite(new Sprite(new Point(320,140), "peca16.gif"));

		super.init();

	}
	
	@Override
	protected Point2D getImageCoordinate() {
		return new Point(600, 20);
	}

}
