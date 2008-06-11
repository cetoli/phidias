package br.ufrj.nce.labase.phidias.toolkit;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JApplet;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;

import br.ufrj.nce.labase.phidias.toolkit.graphic.GraphicPrintElement;
import br.ufrj.nce.labase.phidias.toolkit.graphic.GraphicPrintable;
import br.ufrj.nce.labase.phidias.toolkit.mapping.Mapping;
import br.ufrj.nce.labase.phidias.toolkit.sprite.NPC;
import br.ufrj.nce.labase.phidias.toolkit.sprite.NPCTimer;
import br.ufrj.nce.labase.phidias.toolkit.sprite.Sprite;
import br.ufrj.nce.labase.phidias.toolkit.sprite.SpriteManager;
import br.ufrj.nce.labase.phidias.util.Images;

/**
 * Base class that implements basic facilities for a game board.<br>
 * This facilities corresponds to mouse events methods implementation, such as
 * drag and click. Subclasses may extend particular characteristics by
 * overriding specific methods. <br>
 * The class uses a SpriteManager as the responsible for dealing with mouse
 * events and how sprites respond to them. A base spriteManager implementation
 * is supplied, that fully handles mouse events, but custom implementations may
 * also be defined, passing a instance by the setSpriteManager() method.
 * 
 * @author Diogo Gomes
 * @author Andre Moraes
 * @author Sabrina Bettini
 * 
 */
public abstract class GameBoard extends JApplet implements Runnable, MouseInputListener {

	public static final Integer PHASE_FIVE = 5;

	public static final Integer PHASE_FOUR = 4;

	public static final Integer PHASE_ONE = 1;

	public static final Integer PHASE_SEVEN = 7;

	public static final Integer PHASE_SIX = 6;

	public static final Integer PHASE_THREE = 3;

	public static final Integer PHASE_TWO = 2;

	public Integer CURRENT_PHASE = 0;

	protected Image backgroundImage;

	/**
	 * Object to be used to write with, instead of the standard screen graphics.
	 * (Double Buffering)
	 */
	private Graphics bufferGraphics;

	private List<GraphicPrintable> graphicPrintableElements;

	/**
	 * Attribute used to store the package where image files may be found. This
	 * is a fully qualified package name, starting with "/", that denotes an
	 * absolute path. Each subclass must implement the method
	 * getImagesPackageName() to specify where its image resources are.
	 */
	private String imagesPackageName;

	/**
	 * The total seconds the NPC will be displayed on the board.
	 */
	private int npcDisplaySeconds;

	private Timer npcTimer;

	/**
	 * Image that will contain everything that has been drawn on bufferGraphics.
	 */
	private Image offscreen;

	/**
	 * Attribute used to store the current phase of the game. These phases are
	 * based in the Drive Elaboratin Theory that contains seven phases.
	 */
	private Integer phase;

	/**
	 * Attribute that enables the execution of the method enablePrintGameboard.
	 */
	private boolean enablePrintGameboard = false;

	protected SpriteManager spriteManager;

	public GameBoard() {
		this.spriteManager = new SpriteManager(this);
		graphicPrintableElements = new ArrayList<GraphicPrintable>();
	}

	public void addGraphicPrintable(GraphicPrintable element) {
		this.graphicPrintableElements.add(element);
	}

	/**
	 * Add a obstacule and adds it to the the spriteManager.
	 * 
	 * @param element
	 */
	public void addGraphicPrintElement(GraphicPrintElement element) {
		this.spriteManager.addGraphicPrintElement(element);
	}

	public void addSprite(Sprite sprite) {
		this.spriteManager.addSprite(sprite);
	}

	/**
	 * Boolean method used to check if mouse event occurred inside screen's
	 * area. This is needed to avoid dragging the sprite out of screen and
	 * loosing it.
	 * 
	 * @param e
	 * @return
	 */
	private boolean checkScreenArea(MouseEvent e) {
		return (new Rectangle2D.Double(0, 0, this.getScreenWidth(), this.getScreenHeight())).contains(e.getPoint());
	}

	/**
	 * Creates a Sprite instance and adds it to the applets Sprite collection.<br>
	 * This is an utility method to perform productivity in development, but a
	 * sprite can still be created manually and added to the sprite collection
	 * with addSprite() method.
	 * 
	 * @param coordinate
	 *            Point2D instance determining location on screen when sprite
	 *            must be first rendered into.
	 * @param imageFileName
	 *            Image's file name. This name is joined with the images package
	 *            specified in getImagesPackageName() abstract method. The name
	 *            must be specified with its extension, eg.: "image.gif",
	 *            "buttonOK.jpg"
	 */
	public Sprite createSprite(Point2D coordinate, String imageFileName) {
		Sprite sprite = new Sprite(this.spriteManager, coordinate, this.getImageName(imageFileName));
		this.addSprite(sprite);
		return sprite;
	}

