package br.ufrj.nce.labase.criaconto.view.player;

import java.awt.Event;
import java.awt.Image;

import baklava.Sprite;
import br.ufrj.nce.labase.criaconto.control.Controller;
import br.ufrj.nce.labase.phidias.exception.PhidiasException;
import br.ufrj.nce.labase.phidias.view.Board;
import br.ufrj.nce.labase.phidias.view.Piece;

public class ScenicItem extends Piece {
	private boolean onScene = false;
	protected Image bigImage = null;
	protected int bigImageX = 0;
	protected int bigImageY = 0;
	
	public ScenicItem(Board b, Image image, String name, int x, int y) {
		super(b, image, name, x, y);
		setEdgeHandling(Sprite.edgeSolid);
	}
	
	public ScenicItem(Board b, Image image, String name, Image bigImage, int x, int y, int bigImageX, int bigImageY) {
		super(b, image, name, x, y);
		setEdgeHandling(Sprite.edgeSolid);
		this.bigImage = bigImage;
		this.bigImageX = bigImageX;
		this.bigImageY = bigImageY;
	}
		
	public void mouseUp(Event evt, int x, int y) {
		super.mouseUp(evt, x, y);
		
		if (evt.x == downX && evt.y == downY) {
			//clicou
			//setar no playfield que um item foi selecionado
			getPlayfield().setSelectedSprite(this);
		} else {
			if (evt.y < 590 && evt.x < 850) {	
				if (isOnScene()) {
					try {
						Controller.registerMoveOnSceneEvent(getMoveTime(), getName());				
					} catch (PhidiasException ex) {
						//TODO: melhorar tratamento de excecao
						ex.printStackTrace();
					}
				}
			}
		}
	}
	
	public void mouseDrag(Event evt, int x, int y) {
		if (isOnScene()) {
			setX(evt.x - downX);
			setY(evt.y - downY);
		}
	}
	
	public void putOnBoard() {
		if (!isOnScene()) {
			ScenicItem item = new ScenicItem(getPlayfield(), bigImage, getName(), bigImageX, bigImageY);
			item.setBackground(false);
			item.setRectangular(false);
			item.setOnScene(true);
			getPlayfield().removeSpriteFromList(this);
		
			try {
				Controller.registerPutOnSceneEvent(getMoveTime(), getName());	
			} catch (PhidiasException ex) {
				//TODO: melhorar tratamento de excecao
				ex.printStackTrace();
			}
		}
	}

	public void setOnScene(boolean onScene) {
		this.onScene = onScene;
	}

	public boolean isOnScene() {
		return onScene;
	}
}
