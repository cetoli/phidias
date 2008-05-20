package br.ufrj.nce.labase.elastico;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RescaleOp;

import br.ufrj.nce.labase.phidias.util.Images;

public class ElasticoSpriteManager extends SpriteManager {

	private BufferedImageOp op = new RescaleOp(1.5f, 0, null);

	@Override
	public void mouseMoved(MouseEvent e) {
		for (Sprite sprite : this.getSprites()) {
			if (sprite instanceof Carta)
				sprite.mouseMoved(e);
			else
				super.mouseMoved(e);
		}

	}

	@Override
	public void paintSprites(Graphics g, ImageObserver imgObserver) {

		// Pinta os Sprites sem brilho e sem zoom
		for (Sprite sprite : this.getSprites()) {
			if (sprite instanceof Carta) {
				if (!((Carta) sprite).isBrighter() || !((Carta) sprite).isChoosed()) {
					g.drawImage(sprite.getImage(), (int) sprite.getPosX(), (int) sprite.getPosY(), imgObserver);
				}
			}
		}

		// Pinta os Sprites com brilho e com zoom
		for (Sprite sprite : this.getSprites()) {
			if (sprite instanceof Carta) {

				if (((Carta) sprite).isBrighter()) {
					BufferedImage image = op.filter(sprite.getImage(), null);
					int thumbWidth = 100;
					int thumbHeight = 100;
					int quality = 100;
					BufferedImage thumbImage = Images.createThumbnail(imgObserver, image, thumbWidth, thumbHeight, quality);
					g.drawImage(thumbImage, (int) sprite.getPosX(), (int) sprite.getPosY(), imgObserver);
				}
			}
		}
	}

}
