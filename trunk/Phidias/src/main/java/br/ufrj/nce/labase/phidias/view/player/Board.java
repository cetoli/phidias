package br.ufrj.nce.labase.phidias.view.player;

import java.applet.Applet;

import baklava.Playfield;
import baklava.Sprite;

public class Board extends Playfield {
	
	private static final long serialVersionUID = 1L;
	private Sprite backgroundImage;

	public Board(Applet applet, int width, int height, Sprite image) {
		super(applet, width, height);
		setBackgroundImage(image);		
	}
	
	public void setBackgroundImage(Sprite backgroundImage) {
		if (backgroundImage != null) {
			removeSpriteFromList(backgroundImage);
			removeSpritesFromListNow();
		}
		
		this.backgroundImage = backgroundImage;
		this.backgroundImage.setBackground(true);
		this.backgroundImage.setRectangular(true);
	}
}
