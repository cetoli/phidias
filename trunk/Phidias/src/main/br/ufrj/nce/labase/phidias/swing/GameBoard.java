
package br.ufrj.nce.labase.phidias.swing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JApplet;
import javax.swing.Timer;

import br.ufrj.nce.labase.phidias.util.Images;

/**
 * Base class that implements basic facilities for a game board.<br>
 * This facilities corresponds to mouse events methods implementation, such as drag and click.
 * Subclasses may extend particular caracteristics by overriding specific methods.
 * 
 * @author Diogo Gomes
 * @author Andre Moraes
 * 
 */
public abstract class GameBoard extends JApplet implements Runnable,MouseListener,MouseMotionListener {

	protected Image backgroundImage;

	protected SpriteManager spriteManager;
	
	private List<GraphicPrintable> graphicPrintableElements;
	
	/**
	 *  Object to be used to write with, instead of the standard screen graphics. (Double Buffering) 
	 */
    private Graphics bufferGraphics;

    /**
     *  Image that will contain everything that has been drawn on bufferGraphics.
     */
    private Image offscreen; 
    
    /**
     * Attribute used to store the package where image files may be found. This is a fully
     * qualified package name, starting with "/", that denotes an absolute path.
     * Each subclass must implement the method getImagesPackageName() to specify where
     * its image resorces are.
     */
    private String imagesPackageName;
	
	//TODO: implement phase
	private int phase;
	private Timer phaseTimer;
	
	private int screenWidth = 800;
	
	private int screenHeight = 600;
	
	public GameBoard(){
		this.spriteManager = new SpriteManager();
		graphicPrintableElements = new ArrayList<GraphicPrintable>();
	}
	
	public void init(){
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		/* implementing double buffering logic */
		offscreen = createImage(screenWidth,screenHeight); 
		bufferGraphics = offscreen.getGraphics();
		
//		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setSize(screenWidth, screenHeight);
		
		MediaTracker mt = new MediaTracker(this);
		if (this.backgroundImage != null) mt.addImage(backgroundImage,0);
		
		try
		{
			mt.waitForID(0);
		}
		catch (InterruptedException ie)
		{
		}
	}
	
	public void start()
	{
		Thread th = new Thread(this);
		th.start();
	}

	public void update(Graphics g)
    {
         paint(g);
    }

	
	/**
	 * Creates a Sprite instance and adds it to the applets Sprite collection.<br>
	 * This is an utility method to perform produtivity in development, but a sprite can still
	 * be created manually and added to the sprite collection with addSprite() method.
	 * @param coordinate Point2D intance determining location on screen when sprite must
	 * be first rendered into.
	 * @param imageFileName Image's file name. This name is joined with the images package
	 * specified in getImagesPackageName() abstract method. The name must be specified with its
	 * extention, eg.: "image.gif", "buttonOK.jpg"
	 */
	public void createSprite(Point2D coordinate, String imageFileName){
		this.addSprite(new Sprite(coordinate, this.getImageName(imageFileName)));
	}

	/**
	 * Creates an sprite and adds it tho the game instance collection. Detailed documentation
	 * on melhod createSprite(Point2D coordinate, String imageFileName);
	 * 
	 * @param coordinate
	 * @param imageFileName
	 * @param mapping
	 * @see GameBoard.createSprite(Point2D coordinate, String imageFileName)
	 */
	public void createSprite(Point2D coordinate, String imageFileName, Mapping mapping){
		this.addSprite(new Sprite(coordinate, this.getImageName(imageFileName), mapping));
	}
	
	public void addSprite(Sprite sprite){
		this.spriteManager.addSprite(sprite);
	}
	
	public void addGraphicPrintable(GraphicPrintable element){
		this.graphicPrintableElements.add(element);
	}
	
	public void run()
	{
		//Baixa prioridade para o Thread
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
			
		while (true)
		{
			repaint();
			try
			{
				Thread.sleep(50);
			}
			catch (InterruptedException ex){}
			
			Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		}
	}
	
	public void paint(Graphics g)
	{
		// implementing double buffering
		bufferGraphics.clearRect(0,0,screenWidth,screenHeight);
		bufferGraphics.drawImage(this.backgroundImage,0,0,this);
		this.printGraphicElements((Graphics2D) bufferGraphics);
		this.spriteManager.paintSprites(bufferGraphics, this);
		
		g.drawImage(offscreen,0,0,this); 
	}

	/**
	 * Prints all Graphic elements specified to this GameBoard.<br>
	 * Graphic elements are different from sprites, since they don't interact with
	 * mouse events. Graphic elements are alowed to manage their print logic, in order to
	 * send to screen a variety os graphic types, such as Java2D graphics, and not only images. 
	 * @param g
	 */
	private void printGraphicElements(Graphics2D g) {
		for (GraphicPrintable element: this.graphicPrintableElements){
			element.print(g);
		}
	}
	
	public String getImageName(String imageFileName){
		// caching imagesPackageName
		if (this.imagesPackageName == null){
			this.imagesPackageName = "/" + resolvePackageName(this.getImagesPackageName())+"/"; 
		}
		StringBuilder name = new StringBuilder();
		name.append(this.imagesPackageName).append(imageFileName);
		return name.toString();
	}
	
	/**
	 * Resolve package names, replacing "." for "/"
	 * @param packageName
	 * @return
	 */
	private String resolvePackageName(String packageName){
		return packageName.replace(".", "/");
	}
	
	/**
	 * Abstract method that must be overriden by subclasses, used to specify the package
	 * name where image files may be found.<br>
	 * Image package name must fe a fully qualified packed name, separated by '.' or by "/", for
	 * example: "br.nce.ufrj.labase.images" or "br/nce/ufrj/labase/images"
	 * @return String containing the package name where image files may be found.
	 */
	public abstract String getImagesPackageName();
	
	public void mousePressed(MouseEvent e) {
		this.spriteManager.mousePressed(e);
	}

	public void mouseReleased(MouseEvent e) {
		this.spriteManager.mouseReleased(e);
	}

	public void mouseDragged(MouseEvent e) {
		/* this is needed to avoid dragging the sprite outside screen area, losing it */
		if (checkScreenArea(e)){
			this.spriteManager.mouseDragged(e);
		}
	}
	
	public void npcSayText(String text){
		this.spriteManager.npcSayText(text);
	}
	
	/**
	 * Boolean method used to check if mouse event occured inside screen's area. This
	 * is needed to avoid dragging the sprite out of screen and loosing it.
	 * @param e
	 * @return
	 */
	private boolean checkScreenArea(MouseEvent e){
		return (new Rectangle2D.Double(0, 0, screenWidth, screenHeight)).contains(e.getPoint());
	}

	public void mouseMoved(MouseEvent e) {
		this.spriteManager.mouseMoved(e);
	}
	
	public void mouseClicked(MouseEvent e) {
		this.spriteManager.mouseClicked(e);
	}

	public void mouseEntered(MouseEvent e) {
		this.spriteManager.mouseEntered(e);
	}

	public void mouseExited(MouseEvent e) {
		this.spriteManager.mouseExited(e);
	}

	public List<Sprite> getsprites() {
		return spriteManager.getSprites();
	}

	public Image getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(Image backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public void setBackgroundImage(String backgroundImage) {
		this.backgroundImage = Images.createImage(getImageName(backgroundImage));
	}
	

	public SpriteManager getSpriteManager() {
		return spriteManager;
	}


	public void setSpriteManager(SpriteManager spriteManager) {
		this.spriteManager = spriteManager;
	}
	
	public void setNpc(NPC npc){
		this.spriteManager.setNpc(npc);
	}
	
}
