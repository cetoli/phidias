package br.ufrj.nce.labase.elastico;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import br.ufrj.nce.labase.phidias.toolkit.graphic.GraphicPrintElement;

public class Pino extends GraphicPrintElement {

	private boolean enabled;

	private Ellipse2D internBody;

	private boolean selected;

	public Pino(Color color, Shape body) {
		super(color, body);
		// TODO Auto-generated constructor stub
	}

	public boolean isEnabled() {
		return enabled;
	}

	public boolean isSelected() {
		return selected;
	}

	public void print(Graphics2D g2d) {

		if (this.isEnabled() || this.isSelected())
			g2d.setStroke(new BasicStroke(8.0f));
		else
			g2d.setStroke(new BasicStroke(1.0f));

		g2d.setPaint(this.getColor());

		g2d.draw(this.getBody());
		g2d.fill(this.getBody());
		if (this.isSelected()) {
			g2d.setPaint(Color.GRAY);
			g2d.draw(this.internBody);
			g2d.fill(this.internBody);
		}

	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setPinoBody(Ellipse2D body) {
		// TODO Auto-generated method stub
		this.setBody(body);

		if (body != null) {
			this.internBody = new Ellipse2D.Double(body.getX() + (body.getWidth() / 4), body.getY() + (body.getHeight() / 4), (body.getWidth() / 2), (body.getWidth() / 2));
		}
	}

	public void setSelected(boolean selecionado) {
		this.selected = selecionado;
	}
}
