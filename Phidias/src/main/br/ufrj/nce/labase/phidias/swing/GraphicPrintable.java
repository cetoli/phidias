package br.ufrj.nce.labase.phidias.swing;

import java.awt.Graphics;

/**
 * Used for printing non-sprite elements, such as a Java2D graphic or an image that doesn'n interact with
 * mouse event (background element).
 * @author Diogo Gomes
 *
 */
public interface GraphicPrintable {
	public void print(Graphics graphic);
}
