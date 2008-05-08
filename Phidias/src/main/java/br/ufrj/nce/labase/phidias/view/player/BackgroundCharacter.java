package br.ufrj.nce.labase.phidias.view.player;

import java.awt.Event;
import java.awt.Image;


public class BackgroundCharacter extends Character {
	
	public BackgroundCharacter(Board p, Image image, String name, int x, int y) {
		super(p, image, name, x, y);
	}
	
	public BackgroundCharacter(Board p, Image image, String name, int x, int y, int toolBarX, int toolBarY) {
		super(p, image, name, x, y, toolBarX, toolBarY);
	}
	
	public void mouseDrag(Event evt, int x, int y) {
		setX(originalX);
		setY(originalY);
	}
	
	public void collisionWith(Piece s) { 
	}
}
