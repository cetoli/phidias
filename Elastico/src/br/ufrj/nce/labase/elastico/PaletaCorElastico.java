package br.ufrj.nce.labase.elastico;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import br.ufrj.nce.labase.phidias.swing.GraphicPrintElement;

public class PaletaCorElastico extends GraphicPrintElement {

	private boolean disabled;
	
	private String colorDescription;

	public void setPaletaBody(Rectangle2D paletaCor) {
		this.setBody(paletaCor);
	}

	public PaletaCorElastico(Rectangle2D paletaCor, Color color, String colorDescription) {
		super();
		this.colorDescription = colorDescription;
		this.setBody(paletaCor);
		setColor(color);
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public void print(Graphics2D g2d) {
		if (!this.isDisabled())
			g2d.setPaint(this.getColor());
		else
			g2d.setColor(Color.DARK_GRAY);
		g2d.draw(this.getBody());
		g2d.fill(this.getBody());
	}

	public String getColorDescription() {
		return colorDescription;
	}
}
