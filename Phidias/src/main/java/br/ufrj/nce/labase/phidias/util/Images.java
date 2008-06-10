package br.ufrj.nce.labase.phidias.util;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class Images {
	private Image image = null;

	public Images(String filename) {
		image = createImage(filename);
	}

	public Images(int widthInPixels, int heightInPixels) {
		image = new BufferedImage(widthInPixels, heightInPixels, BufferedImage.TYPE_INT_RGB);
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

	public static BufferedImage getBufferedImage(String string) {

		// get this device's graphics configuration
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsConfiguration gc = ge.getDefaultScreenDevice().getDefaultConfiguration();

		try {
			BufferedImage im = ImageIO.read(Image.class.getResourceAsStream(string));

			int transparency = im.getColorModel().getTransparency();
			BufferedImage copy = gc.createCompatibleImage(im.getWidth(), im.getHeight(), transparency);

			// create a graphics context
			Graphics2D g2d = copy.createGraphics();

			// copy image
			g2d.drawImage(im, 0, 0, null);
			g2d.dispose();

			return copy;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Image createImage(String filename, int width, int height) {
		return createImage(filename).getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}

	public int getWidth() {
		return image.getWidth(null);
	}

	public int getHeight() {
		return image.getHeight(null);
	}

	public static BufferedImage createThumbnail(ImageObserver imgObserver, BufferedImage image, int thumbWidth, int thumbHeight, int quality) {
		double thumbRatio = (double) thumbWidth / (double) thumbHeight;
		int imageWidth = image.getWidth(null);
		int imageHeight = image.getHeight(null);
		double imageRatio = (double) imageWidth / (double) imageHeight;
		if (thumbRatio < imageRatio) {
			thumbHeight = (int) (thumbWidth / imageRatio);
		} else {
			thumbWidth = (int) (thumbHeight * imageRatio);
		}
		// draw original image to thumbnail image object and
		// scale it to the new size on-the-fly
		BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = thumbImage.createGraphics();
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, imgObserver);

		BufferedOutputStream out = new BufferedOutputStream(new ByteArrayOutputStream());
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);

		quality = Math.max(0, Math.min(quality, 100));
		param.setQuality((float) quality / 100.0f, false);
		encoder.setJPEGEncodeParam(param);
		try {
			encoder.encode(thumbImage);
			out.close();
		} catch (ImageFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return thumbImage;
	}

}
