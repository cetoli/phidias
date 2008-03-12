package br.ufrj.nce.labase.phidias.view.attendant;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import br.ufrj.nce.labase.criaconto.control.Controller;
import br.ufrj.nce.labase.criaconto.images.Images;
import br.ufrj.nce.labase.criaconto.view.LoginPanel;
import br.ufrj.nce.labase.phidias.communication.bean.EventResponseBean;
import br.ufrj.nce.labase.phidias.controller.Session;
import br.ufrj.nce.labase.phidias.exception.PhidiasException;

public abstract class Attendant extends Applet {
	private static final long serialVersionUID = 1L;
	private Image backgroundImage;	
	private String background;
	private LoginPanel loginPanel;
	private Timer movesTimer;
	protected Panel mainPanel;
	
	public Attendant(String background) {
		this.background = background;
	}

	public void init() {
		setSize(1024, 820);
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		
		loginPanel = new LoginPanel();
    	loginPanel.setPreferredSize(new Dimension(1024, 820));
    	add(loginPanel, BorderLayout.CENTER);
    	
    	loginPanel.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			if (registerSession()) {
    				startApplication();
    				Session.getInstance().changePhase();    				
    				startMoverTimer();
    			} else {
    				showMessageDialog("Ocorreu um erro ao iniciar o aplicativo! Por favor, tente novamente mais tarde!");
    			}
    		}
    	});
	}
	
	protected void showMessageDialog(String message) {
    	JOptionPane.showMessageDialog(this, message);	
    }
	
	private boolean registerSession() {
    	try {
    		return Controller.registerSession(loginPanel.getLogin(), LoginPanel.CRIA_CONTO);
    	} catch (PhidiasException ex) {
			showMessageDialog("Erro ao iniciar sessão!");
			return false;
		}
    }

	protected void startApplication() {
		backgroundImage = Images.createImage(background);
    	
		setSize(1024, 820);
    	removeAll();
    	
    	mainPanel = new Panel() {
    		public void paint(Graphics g) {
    			super.paint(g);
    			
    			if (backgroundImage != null) {
    				g.drawImage(backgroundImage, 0, 0, null);
    			}
    		}
    	};
    	
    	mainPanel.setLayout(new GridBagLayout());
    	mainPanel.setSize(1024, 820);	
	}

	protected void startMoverTimer() {
		movesTimer = new Timer(4000, new MovesTimer());
		movesTimer.start();
	}
	
	protected boolean registerComment(String comment) {
		try { 
			return Controller.registerComment(comment);
		} catch (PhidiasException ex) {
			showMessageDialog("Erro ao inserir comentário!");
			return false;
		}
	}

	protected boolean registerStimulus(String stimulus) {
		try {
			return Controller.registerStimulus(stimulus);
		} catch (PhidiasException ex) {
			showMessageDialog("Erro ao enviar estímulo!");
			return false;
		}
	}
	
	protected boolean registerPhaseChange() {
		boolean success = false;
		try {
			success = Controller.registerPhaseChange();
			Session.getInstance().changePhase();
		} catch (PhidiasException ex) {
			showMessageDialog("Erro ao enviar solicitação de mudança de fase!");
		}
		return success;
	}
	
	protected boolean registerSessionEnd() {
		try {
			return Controller.registerSessionEnd(LoginPanel.CRIA_CONTO);
		} catch (PhidiasException ex) {
			showMessageDialog("Erro ao encerrar sessão!");
			return false;
		}
	}
	
	protected abstract void appendMove(String move);

	public void stop() {
	}

	public void start() {
	}

	public void destroy() {
	}

	private class MovesTimer implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {		
			try {
				EventResponseBean response = Controller.getMoves();
				if (response != null && response.getMoves() != null && response.getMoves().size() > 0) {
					for (String move : response.getMoves()) {
						appendMove(move);
					}
				}
			} catch (PhidiasException ex) {
				showMessageDialog("Erro ao recuperar as jogadas do paciente!");
			}
		}
	}
}
