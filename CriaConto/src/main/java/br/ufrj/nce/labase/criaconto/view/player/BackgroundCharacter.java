package br.ufrj.nce.labase.criaconto.view.player;

import java.awt.Event;
import java.awt.Image;

import br.ufrj.nce.labase.phidias.view.Board;
import br.ufrj.nce.labase.phidias.view.Piece;

public class BackgroundCharacter extends Character {
	
	public BackgroundCharacter(Board p, Image image, String name, int x, int y) {
		super(p, image, name, x, y);
	}
	
	public void mouseDrag(Event evt, int x, int y) {
		setX(originalX);
		setY(originalY);
	}
	
	public void collisionWith(Piece s) { 
	}
}
