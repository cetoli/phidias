package br.ufrj.nce.labase.criaconto.view.player;

import java.applet.Applet;
import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.Hashtable;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import br.ufrj.nce.labase.criaconto.images.Images;
import br.ufrj.nce.labase.common.MidiSound;
import br.ufrj.nce.labase.criaconto.control.Controller;
import br.ufrj.nce.labase.criaconto.view.LoginPanel;
import br.ufrj.nce.labase.phidias.communication.bean.StimulusBean;
import br.ufrj.nce.labase.phidias.communication.bean.StimulusResponseBean;
import br.ufrj.nce.labase.phidias.controller.Session;
import br.ufrj.nce.labase.phidias.exception.PhidiasException;
import br.ufrj.nce.labase.phidias.view.Board;
import br.ufrj.nce.labase.phidias.view.Piece;

public class CriaContoPlayer extends Applet {
	private static final long serialVersionUID = 1L;
	private Board board;
    private Piece piece = null;  
    private LoginPanel loginPanel;
    private Timer stimulusTimer;	
    private Timer gameStartTimer;
    private Timer npcTimer;
    private int startSequence = 0;
    private Piece npc;
    
    static final String characters[] = {
        "principe", "branca_de_neve", "cacador_frente", "rainha_ma", "anao1", "anao2", "anao3", "anao4", "anao5", "anao6", "anao7"
    };
    static final String sounds[] = {
        "entrada.midi", "entrada.midi", "entrada.midi", "entrada.midi", "entrada.midi", "entrada.midi", "entrada.midi", "entrada.midi", "entrada.midi", "entrada.midi", "entrada.midi"
    };
    static final Scene scenes[] = {
    	new Scene("castelo", 0, 5), new Scene("ponte", 350, 360), new Scene("casa", 600, 100), new Scene("tronco", 465, 212), new Scene("mina", 570, 400)
    };
    
    private static final Hashtable<TextAttribute, Object> stimulusFont =
        									new Hashtable<TextAttribute, Object>();

    static {
    	stimulusFont.put(TextAttribute.FAMILY, "Serif");
    	stimulusFont.put(TextAttribute.SIZE, new Float(10));
    }  
    
    
    public CriaContoPlayer() { 
    	
	}

    public void init() {
		setSize(1024, 820);
    	setBackground(Color.WHITE);
    	setLayout(new GridBagLayout());

    	Controller.setCurrentSound(new MidiSound("entrada.midi", true));
    	Controller.startSound();
    	
    	loginPanel = new LoginPanel();
    	loginPanel.setPreferredSize(new Dimension(1024, 820));
    	add(loginPanel, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));
    	
    	loginPanel.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			showMessageDialog("Por favor aguarde!");
    			
