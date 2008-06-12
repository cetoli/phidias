package br.ufrj.nce.labase.phidias.toolkit.mapping;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;


/**
 * 
 * @author Diogo Gomes
 *
 */
public class AreaMapping extends Mapping{

	/**
	 * Area where the sprite is expected to be dragged into.
	 */
	private Rectangle2D area;
	
	/**
	 * This attribute is used to define a place where a sprite must be moved to, in case of method
	 * intersect() return true after a mouseReleased event.<br>
	 * If this value wasn't explicitly defined by user, it assumes that the point defined as
	 * initial upper-left corner of rectangel area.
	 */
	private Point2D spriteDestination;
	
	public AreaMapping(Rectangle2D area, Point2D spriteDestiantion) {
		this.spriteDestination = spriteDestiantion;
		this.area = area;
	}

	public AreaMapping(Rectangle2D area) {
		this.area = area;
	}
	
	@Override
	/**
	 * This implementation considers to be valid for mapping the intersection for two rectangle areas.
	 * If the implementation deserves to consider valid only if the whole image is inside area, so
	 * it must override intersect() method of Mapping class, using the method contains() of Rectangle2D class.
	 * <code>this.area.contains(this.getSprite().getBody()</code>
	 */
	public boolean intersect() {
		return this.area.intersects(this.getSprite().getBody());
	}

	@Override
	public Point2D getSpriteDestination() {
		if (spriteDestination != null){
			return this.spriteDestination;
		}
		
		return new Point((int)area.getX(), (int)area.getY());
	}

	@Override
	public void setSpriteDestination(Point2D spriteDestination) {
		this.spriteDestination = spriteDestination;
	}

}
