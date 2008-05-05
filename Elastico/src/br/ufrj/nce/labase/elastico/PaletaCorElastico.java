package br.ufrj.nce.labase.elastico;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class PaletaCorElastico {
	private Rectangle2D paletaCor;

	private Color color;
	
	private boolean disabled;

	public Rectangle2D getPaletaCor() {
		return paletaCor;
	}

	public void setPaletaCor(Rectangle2D paletaCor) {
		this.paletaCor = paletaCor;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public PaletaCorElastico(Rectangle2D paletaCor, Color color) {
		super();
		this.paletaCor = paletaCor;
		this.color = color;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

}