    			if (joinSession()) {
    				startGame();
    			} else {
    				showMessageDialog("Ocorreu um erro ao iniciar o jogo! Por favor, tente novamente mais tarde!");
    			}
    		}
    	});
    }
    
    private void showMessageDialog(String message) {
    	JOptionPane.showMessageDialog(this, message);		
    }
    
    private void startGame() {		
		createInitialBackground("tela_inicial.jpg");
        
        gameStartTimer = new Timer(1100, new GameStartTimer());
        gameStartTimer.start();        
	}

	private Board createBoard(String backgroundImageName){
		Board b = new Board(this, getWidth(), getHeight(), backgroundImageName);
		return b;
	}

	private void createInitialBackground(String backgroundImage) {
		setSize(1024, 820);
    	removeAll();
		
    	board = createBoard(backgroundImage);
    	
        add(board, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(1, 1, 1, 1), 0, 0));
        board.start();
	}

	private boolean joinSession() {
		try {
			return Controller.joinSession(loginPanel.getLogin(), LoginPanel.CRIA_CONTO);
		} catch (PhidiasException ex) {
			showMessageDialog("Erro ao iniciar sessão!");
			return false;
		}
	}

	private void changePhase() {
		Session.getInstance().changePhase();
		
		switch (Session.getInstance().getCurrentPhase()) {
			case 1:
				startStimulusTimer();
				firstPhase();
				break;
			case 2:
				showMessageDialog("Parabéns! Agora descubra novidades no cenário!");
				secondPhase();
				break;
			case 3:
				showMessageDialog("Parabéns! Agora monte seu próprio cenário!");
				thirdPhase();
				break;
			default:
				break;
		} 
	}

	private void firstPhase() {
		setSize(1024, 820);
		removeAll();
		
	    board = createBoard("fundo.jpg");
		
	    add(board, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(1, 1, 1, 1), 0, 0));
	    putCharactersOnBoard(true, false);

	    board.start();
	}

	private void secondPhase() {
		setSize(1024, 820);
		removeAll();
		
	    board = createBoard("fundo.jpg");
		
	    add(board, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(1, 1, 1, 1), 0, 0));
	    putCharactersOnBoard(true, true);
	    board.start();
	    
	    stimulusTimer = new Timer(10000, new StimulusTimer());
		stimulusTimer.start();
	}

	private void thirdPhase() {
		setSize(1048, 820);
		removeAll();
	
		board = createBoard("fundo2.jpg");
		
		add(board, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(1, 1, 1, 1), 0, 0));
		putScenicItensOnBoard();
		putCharactersOnBoard(false, true);
		putAnimalsOnBoard();
		board.start();
	}

	private void showNPC(String stimulus) throws InterruptedException {
		BufferedImage npcImage = (BufferedImage)Images.createImage("NPC.gif");		

        // Get drawing context
        Graphics2D g2d = npcImage.createGraphics();
        g2d.setFont(new Font(Font.SERIF, Font.BOLD, 6));
        
        paintStimulus(g2d, stimulus);
		
		// Dispose context
        g2d.dispose();
        
		npc = new Piece(board, npcImage, "npc", 650, 25);
		
		npcTimer = new Timer(7000, new NPCTimer());
		npcTimer.start();
	}
	
   private void paintStimulus(Graphics g, String stimulus) {

        Graphics2D g2d = (Graphics2D)g;
        LineBreakMeasurer lineMeasurer = null;
        g2d.setColor(Color.BLACK);

        int recuo = 85;
        int yInicial = 15;
        float width = g2d.getDeviceConfiguration().getBounds().width - recuo -10;
        
        // index of the first character in the paragraph.
        int paragraphStart = 0;

        // index of the first character after the end of the paragraph.
        int paragraphEnd = 0;

        
        AttributedString text = new AttributedString(stimulus, stimulusFont);
        // Create a new LineBreakMeasurer from the paragraph.
        // It will be cached and re-used.
        if (lineMeasurer == null) {
            AttributedCharacterIterator paragraph = text.getIterator();
            paragraphStart = paragraph.getBeginIndex();
            paragraphEnd = paragraph.getEndIndex();
            FontRenderContext frc = g2d.getFontRenderContext();
            lineMeasurer = new LineBreakMeasurer(paragraph, frc);
        }

        
        // Set break width to width of Component.
        float breakWidth = width;
        float drawPosY = yInicial;
        // Set position to the index of the first character in the paragraph.
        lineMeasurer.setPosition(paragraphStart);

        // Get lines until the entire paragraph has been displayed.
        while (lineMeasurer.getPosition() < paragraphEnd) {

            // Retrieve next layout. A cleverer program would also cache
            // these layouts until the component is re-sized.
            TextLayout layout = lineMeasurer.nextLayout(breakWidth);

/*            // Compute pen x position. If the paragraph is right-to-left we
            // will align the TextLayouts to the right edge of the panel.
            // Note: this won't occur for the English text in this sample.
            // Note: drawPosX is always where the LEFT of the text is placed.
            float drawPosX = layout.isLeftToRight()
                ? recuo : breakWidth - layout.getAdvance();
*/
            float drawPosX = recuo;

            // Move y-coordinate by the ascent of the layout.
            drawPosY += layout.getAscent();

            // Draw the TextLayout at (drawPosX, drawPosY).
            layout.draw(g2d, drawPosX, drawPosY);

            // Move y-coordinate in preparation for next layout.
            drawPosY += layout.getDescent() + layout.getLeading();
        }
    }
	
	private void startStimulusTimer() {
		stimulusTimer = new Timer(10000, new StimulusTimer());
    	stimulusTimer.start();
	}
	
	public final void putImagesOnBoard(String[] images, Inc inc, int x, int y) {
		for (String nomeImage : images) {
			piece = new Piece(board, Images.createImage(nomeImage), nomeImage, x, y);
			
			y = inc.incY(y);
			x = inc.incX(x);
	    } 
    }
	
	public void putScenicItensOnBoard() {
		int x = 865;
		int y = 0;
		
		Inc inc = new Inc(){
			int incY(int i) { 
				return i + piece.getHeight() + 2;
			}
		};
		
		for (Scene cenario : scenes) {
			piece = new ScenicItem(board, Images.createImage(cenario.getName() + ".gif"), cenario.getName(), Images.createImage(cenario.getName() + "_grande.gif"), x, y, cenario.getX(), cenario.getY());
			
			y = inc.incY(y);
			x = inc.incX(x);
	    } 
    }
	
	public void putCharactersOnBoard(boolean background, boolean playSound) {
		int x = 60;
		int y = 635;
		
		Inc inc = new Inc() {
			int incX(int i) { 
				return i + piece.getWidth() + 15;
			}
		};
		
		int i = 0;		
		for (String personagem : characters) {
			if (!background) {
				piece = new Character(board, Images.createImage(personagem + ".gif"), personagem, x, y);			
			} else {
				piece = new BackgroundCharacter(board, Images.createImage(personagem + ".gif"), personagem, x, y);
			}
			
			if (playSound) {
				piece.setSound(new MidiSound(sounds[i++], true));
				piece.setPlaySound(playSound);
			}
			
			y = inc.incY(y);
			x = inc.incX(x);
	    } 
    }
	
    public void stop() {
    	if (board != null) {
    		board.suspend();
    	}
    }
      
    public void start() {
    	if (board != null) {
        	board.resume();
    	}
    }
      
	public void destroy() {
		if (board != null) {
	    	board.stop();
		}
	}

	public void putAnimalsOnBoard() {
		Image image = Images.createImage("passarinho.gif");			
		new Piece(board, image, "passarinho", 480, 110);
		
		Image image2 = Images.createImage("veado.gif");		
		new Piece(board, image2, "veado", 110, 430);
		
		Image image3 = Images.createImage("cachorrinho.gif");		
		new Piece(board, image3, "cachorrinho", 800, 390);
				
		Image image4 = Images.createImage("esquilo_no_balde.gif");		
		new Piece(board, image4, "esquilo_no_balse", 580, 300);
    }
	
	/**Main method*/
	public static void main(String[] args) {
		CriaContoPlayer applet = new CriaContoPlayer();
		Frame frame;
		frame = new Frame() {      
			private static final long serialVersionUID = -1653465628625769166L;
			protected void processWindowEvent(WindowEvent e) {	        
				super.processWindowEvent(e);
		        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
		          System.exit(0);
		        }
			}
    
			public synchronized void setTitle(String title) {
				super.setTitle(title);
				enableEvents(AWTEvent.WINDOW_EVENT_MASK);
			}
		};
    
		frame.setTitle("Jogador");    
		frame.add(applet, BorderLayout.CENTER);
		applet.init();
		applet.start();
		frame.setSize(1000,800);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((d.width - frame.getSize().width) / 2, (d.height - frame.getSize().height) / 2);
		frame.setVisible(true);
	}
	
	private class NPCTimer implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {	
			if (npc != null) {
				board.removeSpriteFromList(npc);
				board.removeSpritesFromListNow();	
				board.repaint();
				
				npcTimer.stop();
				npcTimer = null;
			}
		}		
	}
	
	private class GameStartTimer implements ActionListener {
		private Piece corujaAsasAcima;
		private Piece corujaAsasMeio;
		private Piece corujaAsasAbaixo;
		private Piece corujaPouso;
		private Piece corujaPousoFala;
		
		public void actionPerformed(ActionEvent arg0) {	
			try {
				switch (startSequence) {
					case 0:
						Image imageCorujaAsasAcima = Images.createImage("Asas_Acima.gif");    		
						corujaAsasAcima = new Piece(board, imageCorujaAsasAcima, "Coruja asas acima", 930, 15);
			    		startSequence++;
			    		break;
					case 1:
						board.removeSpriteFromList(corujaAsasAcima);
						board.removeSpritesFromListNow();
			    		Image imageCorujaAsasMeio = Images.createImage("Asas_Meio.gif");		
						corujaAsasMeio = new Piece(board, imageCorujaAsasMeio, "Coruja asas meio", 750, 85);
						startSequence++;
			    		break;
					case 2:
						board.removeSpriteFromList(corujaAsasMeio);
						board.removeSpritesFromListNow();
			    		Image imageCorujaAsasAbaixo = Images.createImage("Asas_Abaixo.gif");		
						corujaAsasAbaixo = new Piece(board, imageCorujaAsasAbaixo, "Coruja asas abaixo", 600, 155);
						startSequence++;
			    		break;
					case 3:
						board.removeSpriteFromList(corujaAsasAbaixo);
						board.removeSpritesFromListNow();
			    		Image imageCorujaPouso = Images.createImage("Asas_Pouso.gif");		
						corujaPouso = new Piece(board, imageCorujaPouso, "Coruja pouso", 505, 205);
						startSequence++;
						break;
					case 4:
						board.removeSpriteFromList(corujaPouso);
						board.removeSpritesFromListNow();
			    		Image imageCorujaPousoFala = Images.createImage("Asas_Pouso_Fala.gif");		
						corujaPousoFala = new Piece(board, imageCorujaPousoFala, "Coruja pouso fala", 505, 205);
						startSequence++;
						break;
					case 5:
						startSequence++;
						break;
					case 6:
						startSequence++;
						break;
					case 7:
						startSequence++;
						break;
					case 8:
						board.removeSpriteFromList(corujaPousoFala);
						board.removeSpritesFromListNow();
			    		imageCorujaPouso = Images.createImage("Asas_Pouso.gif");		
						corujaPouso = new Piece(board, imageCorujaPouso, "Coruja pouso", 505, 205);
						startSequence++;
						break;
					case 9:
						board.setBackgroundImage("tela_inicial3.jpg");
						imageCorujaPouso = Images.createImage("Asas_Pouso.gif");		
						corujaPouso = new Piece(board, imageCorujaPouso, "Coruja pouso", 505, 205);
						startSequence++;
						break;
					case 10:
						board.setBackgroundImage("tela_inicial4.jpg");
						imageCorujaPouso = Images.createImage("Asas_Pouso.gif");		
						corujaPouso = new Piece(board, imageCorujaPouso, "Coruja pouso", 505, 205);
						startSequence++;
						break;
					case 11:
						board.setBackgroundImage("tela_inicial3.jpg");
						imageCorujaPouso = Images.createImage("Asas_Pouso.gif");		
						corujaPouso = new Piece(board, imageCorujaPouso, "Coruja pouso", 505, 205);
						startSequence++;
						break;
					case 12:
						Controller.stopSound();
						changePhase();
						gameStartTimer.stop();
						gameStartTimer = null;
		        	}
		        } catch (Exception ex) {
		        	ex.printStackTrace();
		        	showMessageDialog("Ocorreu um erro ao iniciar o jogo! Por favor, tente novamente mais tarde!");
		        }
		}
	}
	
	private class StimulusTimer implements ActionListener {  
		public void actionPerformed(ActionEvent arg0) {			
			StimulusResponseBean response = Controller.getNextStimulus();
			if (response != null) {
				Integer stimulusType = response.getStimulusTypeId();
				if (stimulusType != null && stimulusType.compareTo(StimulusBean.SHOW_NPC) == 0) {
					try {
						showNPC(response.getStimulusText());
					} catch (InterruptedException e) {
						//TODO: melhorar tratamento de excecao
						e.printStackTrace();
					}
				} else if (stimulusType != null && stimulusType.compareTo(StimulusBean.CHANGE_PHASE) == 0) {
					changePhase();
				}
			}
		}
	}
	
	private class Inc {
		int incX(int i) {
			return i;
		}
		
		int incY(int i) {
			return i;
		}
	}
}
