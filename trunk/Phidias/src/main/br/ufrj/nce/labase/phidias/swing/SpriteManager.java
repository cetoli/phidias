package br.ufrj.nce.labase.phidias.swing;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SpriteManager {
	List<Sprite> sprites = new LinkedList<Sprite>();
	private Sprite currentSprite;
	
	/**
	 * Special sprite type, used to represent Non Playable Caracteres, responsible to print messages on screen
	 * on an image.
	 */
	private NPC npc;
	
	/**
	 * Sets Sprite Brightenning hover behavior true/false. When the mouse moves over a Sprite,
	 * if this attribute is set to true, it is renderer with a filter that makes it brighter
	 * than the original image, highlighting the current sprite among others.
	 */
	private boolean spriteHoverEnabled = false;
	
	/**
	 * Adds Sprite to be managed by this instance.
	 * A backward link is set on sprite instance too.
	 * @param sprite
	 */
	public void addSprite(Sprite sprite) {
		sprite.setSpriteManager(this);
		this.sprites.add(sprite);
	}

	public void removeSprite(Sprite sprite) {
		this.sprites.remove(sprite);
	}

	/**
	 * Finds and returns the sprite instance which area corresponds to the specified coordinates.
	 * If no sprites are found for those coordinates, null is returned.
	 * @param x
	 * @param y
	 * @return
	 */
	public Sprite findSprite(int x, int y) {
		// handles npc dragging
		if (npc != null && npc.isVisible() && npc.getBody().contains(x, y)){
			return npc;
		}
		for (Sprite sprite : sprites) {
			if (sprite.isVisible() && sprite.getBody().contains(x, y))
				return sprite;
		}

		return null;
	}

	/**
	 * Calls mouseDragged() event to the currentSprite instance, generally set by mousePressed() event.
	 * @param e
	 */
	public void mouseDragged(MouseEvent e) {
		if(currentSprite == null)
			this.currentSprite = findSprite(e.getX(), e.getY());

		if (this.currentSprite != null)
			this.currentSprite.mouseDragged(e);
	}

	public void mouseExited(MouseEvent e) {
		// Mouse leaves sprite area. CurrentSprite was defined by mouseEntered() event.
		//TODO: validate this logic. What if one sprite is over another? How this implementation would actuate? 
		if (this.currentSprite != null){
			this.currentSprite.mouseExited(e);
			this.currentSprite = null;
		}
	}

	public void mouseMoved(MouseEvent e) {
		Sprite sprite = findSprite(e.getX(), e.getY());
		if (sprite != null)
			sprite.mouseMoved(e);
	}

	public void mouseEntered(MouseEvent e) {
		//TODO: validate this logic over sprites that overlap
		this.currentSprite = findSprite(e.getX(), e.getY());
		if (currentSprite != null){
			currentSprite.mouseEntered(e);
		}
	}

	public void mouseClicked(MouseEvent e) {
		Sprite sprite = findSprite(e.getX(), e.getY());
		if (sprite != null)
			sprite.mouseClicked(e);
	}

	/**
	 * Sets currentSprite instance back to null, after calling its corresponding mousereleased() method,
	 * currentSprite isntance was set by mousePressed() event.
	 * @param e
	 */
	public void mouseReleased(MouseEvent e) {
		if (this.currentSprite != null){
			currentSprite.mouseReleased(e);
			this.currentSprite = null;
		}
	}

	/**
	 * Sets currentSprite to the sprite instance that which area corresponds to the coordinates where 
	 * the mouse event has occured.
	 * This is due to optimize performance avoiding to loop through sprite list in every mouse event, as mouseDragged()
	 * events specially.
	 * @param e
	 */
	public void mousePressed(MouseEvent e) {
		this.currentSprite = findSprite(e.getX(), e.getY());
		if (this.currentSprite != null)
			this.currentSprite.mousePressed(e);
	}

	public List<Sprite> spritesCollided(Sprite sprite) {
		List<Sprite> spritesCollided = new ArrayList<Sprite>();
		for (Sprite spriteAux : this.sprites) {
			if (!sprite.equals(spriteAux) && spriteAux.getBody().intersects(sprite.getBody()))
				spritesCollided.add(spriteAux);
		}

		if (spritesCollided.size() > 0)
			return spritesCollided;

		return null;
	}

	public void paintSprites(Graphics graphics, ImageObserver imgObserver) {
		for (Sprite spriteAux : sprites) {
			if (spriteAux.isVisible()){
				paintSprite(spriteAux, graphics, imgObserver);
			}
		}
		
		if (this.npc != null && this.npc.isVisible()){
			paintSprite(npc, graphics, imgObserver);
		}

		// used to simulate bringing sprite being dragged to the top level layer
		if (currentSprite != null)
			paintSprite(currentSprite, graphics, imgObserver);
	}
	
	private void paintSprite(Sprite sprite, Graphics g, ImageObserver imgObserver){
		g.drawImage(sprite.getImage(), (int) sprite.getPosX(), (int) sprite.getPosY(), imgObserver);
	}
	
	public void npcSayText(String text){
		this.npc.sayText(text);
	}
	
	public void hideNpc(){
		this.npc.setVisible(false);
	}

	public List<Sprite> getSprites() {
		return sprites;
	}
	
	public void setDisableDragAfterHit(boolean disableDragAfterHit) {
		for (Sprite spriteAux : sprites) {
			spriteAux.setDisableDragAfterHit(disableDragAfterHit);
		}
	}

	public void setSnapToDestination(boolean snapToDestination) {
		for (Sprite spriteAux : sprites) {
			spriteAux.setSnapToDestination(snapToDestination);
		}
	}
	
	public void setDragEnabled(boolean isDragEnabled){
		for (Sprite spriteAux : sprites) {
			spriteAux.setDragEnabled(isDragEnabled);
		}
	}

	public NPC getNpc() {
		return npc;
	}

	public void setNpc(NPC npc) {
		this.npc = npc;
	}
	
	
}
