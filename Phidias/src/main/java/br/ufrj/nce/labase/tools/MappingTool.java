package br.ufrj.nce.labase.tools;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ListIterator;

import br.ufrj.nce.labase.phidias.toolkit.GameBoard;
import br.ufrj.nce.labase.phidias.toolkit.sprite.Sprite;

/**
 * A very simple tool to help identifying coordinates to determine Sprite mapping instances (point and
 * area mappings).<br>
 * This class can be used by changing the value of <code>imageFile</code> attribute, or by subclassing
 * this class and overriding only the methods <code>getImageFile()</code>; and getImagesPackageName()<br>
 * When this applet is executed, it prints on screen the coordinates of mouse position, over the background
 * image defined, showing the exact position for the mapping to be located. If the user wants do use a 
 * rectangle for an area mapping, just drag the mouse to draw the rectangle, and its corner up-left
 * coordinate, as well as its width and height - will be shown.
 * 
 * @author Diogo Gomes
 *
 */
public class MappingTool extends GameBoard {
	
	private int posX, posY;
	private Rectangle currentRect;
	private boolean dragging = false;
	private boolean drawing  = false;
	
	private static final String imageFile = "jmonta_pedacos.jpg";
	
	private static final String IMAGES_PACKAGE = "br.ufrj.nce.labase.attention.montapecas.images"; 
	
	public void initGame() {
		Sprite silhueta = this.createSprite(new Point(604, 190), "montapedacos_silueta.gif");
		silhueta.setDragEnabled(false);
		
		this.setBackgroundImage(this.getImageFile());
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		
		g.setFont(new Font("Serif", Font.BOLD, 12));
		for (ListIterator<Sprite> iterator = this.getSprites().listIterator(getSprites().size()); iterator.hasPrevious();) {
			Sprite sprite = iterator.previous();
			if (sprite.isVisible()) {
				g.drawString("("+(int)sprite.getPosX()+","+(int)sprite.getPosY()+")", (int) sprite.getPosX(), (int)sprite.getPosY());
			}
		}

		if (drawing){
				g.drawRect(posX, posY, currentRect.width, currentRect.height);
				g.drawString("("+posX+","+posY+", width="+currentRect.width+", heigh="+currentRect.height+")",posX,posY-5);
				
				int centerX = posX + (currentRect.width/2);
				int centerY = posY + (currentRect.height/2);
				g.drawString("x ("+centerX+","+centerY+")",centerX,centerY);
		} if (dragging) {
		}  else {
			g.drawString("("+posX+","+posY+")",posX,posY);
		}
	}
	
	public void mouseMoved(MouseEvent me) 
    { 
		this.dragging = false;
		this.drawing = false;
		posX = me.getX();
		posY = me.getY(); 
		super.mouseMoved(me);
    }
	
	public void mousePressed(MouseEvent e) {
		posX = e.getX();
		posY = e.getY(); 
        currentRect = new Rectangle(posX, posY, 0, 0);
		this.dragging = true;
		super.mousePressed(e);
    }

    public void mouseDragged(MouseEvent e) {
		if (this.getSpriteManager().getCurrentSprite() == null){
			this.drawing  = true;
		} else {
			this.dragging = true;
		}
        currentRect.setSize(e.getX() - currentRect.x,
                			e.getY() - currentRect.y);
		super.mouseDragged(e);
    }
	
    @Override
    public String getImagesPackageName() {
    	return IMAGES_PACKAGE ;
    }
    
    public String getImageFile(){
    	return this.imageFile;
    }

	@Override
	public int getScreenHeight() {
		return 600;
	}

	@Override
	public int getScreenWidth() {
		return 800;
	}

	@Override
	public void handlePhaseFive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePhaseFour() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePhaseOne() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePhaseSeven() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePhaseSix() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePhaseThree() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePhaseTwo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paintGameBoard(Graphics g) {
		// TODO Auto-generated method stub
		
	}
}