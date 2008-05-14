package br.ufrj.nce.labase.elastico;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SpriteManager {
	LinkedList<Sprite> sprites = new LinkedList<Sprite>();
	private Sprite currentSprite;
	
	public void addSprite(Sprite sprite) {
		this.sprites.add(sprite);
	}

	public void removeSprite(Sprite sprite) {
		this.sprites.remove(sprite);
	}

	public Sprite findSprite(int x, int y) {
		for (Sprite sprite : sprites) {
			if (sprite.getBody().contains(x, y))
				return sprite;
		}

		return null;
	}

	public void mouseDragged(MouseEvent e) {
		if(currentSprite == null)
			this.currentSprite = findSprite(e.getX(), e.getY());

		if (this.currentSprite != null)
			this.currentSprite.mouseDragged(e);
	}

	public void mouseExited(MouseEvent e) {
		Sprite sprite = findSprite(e.getX(), e.getY());
		if (sprite != null)
			sprite.mouseExited(e);
	}

	public void mouseMoved(MouseEvent e) {
		Sprite sprite = findSprite(e.getX(), e.getY());
		if (sprite != null)
			sprite.mouseMoved(e);
	}

	public void mouseEntered(MouseEvent e) {
		Sprite sprite = findSprite(e.getX(), e.getY());
		if (sprite != null)
			sprite.mouseEntered(e);
	}

	public void mouseClicked(MouseEvent e) {
		Sprite sprite = findSprite(e.getX(), e.getY());
		if (sprite != null)
			sprite.mouseClicked(e);
	}

	public void mouseReleased(MouseEvent e) {
		if (this.currentSprite != null)
			this.currentSprite = null;
		
		Sprite sprite = findSprite(e.getX(), e.getY());
		if (sprite != null)
			sprite.mouseReleased(e);
	}

	public void mousePressed(MouseEvent e) {
		Sprite sprite = findSprite(e.getX(), e.getY());
		if (sprite != null)
			sprite.mousePressed(e);
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

	public void paintSprites(Graphics g, ImageObserver imgObserver) {
		for (Sprite spriteAux : sprites) {
			g.drawImage(spriteAux.getImage(), (int) spriteAux.getPosX(), (int) spriteAux.getPosY(), imgObserver);
		}
	}
}
