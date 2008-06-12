package br.ufrj.nce.labase.phidias.toolkit.sprite.event;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import br.ufrj.nce.labase.phidias.toolkit.sprite.Sprite;
import br.ufrj.nce.labase.phidias.toolkit.sprite.SpriteManager;


/**
 * 
 * @author Diogo Gomes
 */
public class ActionButton extends Sprite {
    
	public ActionButton(SpriteManager spriteManager, Point2D coordinate, String imagePath, SpriteActionListener actionListener) {
		super(spriteManager, coordinate, imagePath);
		this.addSpriteActionListener(actionListener);
		this.setVisible(true);
		this.setDragEnabled(false);
	}
	
	public ActionButton(SpriteManager spriteManager, Point2D coordinate, BufferedImage image, SpriteActionListener actionListener) {
		super(spriteManager, coordinate, image);
		this.addSpriteActionListener(actionListener);
		this.setVisible(true);
		this.setDragEnabled(false);
	}
	
}
