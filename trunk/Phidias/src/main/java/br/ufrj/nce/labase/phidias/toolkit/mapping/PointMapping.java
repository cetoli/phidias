package br.ufrj.nce.labase.phidias.toolkit.mapping;

import java.awt.Point;
import java.awt.geom.Point2D;


public class PointMapping extends Mapping {
	
	/**
	 * Define a point representing the center coordinate into which the image is expected to be dragged.
	 * In this case, the destination area is considered to be exactly the same as the sprite area, represented
	 * by a Rectangle2D instance. This is also considered the destination coordinate where the sprite must
	 * be moved to, after a drag event.
	 */
	private Point2D imageCenter;
	
	/**
	 * This attribute is used to define a place where a sprite must be moved to, in case of method
	 * intersect() return true after a mouseReleased event.<br>
	 * If this value wasn't explicitly defined by user, it assumes that the point defined as
	 * imageCenter in constructor will be used to position the center of the image. So, the method
	 * getSpriteDestination() calculates the upper-left corner point, according to image's width and heigh
	 * relative to the imageCenter point.
	 */
	private Point2D spriteDestination;
	
	public PointMapping(Point2D imageCenter){
		this.imageCenter = imageCenter;
	}

	@Override
	/**
	 * If the mapped point is inside the image's area, the mapping check is considered to return true.
	 */
	public boolean intersect() {
		return this.getSprite().getBody().contains(imageCenter);
	}

	@Override
	public Point2D getSpriteDestination() {
		if (spriteDestination != null){
			return spriteDestination;
		}
		
		int imageWidth = this.getSprite().getImage().getWidth();
		int imageHeight = this.getSprite().getImage().getHeight();
		return new Point((int)imageCenter.getX() - (imageWidth/2), (int)imageCenter.getY() - (imageHeight/2));
	}

	@Override
	public void setSpriteDestination(Point2D spriteDestination) {
		this.spriteDestination = spriteDestination;
	}

}
