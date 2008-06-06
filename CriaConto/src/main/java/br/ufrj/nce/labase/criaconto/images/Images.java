package br.ufrj.nce.labase.criaconto.images;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Images {
	private Image image = null;

	public Images(String filename) {
		image = createImage(filename);
	}

	public Images(int widthInPixels, int heightInPixels) {
		image = new BufferedImage(widthInPixels, heightInPixels,
				BufferedImage.TYPE_INT_RGB);
	}

	public static Image createImage(String string) {
		BufferedImage img = null;

		try {
			InputStream is = Images.class.getResourceAsStream(string);
			BufferedInputStream bis = new BufferedInputStream(is);
			img = ImageIO.read(bis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return img;
	}

	public static Image createImage(String filename, int width, int height) {
		return createImage(filename).getScaledInstance(width, height,
				Image.SCALE_SMOOTH);
	}

	public int getWidth() {
		return image.getWidth(null);
	}

	public int getHeight() {
		return image.getHeight(null);
	}

	public Color getColorAt(int pixel, int pixel2) {
		// TODO Auto-generated method stub
		return null;
	}
}


