package br.ufrj.nce.labase.attention;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import br.ufrj.nce.labase.phidias.swing.GameBoard;
import br.ufrj.nce.labase.phidias.swing.Sprite;
import br.ufrj.nce.labase.phidias.swing.StaticImage;


/**
 * Base class for picking-like games. This class implements basic facilities for
 * funcionalities demandes by attention games based on picking a specific object (specified
 * by a mapping instance in the background), according to current sprite shown.
 * @author Diogo Gomes
 *
 */
public abstract class PickingGameBoard extends GameBoard {
	
	
	private List<Sprite> randomSprites = new LinkedList<Sprite>();
	
	private Sprite currentSprite;
	
	private StaticImage currentImage;
	
	private Random generator;
	
	public void init() {
		super.init();
		generator = new Random(System.currentTimeMillis());
		this.spriteManager.setDragEnabled(false);
		this.randomSprites = new ArrayList<Sprite>(this.spriteManager.getSprites());
		this.setNextSpriteElement();
		this.addGraphicPrintable(this.currentImage);
	}

	private void setNextSpriteElement() {
		this.currentSprite = randomSprites.remove( generator.nextInt(randomSprites.size()) );
		if (this.currentImage == null){
			this.currentImage = new StaticImage(this.getImageCoordinate(), currentSprite.getImage());
		} else {
			this.currentImage.setImage(currentSprite.getImage());
		}
	}
	
	protected abstract Point2D getImageCoordinate();


	@Override
	public void mouseClicked(MouseEvent e) {
		Sprite spriteClicked = this.spriteManager.findSprite(e.getX(), e.getY());
		if (spriteClicked != null){
			spriteClicked.mouseClicked(e);
			if (spriteClicked == this.currentSprite){
				// acertou
				this.setNextSpriteElement();
				System.out.println("Acertou");
			} else {
				// errou
				System.out.println("Errou");
			}
		}
	}
	
	
}
