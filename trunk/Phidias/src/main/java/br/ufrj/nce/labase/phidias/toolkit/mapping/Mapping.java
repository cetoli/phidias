package br.ufrj.nce.labase.phidias.toolkit.mapping;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import br.ufrj.nce.labase.phidias.toolkit.sprite.Sprite;

/**
 * Classe responsible for defining mapping properties for a Sprite instance. Mappings are used to specify
 * the behavior of a sprite in a specified location, ie an area or a point.
 * When a mouseReleased event occur, the mapping associated to the sprite instance (if there is one) is asked
 * if the coordinates where the sprite was moved to corresponds to those from the mouse event. If so, 
 * there is the possibility that the sprite must be moved to a specified position, defined by spriteDestination,
 * according to the game rule.
 * Subclasses may extend its funcionality by implementing abstract methods.
 * @author Diogo Gomes
 *
 */
public abstract class Mapping {

	/**
	 * Backward reference to sprite instance being mapped. The sprite has also a reference to this
	 * mapping instance.
	 */
	private Sprite sprite;
	
	public static Mapping getMapping(Point2D imageCenter){
		return new PointMapping(imageCenter);
	}
	
	public static AreaMapping getMapping(Rectangle2D area, Point2D spriteDestination) {
		return new AreaMapping(area, spriteDestination);
	}
	
	public static AreaMapping getMapping(Rectangle2D area) {
		return new AreaMapping(area);
	}
	
	public abstract boolean intersect();

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public abstract Point2D getSpriteDestination();

	public abstract void setSpriteDestination(Point2D spriteDestination);
	
}
