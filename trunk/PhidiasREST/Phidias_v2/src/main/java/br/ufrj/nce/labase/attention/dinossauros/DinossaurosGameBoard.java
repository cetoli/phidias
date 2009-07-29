package br.ufrj.nce.labase.attention.dinossauros;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;

import br.ufrj.nce.labase.phidias.toolkit.PickingGameBoard;

public class DinossaurosGameBoard extends PickingGameBoard{

	private static final String IMAGES_PACKAGE = "br.ufrj.nce.labase.attention.dinossauros.images"; 
	
	public void initPickingGame(){
		this.setBackgroundImage("fundo.GIF");

		this.createSprite(new Point(35,65), "i1.GIF");
		this.createSprite(new Point(109,45), "i2.GIF");
		this.createSprite(new Point(223,35), "i3.GIF");
		this.createSprite(new Point(389,69), "i4.GIF");
		this.createSprite(new Point(300,70), "i5.GIF");
		this.createSprite(new Point(155,130), "i6.GIF");
		this.createSprite(new Point(95,115), "i7.GIF");
		this.createSprite(new Point(27,135), "i8.GIF");
		this.createSprite(new Point(110,250), "i9.GIF");
		this.createSprite(new Point(115,340), "i10.GIF");
		this.createSprite(new Point(40,280), "i11.GIF");
		this.createSprite(new Point(25,320), "i12.GIF");
		this.createSprite(new Point(235,340), "i13.GIF");
		this.createSprite(new Point(340,350), "i14.GIF");
		this.createSprite(new Point(365,275), "i15.GIF");
		this.createSprite(new Point(306,273), "i16.GIF");
		this.createSprite(new Point(268,200), "i17.GIF");
		this.createSprite(new Point(202,213), "i18.GIF");
		this.createSprite(new Point(370,180), "i19.GIF");
		this.createSprite(new Point(410,120), "i20.GIF");
		this.createSprite(new Point(23,194), "i21.GIF");
		this.createSprite(new Point(195,300), "i22.GIF");
		this.createSprite(new Point(130,195), "i23.GIF");
		this.createSprite(new Point(134,99), "i24.GIF");
		this.createSprite(new Point(182,194), "i25.GIF");
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
