/**
 * 
 */
package br.ufrj.nce.labase.phidias.toolkit.filter;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.RescaleOp;

/**
 * Graphic filter that apply a brighter effect to the image passed as parameter.
 * 
 * @author Diogo Gomes
 * @author Andre Moraes
 */
public class HighLightGraphicFilter implements GraphicFilter {

	private BufferedImageOp op = new RescaleOp(1.5f, 0, null);

	/**
	 * Graphic filter that applies a brighter effect to images.
	 * @see br.ufrj.nce.labase.phidias.graphictoolkit.filter.GraphicFilter#filter(java.awt.image.BufferedImage)
	 */
	public BufferedImage filter(BufferedImage imagem) {
		return op.filter(imagem, null);
	}

}
