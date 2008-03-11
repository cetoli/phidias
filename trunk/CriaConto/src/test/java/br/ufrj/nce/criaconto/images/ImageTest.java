package br.ufrj.nce.criaconto.images;
import java.applet.Applet;
import java.awt.Image;
import java.awt.image.ImageObserver;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import br.ufrj.nce.labase.criaconto.images.Images;
import br.ufrj.nce.labase.phidias.view.player.Board;
import br.ufrj.nce.labase.phidias.view.player.Character;
import br.ufrj.nce.labase.phidias.view.player.Piece;

/**
 * Unit test for simple App.
 */
public class ImageTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ImageTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ImageTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	Image icon = Images.createImage("anao1.gif");
        //Image boardImage = new ImageIcon(baseURL + "fundo.jpg").getImage();
        //Image boardImage = new ImageIcon(baseURL + "fundo.jpg").getImage();
    	ImageObserver observer = new ImageObserver(){

			public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
				// TODO Auto-generated method stub
				return false;
			}
    	};
        assertNotNull(icon);
    	assertTrue(icon.getHeight(observer)>70);
    	Board board = new Board(new Applet(), 500, 500, "fundo.jpg");
    	Piece piece = new Character(board, icon, "anao1", 480, 110);
    	assertNotNull(piece);   
    }
        
}

