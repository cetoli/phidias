package br.ufrj.nce.labase.phidias.swing;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;

import br.ufrj.nce.labase.phidias.swing.Mapping;
import br.ufrj.nce.labase.phidias.util.Images;

/**
 * Sprites, by default, implement facilities for drag events.
 * If some instance may not be dragged, or might implement its own dragging rules, so it must override
 * <code>mousePressed</code>, <code>mouseDragged</code> and <code>mouseReleased</code> methods, or
 * in simpler cases, just call setDragEnabled(false).
 *  
 * @author Andre Moraes
 * @author Diogo Gomes
 *
 */
public class Sprite {

	private static final int DEFAULT_SIZE = 5;

	private BufferedImage image;

	/**
	 * Current position for Sprite instance
	 */
	private Point2D coordinate;
	
	/**
	 * Original instance position, before any drag events. This coordinate is used to return the Sprite
	 * back to its original position in cases that the drag event is not valid, according to business rules.
	 */
	private Point2D origin;

	/**
	 * Mapping associated to this instance. Mapping represents a Rectangle area into where drag events
	 * are valid to this Sprite instance. In case of null mapping object, it means that there are no rules
	 * defined for mapping, and any area will permit an object to be placed in.<br>
	 * When mapping validations return false to sprite instance, it will be then return to its original position.
	 */
	private Mapping mapping;
	
	private boolean dragEnabled;
	
	private boolean visible = true;
	
	/**
	 * Sets if the sprite must be disabled from attending mouse events after a drag event that receives
	 * a true response from this corresponting mapping.
	 */
	//TODO:
	private boolean disableDragAfterHit = false;
	
	/**
	 * Sets if the sprite must be moved to its Mapped destination, after a mousReleased
	 * event that returns a true value from its corresponding Mapping instance.
	 */
	private boolean snapToDestination = false;
	
	/**
	 * Last position coordinate, used to track drag events and render the image in correct position,
	 * as mous mouves through screen.
	 * It is setted by mousePressed.
	 */
	private Point2D lastPositionBeforeDrag;
	
	private int width, height;

	private SpriteManager spriteManager;

	//TODO: verificar se é viável colocar "Mapping... mapping" no construtor, aceitando um conjunto de mapeamentos.
	public Sprite(Point2D coordinate, BufferedImage image) {
		this.coordinate = coordinate;
		this.origin 	= (Point2D) coordinate.clone();
		this.image = image;
		if (image != null) {
			width = image.getWidth();
			height = image.getHeight();
		} else {
			width = DEFAULT_SIZE;
			height = DEFAULT_SIZE;
		}
		lastPositionBeforeDrag = new Point();
		this.dragEnabled = true;
	}

	public Sprite(Point2D coordinate, BufferedImage image, Mapping mapping) {
		this(coordinate, image);
		this.setMapping(mapping);
	}

	public Sprite(Point2D coordinate, String imagePath) {
		this(coordinate, getImage(imagePath));
	}

	public Sprite(Point2D coordinate, String imagePath, Mapping mapping) {
		this(coordinate, getImage(imagePath));
		this.setMapping(mapping);
	}
	
	public Sprite(Point2D coordinate, int width, int height, String imagePath) {
		this(coordinate, getImage(imagePath));
		this.width = width;
		this.height = height;
	}

	public Sprite(Point2D coordinate, int width, int height, BufferedImage image) {
		this(coordinate, image);
		this.width = width;
		this.height = height;
	}

	public static BufferedImage getImage(String imagePath) {
		return Images.getBufferedImage(imagePath);
	}

	public Rectangle2D getBody() {
		return new Rectangle2D.Double(this.coordinate.getX(), this.coordinate.getY(), width, height);
	}

	public boolean hasCollision() {
		return (this.spriteManager.spritesCollided(this) != null);
	}

	public List<Sprite> objectsCollided() {
		return this.spriteManager.spritesCollided(this);
	}
	
