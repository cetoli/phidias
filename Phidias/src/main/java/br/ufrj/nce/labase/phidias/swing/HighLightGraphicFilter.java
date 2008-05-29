/**
 * 
 */
package br.ufrj.nce.labase.phidias.swing;

import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.RescaleOp;

import br.ufrj.nce.labase.phidias.util.Images;

/**
 * Graphic filter that apply a brighter effect to the image passed as parameter.
 * @author Diogo Gomes
 *
 */
public class HighLightGraphicFilter implements GraphicFilter {

	private BufferedImageOp op = new RescaleOp(1.5f, 0, null);

	/**
	 * Graphic filter that applies a brighter effect to images.
	 * @see br.ufrj.nce.labase.phidias.swing.GraphicFilter#filter(java.awt.image.BufferedImage)
	 */
	public BufferedImage filter(BufferedImage imagem) {
		
		System.out.println("aumentando brilho do sprite");

		return op.filter(imagem, null);
	}

}
