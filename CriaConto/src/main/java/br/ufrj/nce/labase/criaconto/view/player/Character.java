package br.ufrj.nce.labase.criaconto.view.player;

import java.awt.Event;
import java.awt.Image;

import baklava.Sprite;
import br.ufrj.nce.labase.criaconto.control.Controller;
import br.ufrj.nce.labase.phidias.view.Board;
import br.ufrj.nce.labase.phidias.view.Piece;

public class Character extends Piece {
	protected boolean onScene = false;
	protected Piece collidedPiece;
	
	public Character(Board b, Image image, String name, int x, int y) {
		super(b, image, name, x, y);
		setEdgeHandling(Sprite.edgeSolid);
	}
	
	public void mouseUp(Event evt, int x, int y) {
		super.mouseUp(evt, x, y);
		
		if (evt.y > 590 || evt.x > 850) {
			if (!onScene) {
				Controller.registerGiveUpEvent(getMoveTime(), getName());				
			} else {
				Controller.registerTakeFromSceneEvent(getMoveTime(), getName());	
			}
			
			setX(originalX);
			setY(originalY);
		} else {
			if (!onScene) {
				Controller.registerPutOnSceneEvent(getMoveTime(), getName());	
				onScene = true;
			} else {
				Controller.registerMoveOnSceneEvent(getMoveTime(), getName());					
			}
		}
	}
	
	public void collisionWith(Piece s) {  
		if (collidedPiece == null || collidedPiece != s) {
			collidedPiece = s;
		}
		Controller.registerCollisionEvent(getName(), ((Piece) s).getName());	
	}
}
