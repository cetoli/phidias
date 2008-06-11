package br.ufrj.nce.labase.phidias.view.player;

import java.awt.Event;
import java.awt.Image;

import baklava.Sprite;
import br.ufrj.nce.labase.phidias.controller.Controller;
import br.ufrj.nce.labase.phidias.exception.PhidiasException;

public class Character extends Piece {
	protected boolean onScene = false;
	protected Piece collidedPiece;
	protected int characterToolBarX;
	protected int characterToolBarY;
	
	public Character(Board b, Image image, String name, int x, int y) {
		super(b, image, name, x, y);
		setEdgeHandling(Sprite.edgeSolid);
		setBackground(false);
	}
	
	public Character(Board b, Image image, String name, int x, int y, int toolBarX, int toolBarY) {
		super(b, image, name, x, y);
		setEdgeHandling(Sprite.edgeSolid);
		setBackground(false);
		characterToolBarX = toolBarX;
		characterToolBarY = toolBarY;
	}
	
	public void mouseUp(Event evt, int x, int y) {
		super.mouseUp(evt, x, y);
		
		try {
			if (evt.y > characterToolBarY || evt.x > characterToolBarX) {
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
		} catch (PhidiasException ex) {
			//TODO: melhorar tratamento de excecao
			ex.printStackTrace();
		}
	}
	
	public void collisionWith(Piece s) {  
		if (collidedPiece == null || collidedPiece != s) {
			collidedPiece = s;
		}
		
		try {
			Controller.registerCollisionEvent(getName(), ((Piece) s).getName());	
		} catch (PhidiasException ex) {
			//TODO: melhorar tratamento de excecao
			ex.printStackTrace();
		}
	}	
}
