package br.ufrj.nce.labase.phidias.view.player;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import br.ufrj.nce.labase.common.MidiSound;
import br.ufrj.nce.labase.criaconto.images.Images;
import br.ufrj.nce.labase.criaconto.view.LoginPanel;
import br.ufrj.nce.labase.phidias.communication.bean.StimulusBean;
import br.ufrj.nce.labase.phidias.communication.bean.StimulusResponseBean;
import br.ufrj.nce.labase.phidias.controller.Controller;
import br.ufrj.nce.labase.phidias.controller.Session;
import br.ufrj.nce.labase.phidias.exception.PhidiasException;

public abstract class Player extends Applet {
	private static final long serialVersionUID = 1L;
	protected Board board;
	protected Piece piece = null;  
    private LoginPanel loginPanel;
    private Timer stimulusTimer;	
    private Timer gameStartTimer;
    private Timer npcTimer;
    protected String midiSound;
    protected String npcImage;
    protected boolean showNPC;
    
    private static final Hashtable<TextAttribute, Object> stimulusFont =
        									new Hashtable<TextAttribute, Object>();
    static {
    	stimulusFont.put(TextAttribute.FAMILY, "Serif");
    	stimulusFont.put(TextAttribute.SIZE, new Float(10));
    }  
    
    
    public Player(String midiSound, boolean showNPC, String npcImage) {
    	this.midiSound = midiSound;
    	this.showNPC = showNPC;
    	
    	if (showNPC) {
    		this.npcImage = npcImage;
    	}    	
	}

    public void init() {
		setSize(1024, 820);
    	setBackground(Color.WHITE);
    	setLayout(new GridBagLayout());

    	Controller.setCurrentSound(new MidiSound(MidiSound.class.getResourceAsStream(midiSound), true));
    	Controller.startSound();
    	
    	loginPanel = new LoginPanel();
    	loginPanel.setPreferredSize(new Dimension(1024, 820));
    	add(loginPanel, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));
    	
    	loginPanel.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			showMessageDialog("Por favor aguarde!");
    			
    			if (joinSession()) {
    				startGame();
    				startTimer();
    			} else {
    				showMessageDialog("Ocorreu um erro ao iniciar o jogo! Por favor, tente novamente mais tarde!");
    			}
    		}
    	});
    }
    
    protected void showMessageDialog(String message) {
    	JOptionPane.showMessageDialog(this, message);		
    }
    
    protected abstract void startGame();
    
    protected abstract void startTimer();
    
    protected void startGameStartTimer(GameStartTimer timer) {		        
        gameStartTimer = new Timer(1100, timer);
        gameStartTimer.start();        
	}

    protected Board createBoard(String backgroundImageName){
		Board b = new Board(this, getWidth(), getHeight(), backgroundImageName);
		return b;
	}

    protected void createInitialBackground(String backgroundImage) {
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

    protected void changePhase() {
		Session.getInstance().changePhase();
	}
	
	private void showNPC(String stimulus) throws InterruptedException {
		BufferedImage image = (BufferedImage)Images.createImage(npcImage);		

        // Get drawing context
        Graphics2D g2d = image.createGraphics();
        g2d.setFont(new Font("Serif", Font.BOLD, 6));
        
        paintStimulus(g2d, stimulus);
		
		// Dispose context
        g2d.dispose();
        
		Piece npc = new Piece(board, image, "npc", 650, 25);
		
		npcTimer = new Timer(7000, new NPCTimer(board, npc, this));
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
	
	protected void startStimulusTimer() {
		stimulusTimer = new Timer(10000, new StimulusTimer());
    	stimulusTimer.start();
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
		
	public void clearNPCTimer() {
		npcTimer.stop();
		npcTimer = null;
	}	
	
	public void clearGameStartTimer() {
		gameStartTimer.stop();
		gameStartTimer = null;
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
}
