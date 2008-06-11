package br.ufrj.nce.labase.attention.dinossauros;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;

import br.ufrj.nce.labase.phidias.toolkit.PickingGameBoard;

public class DinossaurosGameBoard extends PickingGameBoard{

	private static final String IMAGES_PACKAGE = "br.ufrj.nce.labase.attention.dinossauros.images"; 
	
	public void initPickingGame(){
		this.setBackgroundImage("fundo.gif");

		this.createSprite(new Point(35,65), "i1.gif");
		this.createSprite(new Point(109,45), "i2.gif");
		this.createSprite(new Point(223,35), "i3.gif");
		this.createSprite(new Point(389,69), "i4.gif");
		this.createSprite(new Point(300,70), "i5.gif");
		this.createSprite(new Point(155,130), "i6.gif");
		this.createSprite(new Point(95,115), "i7.gif");
		this.createSprite(new Point(27,135), "i8.gif");
		this.createSprite(new Point(110,250), "i9.gif");
		this.createSprite(new Point(115,340), "i10.gif");
		this.createSprite(new Point(40,280), "i11.gif");
		this.createSprite(new Point(25,320), "i12.gif");
		this.createSprite(new Point(235,340), "i13.gif");
		this.createSprite(new Point(340,350), "i14.gif");
		this.createSprite(new Point(365,275), "i15.gif");
		this.createSprite(new Point(306,273), "i16.gif");
		this.createSprite(new Point(268,200), "i17.gif");
		this.createSprite(new Point(202,213), "i18.gif");
		this.createSprite(new Point(370,180), "i19.gif");
		this.createSprite(new Point(410,120), "i20.gif");
		this.createSprite(new Point(23,194), "i21.gif");
		this.createSprite(new Point(195,300), "i22.gif");
		this.createSprite(new Point(130,195), "i23.gif");
		this.createSprite(new Point(134,99), "i24.gif");
		this.createSprite(new Point(182,194), "i25.gif");
	}
	
	@Override
	protected Point2D getImageCoordinate() {
		return new Point(600, 20);
	}

	@Override
	public String getImagesPackageName() {
		return IMAGES_PACKAGE;
	}

	@Override
	public int getScreenHeight() {
		return 600;
	}

	@Override
	public int getScreenWidth() {
		return 800;
	}

	@Override
	public void handlePhaseFive() {
	}

	@Override
	public void handlePhaseFour() {
	}

	@Override
	public void handlePhaseOne() {
	}

	@Override
	public void handlePhaseSeven() {
	}

	@Override
	public void handlePhaseSix() {
	}

	@Override
	public void handlePhaseThree() {
	}

	@Override
	public void handlePhaseTwo() {
	}

	@Override
	public void paintGameBoard(Graphics g) {
	}

	
}