	/**
	 * Creates an sprite and adds it to the game instance collection. Detailed
	 * documentation on method createSprite(Point2D coordinate, String
	 * imageFileName);
	 * 
	 * @param coordinate
	 * @param imageFileName
	 * @param mapping
	 * @see GameBoard.createSprite(Point2D coordinate, String imageFileName)
	 */
	public void createSprite(Point2D coordinate, String imageFileName, Mapping mapping) {
		this.addSprite(new Sprite(this.spriteManager, coordinate, this.getImageName(imageFileName), mapping));
	}

	public Image getBackgroundImage() {
		return backgroundImage;
	}

	public String getImageName(String imageFileName) {
		// caching imagesPackageName
		if (this.imagesPackageName == null) {
			this.imagesPackageName = "/" + resolvePackageName(this.getImagesPackageName()) + "/";
		}
		StringBuilder name = new StringBuilder();
		name.append(this.imagesPackageName).append(imageFileName);
		return name.toString();
	}
	
	
	/*
	 *  TODO: make this the default toolkit method for images instantiation.
	 *  Actually, images instantiation occur in Sprite. The overrided constructors
	 *  in sprite must be eliminated, and exist only a constructor that receives a BufferedImage
	 *  as a parameter 
	 */ 
	public BufferedImage createImage(String imagePath){
		return Images.getBufferedImage(this.getClass(), imagePath);
	}

	/**
	 * Abstract method that must be overridden by subclasses, used to specify
	 * the package name where image files may be found.<br>
	 * Image package name must be a fully qualified packed name, separated by
	 * '.' or by "/", for example: "br.nce.ufrj.labase.images" or
	 * "br/nce/ufrj/labase/images"
	 * 
	 * @return String containing the package name where image files may be
	 *         found.
	 */
	public abstract String getImagesPackageName();

	public int getNpcDisplaySeconds() {
		return npcDisplaySeconds;
	}

	public Integer getPhase() {
		return phase;
	}

	public abstract int getScreenHeight();

	public abstract int getScreenWidth();

	public SpriteManager getSpriteManager() {
		return spriteManager;
	}

	public List<Sprite> getSprites() {
		return spriteManager.getSprites();
	}

	public void hideNpc() {
		this.spriteManager.hideNpc();

		if (npcTimer != null) {
			npcTimer.stop();
			npcTimer = null;
		}
	}

	/**
	 * Applet init() method made final to avoid overriding in subclasses, the
	 * must implement initGame() method.
	 */
	public final void init() {
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		/* implementing double buffering infrastructure */
		offscreen = createImage(this.getScreenWidth(), this.getScreenWidth());
		bufferGraphics = offscreen.getGraphics();

		this.initGame();

		this.setSize(getScreenWidth(), getScreenHeight());

		// TODO: check if MediaTracker is really needed in this context
		MediaTracker mt = new MediaTracker(this);
		if (this.backgroundImage != null)
			mt.addImage(backgroundImage, 0);
		try {
			mt.waitForID(0);
		} catch (InterruptedException ie) {
		}
	}

	/**
	 * Main method to be implemented by subclasses. In this method sprites must
	 * be instantiated and applet parameters must be configured, such as
	 * screenWidth, ScreenHeight, background, otherwise defaults will be used.
	 */
	public abstract void initGame();

	public void mouseClicked(MouseEvent e) {
		this.spriteManager.mouseClicked(e);
	}

	public void mouseDragged(MouseEvent e) {
		/*
		 * this is needed to avoid dragging the sprite outside screen area,
		 * losing it
		 */
		if (checkScreenArea(e)) {
			this.spriteManager.mouseDragged(e);
		}
	}

	public void mouseEntered(MouseEvent e) {
		this.spriteManager.mouseEntered(e);
	}

	public void mouseExited(MouseEvent e) {
		this.spriteManager.mouseExited(e);
	}

