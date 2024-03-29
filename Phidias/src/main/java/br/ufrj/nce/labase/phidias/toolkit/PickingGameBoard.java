package br.ufrj.nce.labase.phidias.toolkit;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import br.ufrj.nce.labase.phidias.toolkit.graphic.StaticImage;
import br.ufrj.nce.labase.phidias.toolkit.sprite.NPC;
import br.ufrj.nce.labase.phidias.toolkit.sprite.Sprite;
import br.ufrj.nce.labase.phidias.toolkit.sprite.event.ActionButton;



/**
 * Base class for picking-like games. This class implements basic facilities for
 * funcionalities demandes by attention games based on picking a specific object (specified
 * by a mapping instance in the background), according to current sprite shown.
 * 
 * @author Diogo Gomes
 */
public abstract class PickingGameBoard extends GameBoard {
	
	
	private List<Sprite> randomSprites = new LinkedList<Sprite>();
	
	private Sprite currentSprite;
	
	private StaticImage currentImage;
	
	private Random generator;
	
	private boolean sortSprites = true;
	
	public final void initGame() {
		generator = new Random(System.currentTimeMillis());
		this.initPickingGame();
		if (this.sortSprites){
			this.startSortingSprites();
		}
		this.spriteManager.setDragEnabled(false);
	}
	
	public void startSortingSprites(){
		this.sortSprites = true;
		this.randomSprites = new ArrayList<Sprite>(this.spriteManager.getSprites());
		this.setNextSpriteElement();
		this.addGraphicPrintable(this.currentImage);
	}
	
	/**
	 * PickingGameBoard class implements facilities to commonly used funcionalities of
	 * picking-like games. It implements initGame() method, and subclasses must so
	 * implement initPickingGame() method for sprites instantiation and game's parameters
	 * configuring.
	 */
	public abstract void initPickingGame();

	/**
	 * Abstract method implemented by subclasses to define the position where the random
	 * image should be located.
	 * @return Point2D instance defining the location of the random image.
	 */
	protected abstract Point2D getImageCoordinate();

	private void setNextSpriteElement() {
		if (randomSprites.size() >0){
			this.currentSprite = randomSprites.remove( generator.nextInt(randomSprites.size()) );
			if (this.currentImage == null){
				this.currentImage = new StaticImage(this.getImageCoordinate(), currentSprite.getImage());
			} else {
				this.currentImage.setImage(currentSprite.getImage());
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Sprite spriteClicked = this.spriteManager.findSprite(e.getX(), e.getY());
		if (spriteClicked != null){
			spriteClicked.mouseClicked(e);
			if ((this.sortSprites) && 
			    (!(spriteClicked instanceof NPC) && !(spriteClicked instanceof ActionButton)) ){
				if (spriteClicked == this.currentSprite){
					//TODO: implementar l�ciga para acerto
					this.setNextSpriteElement();
					System.out.println("Acertou");
				} else {
					//TODO: implementar l�gica para erro
					System.out.println("Errou");
				}
			}
		}
	}

	public boolean isSortSprites() {
		return sortSprites;
	}

	public void setSortSprites(boolean sortSprites) {
		this.sortSprites = sortSprites;
	}
	
	
	
}
