package br.ufrj.nce.labase.phidias.swing.images;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

//TODO: find a better solution for image relative path, for differente applications.

/*
 * Verificar esta abordagem":
 *  getClass().getResourceAsStream(): procura o resource no mesmo diretorio do .class.

getClass().getClassLoader().getResourceAsStream(): procura no CLASSPATH.

Posso usar aqui a opção 2, e a subclasse passar o nome da imagem com o caminho completo. a classe passaria como parametro o caminho completo 
do arquivo de imagem
 */
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
			if (img == null){
				System.out.println("No sprite image for " + string);
			}
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

	public static BufferedImage getBufferedImage(String imagePath) {
		return (BufferedImage) createImage(imagePath);
	}
}


