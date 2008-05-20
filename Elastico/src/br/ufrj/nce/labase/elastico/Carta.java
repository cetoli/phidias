package br.ufrj.nce.labase.elastico;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;

public class Carta extends Sprite {

	private boolean choosed, brighter;
	private double initialX, initialY;

	public Carta(SpriteManager spriteManager, Point2D coordinate, BufferedImage image) {
		super(spriteManager, coordinate, image);
	}

	public double getInitialX() {
		return initialX;
	}

	public double getInitialY() {
		return initialY;
	}

	public boolean isBrighter() {
		return brighter;
	}

	public boolean isChoosed() {
		return choosed;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.setChoosed(true);
		this.brighter = false;
		this.setPosXY(e.getX() - this.initialX, e.getY() - this.initialY);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (!this.choosed && this.getBody().contains(e.getX(), e.getY()))
			this.brighter = true;
		else
			this.brighter = false;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.initialX = e.getX() - this.getPosX();
		this.initialY = e.getY() - this.getPosY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		List<GraphicPrintElement> obstacules = this.getSpriteManager().getObstacules();
		for (GraphicPrintElement obstacule : obstacules) {
			if (obstacule.getBody().intersects(this.getBody())) {
				Point2D cartaInit = new Point2D.Double(this.getBody().getCenterX(), this.getBody().getCenterY());
				Rectangle2D rectangle = obstacule.getBody().getBounds2D();
				double obstaculeXYLeft = Math.abs(cartaInit.distance(rectangle.getMinX(), rectangle.getMinY()));
				double obstaculeXYRight = Math.abs(cartaInit.distance(rectangle.getMaxX(), rectangle.getY()));
				double obstaculeXYUpperLeft = Math.abs(cartaInit.distance(rectangle.getX(), rectangle.getMaxY()));
				double obstaculeXYUpperRight = Math.abs(cartaInit.distance(rectangle.getMaxX(), rectangle.getMaxY()));

				// Trata os eventos de decisão em que canto a carta será
				// deslocada caso intercepte um pino. Essa comparação leva em
				// consideração o centro da carta e os pontos que formam o
				// obstaculo.
				if (obstaculeXYLeft <= obstaculeXYRight && obstaculeXYLeft <= obstaculeXYUpperLeft && obstaculeXYLeft <= obstaculeXYUpperRight)
					this.setPosXY(rectangle.getMinX() - this.getBody().getWidth(), rectangle.getMinY());
				else if (obstaculeXYRight <= obstaculeXYLeft && obstaculeXYRight <= obstaculeXYUpperLeft && obstaculeXYRight <= obstaculeXYUpperRight)
					this.setPosXY(rectangle.getMaxX(), rectangle.getMinY());
				else if (obstaculeXYUpperLeft <= obstaculeXYLeft && obstaculeXYUpperLeft <= obstaculeXYRight && obstaculeXYUpperLeft <= obstaculeXYUpperRight)
					this.setPosXY(rectangle.getMinX() - this.getBody().getWidth(), rectangle.getMaxY());
				else if (obstaculeXYUpperRight <= obstaculeXYLeft && obstaculeXYUpperRight <= obstaculeXYRight && obstaculeXYUpperRight <= obstaculeXYUpperLeft)
					this.setPosXY(rectangle.getMaxX(), rectangle.getMaxY());
			}
		}
	}

	public void setChoosed(boolean escolhida) {
		this.choosed = escolhida;
	}

	public void setInitialX(double initialX) {
		this.initialX = initialX;
	}

	public void setInitialY(double initialY) {
		this.initialY = initialY;
	}
}
