
package br.ufrj.nce.labase.phidias.swing;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JApplet;
import javax.swing.Timer;

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
		this.printGraphicElements(bufferGraphics);
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
	private void printGraphicElements(Graphics g) {
		for (GraphicPrintable element: this.graphicPrintableElements){
			element.print(g);
		}
	}

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


	public SpriteManager getSpriteManager() {
		return spriteManager;
	}


	public void setSpriteManager(SpriteManager spriteManager) {
		this.spriteManager = spriteManager;
	}
	
}
