package br.ufrj.nce.labase.criaconto.view.player;

import java.applet.Applet;
import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.Timer;

import br.ufrj.nce.criaconto.images.Images;
import br.ufrj.nce.labase.common.MidiSound;
import br.ufrj.nce.labase.criaconto.control.Controller;
import br.ufrj.nce.labase.phidias.communication.CommunicationProtocol;
import br.ufrj.nce.labase.phidias.communication.bean.StimulusBean;
import br.ufrj.nce.labase.phidias.communication.bean.StimulusResponseBean;
import br.ufrj.nce.labase.phidias.controller.Session;
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
    private MidiSound sound;
    private Piece npc;
    
    
    static final String characters[] = {
        "principe", "branca_de_neve", "cacador_frente", "rainha_ma", "anao1", "anao2", "anao3", "anao4", "anao5", "anao6", "anao7"
    };
    static final Scene scenes[] = {
    	new Scene("castelo", 0, 5), new Scene("ponte", 350, 360), new Scene("casa", 600, 100), new Scene("tronco", 465, 212), new Scene("mina", 570, 400)
    };
    
    public CriaContoPlayer() { 
    	
	}

    public void init() {
		setSize(1024, 820);
    	setBackground(Color.WHITE);
    	setLayout(new GridBagLayout());

    	loginPanel = new LoginPanel();
    	loginPanel.setPreferredSize(new Dimension(1024, 830));
    	add(loginPanel, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));
    	
    	loginPanel.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			startGame();
    		}
    	});
    }
    
    private boolean registerSession() {
    	return Controller.registerSession(1, 1, loginPanel.getLogin());
    }

	private void startGame() {
		if (!registerSession()) {
			return;
		}
		
		createInitialBackground("tela_inicial.jpg");
        
        gameStartTimer = new Timer(1500, new GameStartTimer());
        gameStartTimer.start();        
	}

	private void createInitialBackground(String backgroundImage) {
		setSize(1024, 820);
    	removeAll();
		
    	board = createBoard(backgroundImage);
    	
        add(board, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(1, 1, 1, 1), 0, 0));
        board.start();
	}

	private void showNPC() throws InterruptedException {
		Image npcImage = Images.createImage("Asas_Pouso_Fala.gif");		
		Piece npc = new Piece(board, npcImage, "npc", 800, 105);
		
		npcTimer = new Timer(5000, new NPCTimer());
		npcTimer.start();
	}
	
	private void firstPhase() {
		setSize(1024, 820);
    	removeAll();
		
        board = createBoard("fundo.jpg");
    	
        add(board, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(1, 1, 1, 1), 0, 0));
        putPersonagensOnBoard(true);
        board.start();
        
        stimulusTimer = new Timer(10000, new StimulusTimer());
    	stimulusTimer.start();
        
        sound = new MidiSound("102.mid", true);
        sound.start();
	}
    
    private Board createBoard(String backgroundImageName){
    	Board b = new Board(this, getWidth(), getHeight(), backgroundImageName);
    	return b;
    }
    
	public final void putImagesOnBoard(String[] images, Inc inc, int x, int y) {
		for (String nomeImage : images) {
			piece = new Piece(board, Images.createImage(nomeImage), nomeImage, x, y);
			
			y = inc.incY(y);
			x = inc.incX(x);
	    } 
    }
	
	public void putCenariosOnBoard() {
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
	
	public void putPersonagensOnBoard(boolean background) {
		int x = 60;
		int y = 635;
		
		Inc inc = new Inc() {
			int incX(int i) { 
				return i + piece.getWidth() + 15;
			}
		};
		
		for (String personagem : characters) {
			piece = new Character(board, Images.createImage(personagem + ".gif"), personagem, x, y);				
			piece.setBackground(true);
			
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

	public void putAnimaisOnBoard() {
		Image image = Images.createImage("passarinho.gif");			
		Piece piece = new Piece(board, image, "passarinho", 480, 110);
		
		Image image2 = Images.createImage("veado.gif");		
		Piece piece2 = new Piece(board, image2, "veado", 110, 430);
		
		Image image3 = Images.createImage("cachorrinho.gif");		
		Piece piece3 = new Piece(board, image3, "cachorrinho", 800, 390);
				
		Image image4 = Images.createImage("esquilo_no_balde.gif");		
		Piece piece4 = new Piece(board, image4, "esquilo_no_balse", 580, 300);
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
			board.removeSpriteFromList(npc);
			board.removeSpritesFromListNow();
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
						gameStartTimer.stop();
						gameStartTimer = null;
						firstPhase();
		        	}
		        } catch (Exception e) {
		        	e.printStackTrace();
		        }
		}
	}
	private class StimulusTimer implements ActionListener {  
		public void actionPerformed(ActionEvent arg0) {			
			StimulusBean stimulus = new StimulusBean();
			
			stimulus.setPhaseId(Session.getInstance().getCurrentPhase());
			stimulus.setSessionId(Session.getInstance().getId());
	
			StimulusResponseBean response = (StimulusResponseBean) CommunicationProtocol.execute(CommunicationProtocol.GET_NEXT_STIMULUS_ACTION, stimulus);
			Integer stimulusType = response.getStimulusTypeId();
			if (stimulusType != null) {
				if (stimulusType.compareTo(StimulusBean.SHOW_NPC) == 0) {
					try {
						showNPC();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else if (stimulusType.compareTo(StimulusBean.CHANGE_PHASE) == 0) {
					if (Session.getInstance().getCurrentPhase() == 1) {
						setSize(1048, 820);
				    	removeAll();
		
				    	board = createBoard("fundo2.jpg");
				    	
				        add(board, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(1, 1, 1, 1), 0, 0));
				        putCenariosOnBoard();
				        putPersonagensOnBoard(false);
				        putAnimaisOnBoard();
				        board.start();
				        
				        Session.getInstance().changePhase();
					}
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
