package br.ufrj.nce.labase.tools;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import br.ufrj.nce.labase.phidias.toolkit.GameBoard;
import br.ufrj.nce.labase.phidias.toolkit.sprite.Sprite;

/**
 * A very simple tool to help identifying coordinates to determane Sprite mapping instances (point and
 * area mappings).<br>
 * This class can be used by changing the value of <code>imageFile</code> attribute, or by subclassing
 * this class and overriding only the methods <code>getImageFile()</code>; and getImagesPackageName()<br>
 * When this applet is executed, it prints on screen the coordinates of mouse position, over the background
 * image defined, showing the exact position for the mapping to be located. If the user wants do use a 
 * rectange for an area mapping, just drag the mouse to draw the rectange, and its corner up-left
 * coordinate, as well as its width and heigh - will be shown.
 * 
 * @author Diogo Gomes
 *
 */
public class MappingTool extends GameBoard {
	
	private int posX, posY;
	private Rectangle currentRect;
	private boolean dragging = false;
	
	private static final String imageFile = "jmonta_pedacos.jpg";
	
	private static final String IMAGES_PACKAGE = "br.ufrj.nce.labase.attention.montapecas.images"; 
	
	public void initGame() {
	//	Sprite silhueta = this.createSprite(new Point(604, 190), "montapedacos_silueta.gif");
	//	silhueta.setDragEnabled(false);
		
		this.setBackgroundImage(this.getImageFile());
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		g.setFont(new Font("Serif", Font.BOLD, 12));
		if (dragging){
			g.drawRect(posX, posY, currentRect.width, currentRect.height);
			g.drawString("("+posX+","+posY+", width="+currentRect.width+", heigh="+currentRect.height+")",posX,posY-5);
		} else {
			g.drawString("("+posX+","+posY+")",posX,posY);
		}
	}
	
	public void mouseMoved(MouseEvent me) 
    { 
		this.dragging = false;
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
    	this.dragging = true;
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
}