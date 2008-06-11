package br.ufrj.nce.labase.phidias.toolkit.sprite;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import br.ufrj.nce.labase.phidias.toolkit.GameBoard;
import br.ufrj.nce.labase.phidias.toolkit.filter.GraphicFilter;
import br.ufrj.nce.labase.phidias.toolkit.graphic.GraphicPrintElement;
import br.ufrj.nce.labase.phidias.toolkit.sprite.event.ActionButton;

/**
 * Base implementation for Sprite managing, that handles mouse events and
 * sprites behavior.<br>
 * A custom implementation of a Sprite Manager may be implemented, by
 * subclassing this class and overriding appropriate methods. Custom
 * SpriteManagers must be defined in GameBoard subclasses, in initGame() method,
 * by calling setSpriteManager() method.
 * 
 * @author Diogo Gomes
 * @author Andre Moraes
 */
public class SpriteManager {

	/**
	 * List with Sprite intances to be used by this game. This list is read from
	 * the end to the beginnig when the sprites are printed on screen, so that
	 * the last sprite printed remains on top of other. When application looks
	 * for a sprite in a specified screen location, the list is read from the
	 * beginning to the end, so that the first sprite (on top of others) would
	 * be returned. When a sprite is clicked (on mousePressed event), it is
	 * moved to the beginning of the list.
	 */
	List<Sprite> sprites = new LinkedList<Sprite>();
	
	private GameBoard gameBoard;
	
	private Sprite currentSprite;

	/**
	 * Current hovered sprite, used to implement hover behavior.
	 */
	private Sprite hoveredSprite;

	private List<GraphicPrintElement> graphicPrintElement;

	private List<ActionButton> actionButtons;

	/**
	 * Special sprite type, used to represent Non Playable Characters,
	 * responsible to print messages on screen on an image.
	 */
	private NPC npc;

	/**
	 * Sets Sprite Brightening hover behavior true/false. When the mouse moves
	 * over a Sprite, if this attribute is set to true, it is renderer with a
	 * filter that makes it brighter than the original image, highlighting the
	 * current sprite among others.
	 */
	private boolean spriteHoverEnabled = false;

	/**
	 * Adds Sprite to be managed by this instance.
	 * 
	 * @param sprite
	 */
	public void addSprite(Sprite sprite) {
		this.sprites.add(sprite);
	}

	public void removeSprite(Sprite sprite) {
		this.sprites.remove(sprite);
	}

	/**
	 * Finds and returns the sprite instance which area corresponds to the
	 * specified coordinates. If no sprites are found for those coordinates,
	 * null is returned.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Sprite findSprite(int x, int y) {
		// handles npc dragging
		if (npc != null && npc.isVisible() && npc.getBody().contains(x, y)) {
			return npc;
		}

		// handle action buttons
		if (actionButtons != null) {
			for (ActionButton button : actionButtons) {
				if (button.isVisible() && button.getBody().contains(x, y))
					return button;
			}
		}
		
		for (Sprite sprite : sprites) {
			if (sprite.isVisible() && sprite.getBody().contains(x, y))
				return sprite;
		}

		return null;
	}

	/**
	 * Calls mouseDragged() event to the currentSprite instance, generally set
	 * by mousePressed() event.
	 * 
	 * @param e
	 */
	public void mouseDragged(MouseEvent e) {
		if (currentSprite == null)
			this.currentSprite = findSprite(e.getX(), e.getY());

		if (this.currentSprite != null)
			this.currentSprite.mouseDragged(e);
	}

	public void mouseExited(MouseEvent e) {
		// Mouse leaves sprite area. CurrentSprite was defined by mouseEntered()
		// event.
		// TODO: validate this logic. What if one sprite is over another? How
		// this implementation would actuate?
		if (this.currentSprite != null) {
			this.currentSprite.mouseExited(e);
			this.currentSprite = null;
		}
	}

	public void mouseMoved(MouseEvent e) {
		this.hoveredSprite = findSprite(e.getX(), e.getY());

		if (this.hoveredSprite != null)
			this.hoveredSprite.mouseMoved(e);
	}

	public void mouseEntered(MouseEvent e) {
		// TODO: validate this logic over sprites that overlap
		this.currentSprite = findSprite(e.getX(), e.getY());
		if (currentSprite != null) {
			currentSprite.mouseEntered(e);
		}
	}

	public void mouseClicked(MouseEvent e) {
		Sprite sprite = findSprite(e.getX(), e.getY());
		if (sprite != null)
			sprite.mouseClicked(e);
	}

	/**
	 * Sets currentSprite instance back to null, after calling its corresponding
	 * mousereleased() method, currentSprite isntance was set by mousePressed()
	 * event.
	 * 
	 * @param e
	 */
	public void mouseReleased(MouseEvent e) {
		if (this.currentSprite != null) {
			currentSprite.mouseReleased(e);
			this.currentSprite = null;
		}
	}

