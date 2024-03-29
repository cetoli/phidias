package br.ufrj.nce.labase.phidias.view.player;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.ScrollPane;
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

import baklava.Sprite;
import br.ufrj.nce.labase.common.MidiSound;
import br.ufrj.nce.labase.phidias.communication.bean.StimulusBean;
import br.ufrj.nce.labase.phidias.communication.bean.StimulusResponseBean;
import br.ufrj.nce.labase.phidias.controller.Controller;
import br.ufrj.nce.labase.phidias.controller.Session;
import br.ufrj.nce.labase.phidias.exception.PhidiasException;

public abstract class Player extends Applet {
	private static final long serialVersionUID = 1L;
	private PlayerLoginPanel loginPanel;
    private Timer stimulusTimer;	
    private Timer gameStartTimer;
    private Timer npcTimer;
    private Timer sessionTimer;
    private Timer gameOverTimer;
   
    protected Image loginBackgroundImage;    
    protected Board board;
	protected Piece piece = null;  
    protected MidiSound midiSound;
    protected Image npcImage;
    protected boolean showNPC;
    protected Color backgroundColor;
    
    private ScrollPane mainPanel;
    
    private String imagesPackageName;
    
    private static final Hashtable<TextAttribute, Object> stimulusFont =
        new Hashtable<TextAttribute, Object>();
    
    static {
    	stimulusFont.put(TextAttribute.FAMILY, "Serif");
    	stimulusFont.put(TextAttribute.SIZE, new Float(10));
    }      
    
    public Player(boolean showNPC, int game, Color backgroundColor) {
    	this.showNPC = showNPC;
    	this.backgroundColor = backgroundColor;
    	Session.getInstance().setGame(game);    	   	
	}

    public void init() {    	
		setSize(1024, 820);
    	setBackground(Color.WHITE);
    	setLayout(new GridBagLayout());
    	
    	mainPanel = new ScrollPane(ScrollPane.SCROLLBARS_ALWAYS);
    	add(mainPanel, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));
    	
    	Controller.setCurrentSound(midiSound);
    	Controller.startSound();
    	
    	loginPanel = new PlayerLoginPanel(loginBackgroundImage, backgroundColor);
    	loginPanel.setPreferredSize(new Dimension(1024, 820));
    	mainPanel.add(loginPanel);
    	
    	loginPanel.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			showMessageDialog("Por favor, aguarde enquanto encontramos um Aplicador dispon�vel!");
    			
    			if (registerSession()) {
    				startSessionTimer();
    			} else {
    				showMessageDialog("Ocorreu um erro ao iniciar o jogo! Por favor, tente novamente mais tarde!");
    			}
    		}
    	});
    }
    
    private void startSessionTimer() {
    	sessionTimer = new Timer(5000, new SessionTimer());
    	sessionTimer.start();
    }
    
    private void startGameOverTimer() {
    	gameOverTimer = new Timer(2000, new GameOverTimer());
    	gameOverTimer.start();
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

    protected Board createBoard(Image backgroundImage){
    	mainPanel.removeAll();
		
    	Board b = new Board(this, getWidth(), getHeight());
    	Sprite background = new Sprite(b);
    	background.setImage(backgroundImage);
    	b.setBackgroundImage(background);
    	
    	mainPanel.add(b);
        return b;
	}

    protected void createInitialBackground(Image backgroundImage) {
		setSize(1024, 820);
		
    	board = createBoard(backgroundImage);
    	
    	board.start();
	}

    private boolean registerSession() {
    	try {
    		return Controller.registerSession(loginPanel.getLogin());
    	} catch (PhidiasException ex) {
			showMessageDialog("Erro ao iniciar sess�o!");
			return false;
		}
    }
    
    protected void changePhase() {
		Session.getInstance().changePhase();
	}
	
	protected void showNPC(String stimulus) throws InterruptedException {
		if (!showNPC) {
			return;
		}
		
		BufferedImage npc = (BufferedImage) npcImage;		
		 // Get drawing context
        Graphics2D g2d = npc.createGraphics();
        g2d.setFont(new Font("Serif", Font.BOLD, 6));
        
        paintStimulus(g2d, stimulus);
		
		// Dispose context
        g2d.dispose();
        
		Piece npcSprite = new Piece(board, npc, "npc", 650, 25);
		
		npcTimer = new Timer(7000, new NPCTimer(board, npcSprite, this));
		npcTimer.start();
	}
	
   private void paintStimulus(Graphics2D g2d, String stimulus) {

        LineBreakMeasurer lineMeasurer = null;
        g2d.setColor(Color.BLACK);

        int recuo = 60;
        int yInicial = 5;
        float width = g2d.getDeviceConfiguration().getBounds().width - recuo;
        
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
	
	private void stopSessionTimer() {
		sessionTimer.stop();
		sessionTimer = null;
	}
	
	private void stopGameOverTimer() {
		gameOverTimer.stop();
		gameOverTimer = null;
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
	    	Controller.registerSessionEnd();
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
	
	protected void setBoardImage(Image boardImage, Board board) {
		Sprite sprite = new Sprite(board);
		sprite.setImage(boardImage);
		board.setBackgroundImage(sprite);
	}
	
	private class StimulusTimer implements ActionListener {  
		public void actionPerformed(ActionEvent e) {			
			StimulusResponseBean response = Controller.getNextStimulus();
			if (response != null) {
				Integer stimulusType = response.getStimulusTypeId();
				if (stimulusType != null && stimulusType.compareTo(StimulusBean.SHOW_NPC) == 0) {
					try {
						showNPC(response.getStimulusText());
					} catch (InterruptedException ex) {
						//TODO: melhorar tratamento de excecao
						ex.printStackTrace();
					}
				} else if (stimulusType != null && stimulusType.compareTo(StimulusBean.CHANGE_PHASE) == 0) {
					changePhase();
				}
			}
		}
	}
	
	private class SessionTimer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (Controller.getSessionAttendant()) {
				startGame();
				startTimer();
				stopSessionTimer();
				startGameOverTimer();
			}
		}
	}
	
	private class GameOverTimer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (Controller.getSessionEnded() && Session.getInstance().isGameOver()) {
				showMessageDialog("O Aplicador encerrou o jogo!");
				stopGameOverTimer();
				stop();
			}
		}
	}
	
	public String getImageName(String imageFileName){
		// caching imagesPackageName
		if (this.imagesPackageName == null){
			this.imagesPackageName = "/" + resolvePackageName(this.getImagesPackageName())+"/"; 
		}
		
		StringBuilder name = new StringBuilder();
		name.append(this.imagesPackageName).append(imageFileName);
		return name.toString();
	}
	
	/**
	 * Resolve package names, replacing "." for "/"
	 * @param packageName
	 * @return
	 */
	private String resolvePackageName(String packageName){
		return packageName.replace(".", "/");
	}
	
	public abstract String getImagesPackageName();
}
