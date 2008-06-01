/**
 * 
 */
package br.ufrj.nce.labase.phidias.toolkit.filter;

import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import br.ufrj.nce.labase.phidias.util.Images;

/**
 * Graphic filter that apply a resize to the image passed as parameter.
 * 
 * @author Andre Moraes
 * 
 */
public class ThumbnailGraphicFilter implements GraphicFilter {

	private ImageObserver imageObserver;

	private int quality = 100;

	private int thumbHeight = 100;

	private int thumbWidth = 100;

	public ThumbnailGraphicFilter() {
		super();
	}

	/**
	 * This constructor can be called if the user want to reset the initial
	 * values of the ThumbnailGraphicFilter.
	 * 
	 * @param thumbWidth
	 * @param thumbHeight
	 * @param quality
	 * @param imageObserver
	 */
	public ThumbnailGraphicFilter(int thumbWidth, int thumbHeight, int quality, ImageObserver imageObserver) {
		super();
		this.thumbWidth = thumbWidth;
		this.thumbHeight = thumbHeight;
		this.quality = quality;
		this.imageObserver = imageObserver;
	}

	/**
	 * Graphic filter that applies a resize to the images.
	 * 
	 * @see br.ufrj.nce.labase.phidias.swing.GraphicFilter#filter(java.awt.image.BufferedImage)
	 */
	public BufferedImage filter(BufferedImage imagem) {

		int thumbWidth = 100;
		int thumbHeight = 100;
		int quality = 100;
		return Images.createThumbnail(imageObserver, imagem, thumbWidth, thumbHeight, quality);
	}

}