	/**
	 * Sets currentSprite to the sprite instance that which area corresponds to
	 * the coordinates where the mouse event has occured. This is due to
	 * optimize performance avoiding to loop through sprite list in every mouse
	 * event, as mouseDragged() events specially. When a sprite location
	 * corresponds to the area where mousPressed event occured, it is moved to
	 * the beginning of the sprites list.
	 * 
	 * @param e
	 */
	public void mousePressed(MouseEvent e) {
		this.currentSprite = findSprite(e.getX(), e.getY());
		// Sprites are only ment to react to mouse events (such as moving to the beginig o the list)
		// if their dragEnabled property is enabled.
		if (this.currentSprite != null && this.currentSprite.isDragEnabled()==true){
			this.currentSprite.mousePressed(e);
			// moving to the beginning of the list
			this.sprites.remove(currentSprite);
			this.sprites.add(0, currentSprite);
		}
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

	/**
	 * Method for printing sprites on screen. To render truly layered sprites,
	 * the list is drawn from the end to the beginning, so that the last sprite
	 * printed remains on the top of others on screen.
	 * 
	 * @param graphics
	 * @param imgObserver
	 */
	public void paintSprites(Graphics graphics, ImageObserver imgObserver) {
		// reverse reading of sprites list
		Sprite sprite;
		for (ListIterator<Sprite> iterator = this.sprites.listIterator(sprites.size()); iterator.hasPrevious();) {
			sprite = iterator.previous();
			if (sprite.isVisible()) {
				paintSprite(sprite, graphics, imgObserver);
			}
		}

		if (this.npc != null && this.npc.isVisible()) {
			paintSprite(npc, graphics, imgObserver);
		}

		if (this.actionButtons != null) {
			for (ActionButton button: actionButtons){
				paintSprite(button, graphics, imgObserver);
			}
		}
		
		if (this.spriteHoverEnabled && this.hoveredSprite != null) {
			BufferedImage image = hoveredSprite.getImage();
			for (GraphicFilter graphicFilter : this.hoveredSprite.getHoverFilters()) {
				image = graphicFilter.filter(image);
			}
			if (image != null)
				graphics.drawImage(image, (int) hoveredSprite.getPosX(), (int) hoveredSprite.getPosY(), imgObserver);
		}
	}

	/**
	 * Method for printing graphic elements on screen.
	 * 
	 * @param graphics
	 */
	public void paintGraphicElements(Graphics graphics) {
		if (this.graphicPrintElement != null){
			for (GraphicPrintElement element : this.graphicPrintElement) {
				element.print((Graphics2D) graphics);
			}
		}
	}

	private void paintSprite(Sprite sprite, Graphics g, ImageObserver imgObserver) {
		g.drawImage(sprite.getImage(), (int) sprite.getPosX(), (int) sprite.getPosY(), imgObserver);
	}

	public void npcSayText(String text) {
		this.npc.sayText(text);
	}

	public void hideNpc() {
		this.npc.setVisible(false);
	}

	public void showNpc() {
		this.npc.setVisible(true);
	}

	public List<Sprite> getSprites() {
		return sprites;
	}

	/**
	 * Sets if the sprite must be disabled from attending mouse events after a
	 * drag event that receives a true response from this corresponting mapping.
	 */
	public void setDisableDragAfterHit(boolean disableDragAfterHit) {
		for (Sprite spriteAux : sprites) {
			spriteAux.setDisableDragAfterHit(disableDragAfterHit);
		}
	}

	/**
	 * Sets if the sprite must be moved to its Mapped destination, after a
	 * mousReleased event that returns a true value from its corresponding
	 * Mapping instance.
	 */
	public void setSnapToDestination(boolean snapToDestination) {
		for (Sprite spriteAux : sprites) {
			spriteAux.setSnapToDestination(snapToDestination);
		}
	}

	public void setDragEnabled(boolean isDragEnabled) {
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

	public boolean isSpriteHoverEnabled() {
		return spriteHoverEnabled;
	}

	public void setSpriteHoverEnabled(boolean spriteHoverEnabled) {
		this.spriteHoverEnabled = spriteHoverEnabled;
	}

	public List<GraphicPrintElement> getGraphicPrintElement() {
		return graphicPrintElement;
	}

	public void setGraphicPrintElement(List<GraphicPrintElement> obstacules) {
		this.graphicPrintElement = obstacules;
	}

	public boolean addGraphicPrintElement(GraphicPrintElement o) {
		if (graphicPrintElement == null)
			graphicPrintElement = new ArrayList<GraphicPrintElement>();

		return graphicPrintElement.add(o);
	}

	public boolean addActionButton(ActionButton actionButton) {
		if (actionButtons == null)
			actionButtons = new ArrayList<ActionButton>();
		
		return actionButtons.add(actionButton);
	}
	
	public Sprite getCurrentSprite() {
		return currentSprite;
	}

	public SpriteManager(GameBoard gameboard) {
		super();
		this.gameBoard = gameboard;
	}

	public GameBoard getGameBoard() {
		return gameBoard;
	}
}