	public void mouseMoved(MouseEvent e) {
		// handling cursor format
		Sprite sprite = this.spriteManager.findSprite(e.getX(), e.getY());
		if (sprite != null && sprite.isVisible()) {
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		} else {
			this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		this.spriteManager.mouseMoved(e);
	}

	public void mousePressed(MouseEvent e) {
		this.spriteManager.mousePressed(e);
	}

	public void mouseReleased(MouseEvent e) {
		this.spriteManager.mouseReleased(e);
	}

	public void npcSayText(String text) {
		this.spriteManager.npcSayText(text);
		this.showNpc();
	}

	/**
	 * This method should be implemented if it is required to implement a custom
	 * paint different of the default.
	 * 
	 * @param g
	 */
	public abstract void paintGameBoard(Graphics g);

	/**
	 * This method should be implemented if it is required to implement a custom
	 * processing to the phase one of Drive Elaboration. Leave it blank if you
	 * don't want to implement this phase.
	 * 
	 * @param g
	 */
	public abstract void handlePhaseOne();

	/**
	 * This method should be implemented if it is required to implement a custom
	 * processing to the phase two of Drive Elaboration. Leave it blank if you
	 * don't want to implement this phase.
	 * 
	 * @param g
	 */
	public abstract void handlePhaseTwo();

	/**
	 * This method should be implemented if it is required to implement a custom
	 * processing to the phase three of Drive Elaboration. Leave it blank if you
	 * don't want to implement this phase.
	 * 
	 * @param g
	 */
	public abstract void handlePhaseThree();

	/**
	 * This method should be implemented if it is required to implement a custom
	 * processing to the phase four of Drive Elaboration. Leave it blank if you
	 * don't want to implement this phase.
	 * 
	 * @param g
	 */
	public abstract void handlePhaseFour();

	/**
	 * This method should be implemented if it is required to implement a custom
	 * processing to the phase five of Drive Elaboration. Leave it blank if you
	 * don't want to implement this phase.
	 * 
	 * @param g
	 */
	public abstract void handlePhaseFive();

	/**
	 * This method should be implemented if it is required to implement a custom
	 * processing to the phase six of Drive Elaboration. Leave it blank if you
	 * don't want to implement this phase.
	 * 
	 * @param g
	 */
	public abstract void handlePhaseSix();

	/**
	 * This method should be implemented if it is required to implement a custom
	 * processing to the phase seven of Drive Elaboration. Leave it blank if you
	 * don't want to implement this phase.
	 * 
	 * @param g
	 */
	public abstract void handlePhaseSeven();

	public  void paint(Graphics g) {
		// implementing double buffering
		bufferGraphics.clearRect(0, 0, this.getScreenWidth(), this.getScreenHeight());
		bufferGraphics.drawImage(this.backgroundImage, 0, 0, this);

		// Call a custom paint or a default paint.
		if (!enablePrintGameboard) {
			this.printGraphicElements((Graphics2D) bufferGraphics);
			this.spriteManager.paintSprites(bufferGraphics, this);
			this.spriteManager.paintGraphicElements(bufferGraphics);
		} else
			this.paintGameBoard(bufferGraphics);

		g.drawImage(offscreen, 0, 0, this);
	}

	/**
	 * Method that call a handle during a phase change.
	 * 
	 * @param phase
	 */
	public void changePhase(Integer phase) {
		this.phase = phase;

		if (this.phase.equals(PHASE_ONE))
			this.handlePhaseOne();
		else if (this.phase.equals(PHASE_TWO))
			this.handlePhaseTwo();
		else if (this.phase.equals(PHASE_THREE))
			this.handlePhaseThree();
		else if (this.phase.equals(PHASE_FOUR))
			this.handlePhaseFour();
		else if (this.phase.equals(PHASE_FIVE))
			this.handlePhaseFive();
		else if (this.phase.equals(PHASE_SIX))
			this.handlePhaseSix();
		else if (this.phase.equals(PHASE_SEVEN))
			this.handlePhaseSeven();
	}

	/**
	 * Prints all Graphic elements specified to this GameBoard.<br>
	 * Graphic elements are different from sprites, since they don't interact
	 * with mouse events. Graphic elements are allowed to manage their print
	 * logic, in order to send to screen a variety of graphic types, such as
	 * Java2D graphics, and not only images.
	 * 
	 * @param g
	 */
	private void printGraphicElements(Graphics2D g) {
		for (GraphicPrintable element : this.graphicPrintableElements) {
			element.print(g);
		}
	}

	/**
	 * Resolve package names, replacing "." for "/"
	 * 
	 * @param packageName
	 * @return
	 */
	private String resolvePackageName(String packageName) {
		return packageName.replace(".", "/");
	}

	public void run() {
		// Low priority to the Thread
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

		while (true) {
			repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException ex) {
			}

			Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		}
	}

	public void setBackgroundImage(Image backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public void setBackgroundImage(String backgroundImage) {
		this.backgroundImage = this.createImage(getImageName(backgroundImage));
	}

	public void setNpc(NPC npc) {
		this.spriteManager.setNpc(npc);
	}

	public void setNpcDisplaySeconds(int npcDisplaySeconds) {
		this.npcDisplaySeconds = npcDisplaySeconds;
	}

	/**
	 * Subclasses may customize its own SpriteManager class, adding specific
	 * functionalities to dealing with sprites and how their responses to mouse
	 * events.<br>
	 * If the current spriteManager already contains sprite instances
	 * associated, so they are copied to the spriteManager passed as parameter.
	 * 
	 * @param spriteManager
	 */
	public void setSpriteManager(SpriteManager newSpriteManager) {
		for (Sprite sprite : this.spriteManager.getSprites()) {
			newSpriteManager.addSprite(sprite);
			sprite.setSpriteManager(newSpriteManager);
		}
		this.spriteManager = newSpriteManager;
	}

	public void showNpc() {
		this.spriteManager.showNpc();

		if (getNpcDisplaySeconds() > 0) {
			npcTimer = new Timer(getNpcDisplaySeconds()*1000, new NPCTimer(this));
			npcTimer.setRepeats(false);
			npcTimer.start();
		}
	}

	public void start() {
		Thread th = new Thread(this);
		th.start();
	}

	public void update(Graphics g) {
		paint(g);
	}

	public boolean isEnablePrintGameboard() {
		return enablePrintGameboard;
	}

	public void setEnablePrintGameboard(boolean enableGameboard) {
		this.enablePrintGameboard = enableGameboard;
	}

}
