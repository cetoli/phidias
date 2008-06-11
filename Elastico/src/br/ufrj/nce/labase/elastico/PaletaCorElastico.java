package br.ufrj.nce.labase.elastico;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Arrays;
import java.util.Collections;

import br.ufrj.nce.labase.phidias.toolkit.graphic.GraphicPrintElement;
import br.ufrj.nce.labase.phidias.toolkit.sprite.Sprite;

public class PaletaCorElastico extends GraphicPrintElement {

	private boolean disabled;

	private String colorDescription;

	private Ellipse2D internBody;

	public PaletaCorElastico(Ellipse2D paletaCor, Color color, String colorDescription) {
		super(color, paletaCor);
		this.colorDescription = colorDescription;
		this.setBody(paletaCor);

		this.internBody = new Ellipse2D.Double(paletaCor.getX() + (paletaCor.getWidth() / 4), paletaCor.getY() + (paletaCor.getHeight() / 4), (paletaCor.getWidth() / 2), (paletaCor.getHeight() / 2));
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public void print(Graphics2D g2d) {
		
		//Imprime a elipse externa do elastico
		g2d.setPaint(this.getColor());
		g2d.draw(this.getBody());
		g2d.fill(this.getBody());

		//Imprime a ellipse interna do elastico
		g2d.setColor(Color.DARK_GRAY);
		g2d.draw(this.internBody);
		g2d.fill(this.internBody);
		
		//Imprime uma reta que indica que o elastico ja foi escolhido e está desabilitado.
		if (this.isDisabled()){
			g2d.setStroke(new BasicStroke(3.0f));
			g2d.setPaint(Color.RED);
			g2d.draw(new Line2D.Double(this.getBody().getBounds2D().getX(), this.getBody().getBounds2D().getY(), this.getBody().getBounds2D().getX() + this.getBody().getBounds2D().getWidth(), this.getBody().getBounds2D().getY() + this.getBody().getBounds2D().getHeight()));
			g2d.setStroke(new BasicStroke(1.0f));
		}		
	}

	public String getColorDescription() {
		return colorDescription;
	}
}
