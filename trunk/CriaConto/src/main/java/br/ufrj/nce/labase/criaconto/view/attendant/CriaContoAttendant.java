package br.ufrj.nce.labase.criaconto.view.attendant;

import java.applet.Applet;
import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.Timer;

import br.ufrj.nce.criaconto.images.Images;
import br.ufrj.nce.labase.criaconto.control.Controller;
import br.ufrj.nce.labase.criaconto.view.LoginPanel;
import br.ufrj.nce.labase.phidias.communication.bean.EventResponseBean;

public class CriaContoAttendant extends Applet {
	private static final long serialVersionUID = 1L;
	private Image backgroundImage;
	private TextArea commentsText;
	private TextField stimulusText;
	private TextArea comments;
	private TextArea stimulus;
	private TextArea moves;
	private Timer movesTimer;
	private LoginPanel loginPanel;

	public CriaContoAttendant() {
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
    			}
    		}
    	});
	}
	
	private boolean registerSession() {
    	return Controller.registerSession(loginPanel.getLogin(), LoginPanel.CRIA_CONTO);
    }


	private void startApplication() {
		backgroundImage = Images.createImage("fundoAplicador.jpg");
    	
		setSize(1024, 820);
    	removeAll();
    	
    	Panel p = new Panel();
    	p.setLayout(new GridBagLayout());
		
		Label movesLabel = new Label("Jogadas do paciente");
		p.add(movesLabel, new GridBagConstraints(0, 0, 3, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		moves = new TextArea();
		moves.setRows(10);
		moves.setColumns(80);
		moves.setEditable(false);
		p.add(moves, new GridBagConstraints(0, 1, 3, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		Label commentsLabel = new Label("Entre com os comentarios");
		p.add(commentsLabel, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		commentsText = new TextArea();
		commentsText.setColumns(80);
		commentsText.setRows(3);
		p.add(commentsText, new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		Button ok = new Button("OK");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerComment();
			}
		});
		p.add(ok, new GridBagConstraints(2, 2, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		comments = new TextArea();
		comments.setRows(10);
		comments.setColumns(80);
		comments.setEditable(false);
		p.add(comments, new GridBagConstraints(0, 3, 3, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		Label interventionsLabel = new Label("Entre com os estimulos do NPC:");
		p.add(interventionsLabel, new GridBagConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		stimulusText = new TextField();
		p.add(stimulusText, new GridBagConstraints(1, 4, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		Button ok2 = new Button("OK");
		ok2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerStimulus();
			}
		});
		p.add(ok2, new GridBagConstraints(2, 4, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		stimulus = new TextArea();
		stimulus.setRows(10);
		stimulus.setColumns(80);
		stimulus.setEditable(false);
		p.add(stimulus, new GridBagConstraints(0, 5, 3, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));
		
		Button changePhase = new Button("Mudar fase");
		changePhase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerPhaseChange();
			}
		});
		p.add(changePhase, new GridBagConstraints(0, 6, 3, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));
		
		
		Button exit = new Button("Sair");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (registerSessionEnd()) {
					stop();
				}
			}
		});
		p.add(exit, new GridBagConstraints(0, 7, 3, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));
		
		add(p, BorderLayout.NORTH);
		
		movesTimer = new Timer(2000, new MovesTimer());
		movesTimer.start();
	}

	private void registerComment() {
		String comment = commentsText.getText();
		if (Controller.registerComment(comment)) {
			commentsText.setText("");
			comments.append(comment + "\n");
		}
	}

	private void registerStimulus() {
		String stimulusString = stimulusText.getText();
		if (Controller.registerStimulus(stimulusString)) {
			stimulusText.setText("");
			stimulus.append(stimulusString + "\n");
		}
	}
	
	private boolean registerPhaseChange() {
		return Controller.registerPhaseChange();
	}
	
	private boolean registerSessionEnd() {
		return Controller.registerSessionEnd(LoginPanel.CRIA_CONTO);
	}

	public void stop() {
	}

	public void start() {
	}

	public void destroy() {
	}

	public void paint(Graphics g) {
		super.paint(g);
		
		if (backgroundImage != null) {
			g.drawImage(backgroundImage, 0, 0, null);
		}
	}

	/** Main method */
	public static void main(String[] args) {
		CriaContoAttendant applet = new CriaContoAttendant();
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

		frame.setTitle("Aplicador");
		frame.add(applet, BorderLayout.CENTER);
		applet.init();
		applet.start();
		frame.setSize(1024, 820);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((d.width - frame.getSize().width) / 2, (d.height - frame.getSize().height) / 2);
		frame.setVisible(true);
	}

	private class MovesTimer implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {		
			EventResponseBean response = Controller.getMoves();
			if (response != null && response.getMoves() != null && response.getMoves().size() > 0) {
				for (String move : response.getMoves()) {
					moves.append(move + "\n");
				}
			}
		}
	}
}