 	/**
 	 * Default implementation for mouse drag events.
 	 * Changes current instance coordinates, according to drag event. New values
 	 * will be used by thread's paint() method, to render image in correct position.
 	 * Incase of particular treatment of this event is needes, this method must be overriden by subclass.
 	 * 
 	 */
 	public void mouseDragged(MouseEvent e){
 		if (this.dragEnabled){
 			this.setPosXY(e.getX() - this.lastPositionBeforeDrag.getX(),
 					e.getY() - this.lastPositionBeforeDrag.getY());		
 		}
	}

 	/**
 	 * Default implementation for mouse pressed events.
 	 * Method called by SpriteManager only for instance whose coordinates corresponds to the mouse area interation
 	 * where click occured.
 	 */
 	public void mousePressed(MouseEvent e){
		this.lastPositionBeforeDrag.setLocation(e.getX() - this.getPosX(),
												e.getY() - this.getPosY());
 	}
 	
 	/**
 	 * Default implementation for mouse Released events.
 	 * @param e
 	 */
 	public void mouseReleased(MouseEvent e){
 		// actions only when a mapping is defined
 		if (this.dragEnabled && this.mapping != null){
 			if (this.mapping.intersect()){
 				
 				System.out.println("Intersect");
 				
 				if (this.disableDragAfterHit){
 					this.dragEnabled = false;
 				}
 				if (this.snapToDestination){
 					this.coordinate.setLocation(mapping.getSpriteDestination());
 	 				System.out.println("SnapToDestination?"+snapToDestination);
 				}
 			}else {
 				this.resetCoordinates();
 			}
 		}
 	}
 	
 	/**
 	 * Retorna a instância Piece para seu local de origem.
 	 */
 	private void resetCoordinates(){
 		this.coordinate.setLocation(this.origin);
 	}


 	/**
 	 * Default empty implementation. Must be overriden by subclasses.
 	 * @param e
 	 */
 	public void mouseExited(MouseEvent e){
		
	}

 	/**
 	 * Default empty implementation. Must be overriden by subclasses.
 	 * @param e
 	 */
	public void mouseMoved(MouseEvent e){
		
	}

 	/**
 	 * Default empty implementation. Must be overriden by subclasses.
 	 * @param e
 	 */
	public void mouseEntered(MouseEvent e){
		
	}

 	/**
 	 * Default empty implementation. Must be overriden by subclasses.
 	 * @param e
 	 */
	public void mouseClicked(MouseEvent e){
		
	}

	public Point2D getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Point2D coordinate) {
		this.coordinate = coordinate;
	}

	public Point2D getOrigin() {
		return origin;
	}

	public void setOrigin(Point2D origin) {
		this.origin = origin;
	}

	public Mapping getMapping() {
		return mapping;
	}

	public void setMapping(Mapping mapping) {
		this.mapping = mapping;
		/* backward referente in mapping instance, pointing to this sprite instance */
		mapping.setSprite(this);
	}

	public boolean isDragEnabled() {
		return dragEnabled;
	}

	public void setDragEnabled(boolean dragEnabled) {
		this.dragEnabled = dragEnabled;
	}

	public boolean isDisableDragAfterHit() {
		return disableDragAfterHit;
	}

	public void setDisableDragAfterHit(boolean disableDragAfterHit) {
		this.disableDragAfterHit = disableDragAfterHit;
	}

	public boolean isSnapToDestination() {
		return snapToDestination;
	}

	/**
	 * Sets if the sprite must be moved to its Mapped destination, after a mousReleased
	 * event that returns a true value from its corresponding Mapping instance.
	 */
	public void setSnapToDestination(boolean snapToDestination) {
		this.snapToDestination = snapToDestination;
	}

	public BufferedImage getImage(){
		return this.image;
	}
	
	public double getPosX() {
		return coordinate.getX();
	}

	public double getPosY() {
		return coordinate.getY();
	}

	public void setPosXY(double x, double y){
		this.coordinate.setLocation(x, y);
	}
	
	public SpriteManager getSpriteManager() {
		return spriteManager;
	}

	public void setSpriteManager(SpriteManager spriteManager) {
		this.spriteManager = spriteManager;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	
}
