package br.ufrj.nce.labase.criaconto.images;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;


public class Images {

	public static Image createImage(String string) {
		BufferedImage img = null;

		try {
		  InputStream is = Images.class.getResourceAsStream(string);
		  BufferedInputStream bis = new BufferedInputStream(is);
		  img = ImageIO.read(bis);
		 }
		catch(Exception e) {
		  e.printStackTrace();
		}	
	return img;
	}
}

