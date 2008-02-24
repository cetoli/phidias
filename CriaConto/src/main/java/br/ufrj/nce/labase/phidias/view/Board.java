package br.ufrj.nce.labase.phidias.view;

import java.applet.Applet;

import baklava.Playfield;
import baklava.Sprite;
import br.ufrj.nce.criaconto.images.Images;

public class Board extends Playfield {
	
	private static final long serialVersionUID = 1L;
	private Sprite backgroundImage;

	public Board(Applet applet, int width, int height, String image) {
		super(applet, width, height);
		setBackgroundImage(image);		
	}
	
	public void setBackgroundImage(String backgroundImageName) {
		if (backgroundImage != null) {
			removeSpriteFromList(backgroundImage);
			removeSpritesFromListNow();
		}
		
		backgroundImage = new Sprite(this);
    	backgroundImage.setBackground(true);
    	backgroundImage.setRectangular(true);
    	backgroundImage.setImage(Images.createImage(backgroundImageName));
	}
}
