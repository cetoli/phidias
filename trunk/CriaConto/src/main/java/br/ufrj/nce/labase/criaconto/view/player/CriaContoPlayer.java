package br.ufrj.nce.labase.criaconto.view.player;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import br.ufrj.nce.labase.common.MidiSound;
import br.ufrj.nce.labase.phidias.controller.Controller;
import br.ufrj.nce.labase.phidias.controller.Session;
import br.ufrj.nce.labase.phidias.util.Images;
import br.ufrj.nce.labase.phidias.view.player.BackgroundCharacter;
import br.ufrj.nce.labase.phidias.view.player.GameStartTimer;
import br.ufrj.nce.labase.phidias.view.player.Piece;
import br.ufrj.nce.labase.phidias.view.player.Player;
import br.ufrj.nce.labase.phidias.view.player.Scene;
import br.ufrj.nce.labase.phidias.view.player.ScenicItem;
import br.ufrj.nce.labase.phidias.view.player.Character;

public class CriaContoPlayer extends Player {
	private static final long serialVersionUID = 1L;
	private int startSequence = 0;
    
	private static final String IMAGES_PACKAGE = "br.ufrj.nce.labase.criaconto.images"; 
	
	static final String characters[] = {
        "principe", "branca_de_neve", "cacador_frente", "rainha_ma", "anao1", "anao2", "anao3", "anao4", "anao5", "anao6", "anao7"
    };
    static final String sounds[] = {
        "entrada.midi", "entrada.midi", "entrada.midi", "entrada.midi", "entrada.midi", "entrada.midi", "entrada.midi", "entrada.midi", "entrada.midi", "entrada.midi", "entrada.midi"
    };
    static final Scene scenes[] = {
    	new Scene("castelo", 0, 5), new Scene("ponte", 350, 360), new Scene("casa", 600, 100), new Scene("tronco", 465, 212), new Scene("mina", 570, 400)
    };
    
    public static final int CRIA_CONTO = 1;
	    
    public CriaContoPlayer() { 
    	super(true, CRIA_CONTO, new Color(249, 227, 203));
	}

    public void init() {
    	midiSound = new MidiSound(MidiSound.class.getResourceAsStream("entrada.midi"), true);
    	npcImage = getImage("NPC.gif");
    	loginBackgroundImage = getImage("fundoAplicador.jpg");
    	
    	super.init();
    }
    
    protected void startGame() {
    	Image initialBackground = getImage("tela_inicial.jpg");
		createInitialBackground(initialBackground);             
	}

	private Image getImage(String imageName) {
		return Images.createImage(getImageName(imageName));
	}
    
    protected void startTimer() {
    	startGameStartTimer(new CriaContoGameStartTimer());
    }

    private void startCriaConto() {
    	Controller.stopSound();
		clearGameStartTimer();
		changePhase();
    }
    
    protected void changePhase() {
		super.changePhase();
		
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
		
	    board = createBoard(getImage("fundo.jpg"));
		
	    add(board, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(1, 1, 1, 1), 0, 0));
	    putCharactersOnBoard(true, false);

