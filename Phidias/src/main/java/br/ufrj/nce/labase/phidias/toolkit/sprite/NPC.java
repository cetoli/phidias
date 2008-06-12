package br.ufrj.nce.labase.phidias.toolkit.sprite;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.Hashtable;

import br.ufrj.nce.labase.phidias.util.Images;


/**
 * 
 * @author Diogo Gomes
 */
public class NPC extends Sprite {
	
	private BufferedImage originalImage;
	
	private Font font = new Font("Serif", Font.BOLD, 12);
	
	private Color color = Color.BLACK;
	
	private int recuoTexto = 92;
	
	private int posYInicial = 10;
	
	private int paragraphWidth = 0;
	
    private static final Hashtable<TextAttribute, Object> stimulusFont =
        new Hashtable<TextAttribute, Object>();
    
    static {
    	stimulusFont.put(TextAttribute.FAMILY, "Serif");
    	stimulusFont.put(TextAttribute.SIZE, new Float(10));
    }      
    
	public NPC(SpriteManager spriteManager, Point2D coordinate, String imagePath) {
		super(spriteManager, coordinate, imagePath);
		this.originalImage = this.getImage();
		this.setVisible(false);
	}
	
	public NPC(SpriteManager spriteManager, Point2D coordinate, BufferedImage image) {
		super(spriteManager, coordinate, image);
		this.originalImage = this.getImage();
		this.setVisible(false);
	}
	
	public void sayText(String text){
		this.setVisible(true);

		this.setImage(Images.cloneImage(this.originalImage));
		
        Graphics2D g2d = this.getImage().createGraphics();
        g2d.setFont(this.font);

        LineBreakMeasurer lineMeasurer = null;
        g2d.setColor(this.color);

        float width = g2d.getDeviceConfiguration().getBounds().width - this.recuoTexto -10;
        
        // index of the first character in the paragraph.
        int paragraphStart = 0;

        // index of the first character after the end of the paragraph.
        int paragraphEnd = 0;
        
        Hashtable<TextAttribute, Object> textFormat = new Hashtable<TextAttribute, Object>();
        textFormat.put(TextAttribute.FONT, this.font);
        
        AttributedString textToPrint = new AttributedString(text, textFormat);
        // Create a new LineBreakMeasurer from the paragraph.
        // It will be cached and re-used.
        if (lineMeasurer == null) {
            AttributedCharacterIterator paragraph = textToPrint.getIterator();
            paragraphStart = paragraph.getBeginIndex();
            paragraphEnd = paragraph.getEndIndex();
            FontRenderContext frc = g2d.getFontRenderContext();
            lineMeasurer = new LineBreakMeasurer(paragraph, frc);
        }
        
        // Set break width to width of Component.
        float breakWidth = (this.paragraphWidth != 0 ? paragraphWidth : width);
        float drawPosY = this.posYInicial;
        // Set position to the index of the first character in the paragraph.
        lineMeasurer.setPosition(paragraphStart);

        // Get lines until the entire paragraph has been displayed.
        while (lineMeasurer.getPosition() < paragraphEnd) {

            // Retrieve next layout. A cleverer program would also cache
            // these layouts until the component is re-sized.
            TextLayout layout = lineMeasurer.nextLayout(breakWidth);

            float drawPosX = this.recuoTexto;

            // Move y-coordinate by the ascent of the layout.
            drawPosY += layout.getAscent();

            // Draw the TextLayout at (drawPosX, drawPosY).
            layout.draw(g2d, drawPosX, drawPosY);

            // Move y-coordinate in preparation for next layout.
            drawPosY += layout.getDescent() + layout.getLeading();
        }
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getRecuoTexto() {
		return recuoTexto;
	}

	public void setRecuoTexto(int recuoTexto) {
		this.recuoTexto = recuoTexto;
	}

	public int getPosYInicial() {
		return posYInicial;
	}

	public void setPosYInicial(int posYInicial) {
		this.posYInicial = posYInicial;
	}

	public int getParagraphWidth() {
		return paragraphWidth;
	}

	public void setParagraphWidth(int paragraphWidth) {
		this.paragraphWidth = paragraphWidth;
	}

	public BufferedImage getOriginalImage() {
		return originalImage;
	}

	public void setOriginalImage(BufferedImage originalImage) {
		this.originalImage = originalImage;
	}
	
}