	    board.start();
	}

	private void secondPhase() {
		setSize(1024, 820);
		removeAll();
		
	    board = createBoard(getImage("fundo.jpg"));
		
	    add(board, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(1, 1, 1, 1), 0, 0));
	    putCharactersOnBoard(true, true);
	    board.start();
	}

	private void thirdPhase() {
		setSize(1048, 820);
		removeAll();
	
		board = createBoard(getImage("fundo2.jpg"));
		
		add(board, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(1, 1, 1, 1), 0, 0));
		putScenicItensOnBoard();
		putCharactersOnBoard(false, true);
		putAnimalsOnBoard();
		board.start();
	}
		
	public final void putImagesOnBoard(String[] images, Inc inc, int x, int y) {
		for (String nomeImage : images) {
			piece = new Piece(board, getImage(nomeImage), nomeImage, x, y);
			
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
			piece = new ScenicItem(board, getImage(cenario.getName() + ".gif"), cenario.getName(), getImage(cenario.getName() + "_grande.gif"), x, y, cenario.getX(), cenario.getY(), 850, 590);
			
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
				piece = new Character(board, getImage(personagem + ".gif"), personagem, x, y, 850, 590);			
			} else {
				piece = new BackgroundCharacter(board, getImage(personagem + ".gif"), personagem, x, y, 850, 590);
			}
			
			if (playSound) {
				piece.setSound(new MidiSound(MidiSound.class.getResourceAsStream(sounds[i++]), true));
				piece.setPlaySound(playSound);
			}
			
			y = inc.incY(y);
			x = inc.incX(x);
	    } 
    }
	
    public void stop() {
    	super.stop();
    }
      
    public void start() {
    	super.start();
    }
      
	public void destroy() {
		super.destroy();
	}

	public void putAnimalsOnBoard() {
		Image image = getImage("passarinho.gif");			
		new Piece(board, image, "passarinho", 480, 110);
		
		Image image2 = getImage("veado.gif");		
		new Piece(board, image2, "veado", 110, 430);
		
		Image image3 = getImage("cachorrinho.gif");		
		new Piece(board, image3, "cachorrinho", 800, 390);
				
		Image image4 = getImage("esquilo_no_balde.gif");		
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
		
	private class CriaContoGameStartTimer extends GameStartTimer {
		private Piece corujaAsasAcima;
		private Piece corujaAsasMeio;
		private Piece corujaAsasAbaixo;
		private Piece corujaPouso;
		private Piece corujaPousoFala;
		
		public void actionPerformed(ActionEvent arg0) {	
			try {
				switch (startSequence) {
					case 0:
						Image imageCorujaAsasAcima = getImage("Asas_Acima.gif");    		
						corujaAsasAcima = new Piece(board, imageCorujaAsasAcima, "Coruja asas acima", 930, 15);
			    		startSequence++;
			    		break;
					case 1:
						board.removeSpriteFromList(corujaAsasAcima);
						board.removeSpritesFromListNow();
			    		Image imageCorujaAsasMeio = getImage("Asas_Meio.gif");		
						corujaAsasMeio = new Piece(board, imageCorujaAsasMeio, "Coruja asas meio", 750, 85);
						startSequence++;
			    		break;
					case 2:
						board.removeSpriteFromList(corujaAsasMeio);
						board.removeSpritesFromListNow();
			    		Image imageCorujaAsasAbaixo = getImage("Asas_Abaixo.gif");		
						corujaAsasAbaixo = new Piece(board, imageCorujaAsasAbaixo, "Coruja asas abaixo", 600, 155);
						startSequence++;
			    		break;
					case 3:
						board.removeSpriteFromList(corujaAsasAbaixo);
						board.removeSpritesFromListNow();
			    		Image imageCorujaPouso = getImage("Asas_Pouso.gif");		
						corujaPouso = new Piece(board, imageCorujaPouso, "Coruja pouso", 505, 205);
						startSequence++;
						break;
					case 4:
						board.removeSpriteFromList(corujaPouso);
						board.removeSpritesFromListNow();
			    		Image imageCorujaPousoFala = getImage("Asas_Pouso_Fala.gif");		
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
			    		imageCorujaPouso = getImage("Asas_Pouso.gif");		
						corujaPouso = new Piece(board, imageCorujaPouso, "Coruja pouso", 505, 205);
						startSequence++;
						break;
					case 9:
						setBoardImage(getImage("tela_inicial3.jpg"), board);
						imageCorujaPouso = getImage("Asas_Pouso.gif");		
						corujaPouso = new Piece(board, imageCorujaPouso, "Coruja pouso", 505, 205);
						startSequence++;
						break;
					case 10:
						setBoardImage(getImage("tela_inicial4.jpg"), board);
						imageCorujaPouso = getImage("Asas_Pouso.gif");		
						corujaPouso = new Piece(board, imageCorujaPouso, "Coruja pouso", 505, 205);
						startSequence++;
						break;
					case 11:
						setBoardImage(getImage("tela_inicial3.jpg"), board);
						imageCorujaPouso = getImage("Asas_Pouso.gif");		
						corujaPouso = new Piece(board, imageCorujaPouso, "Coruja pouso", 505, 205);
						startSequence++;
						break;
					case 12:
						startCriaConto();
		        	}
		        } catch (Exception ex) {
		        	ex.printStackTrace();
		        	showMessageDialog("Ocorreu um erro ao iniciar o jogo! Por favor, tente novamente mais tarde!");
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

	@Override
	public String getImagesPackageName() {
		return IMAGES_PACKAGE;
	}
}
