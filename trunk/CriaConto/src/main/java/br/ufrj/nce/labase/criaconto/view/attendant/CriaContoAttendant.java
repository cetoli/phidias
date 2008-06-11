package br.ufrj.nce.labase.criaconto.view.attendant;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import br.ufrj.nce.labase.phidias.controller.Session;
import br.ufrj.nce.labase.phidias.util.Images;
import br.ufrj.nce.labase.phidias.view.attendant.Attendant;

public class CriaContoAttendant extends Attendant {
	private static final long serialVersionUID = 1L;
	private Color backgroundColor = new Color(249, 227, 203);
	private TextArea commentsText;
	private TextField stimulusText;
	private TextArea comments;
	private TextArea stimulus;
	private TextArea moves;
	private Button changePhase;
	
	private static final String IMAGES_PACKAGE = "br.ufrj.nce.labase.criaconto.images"; 
	
	public static final int CRIA_CONTO = 1;
	
	public CriaContoAttendant() {
		super(CRIA_CONTO, new Color(249, 227, 203));
	}

	public void init() {
		backgroundImage = Images.createImage(getImageName("fundoAplicador.jpg"));
    	loginBackgroundImage = Images.createImage(getImageName("fundoAplicador.jpg"));
    	
    	super.init();
	}	
	
	protected void appendMove(String move) {
		moves.append(move + "\n");
	}
	
	protected void startApplication() {
		super.startApplication();
		
		Label patientLabel = new Label("Paciente: " + Session.getInstance().getPatient());
		patientLabel.setBackground(backgroundColor);
		mainPanel.add(patientLabel, new GridBagConstraints(0, 0, 3, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));
		
		Label movesLabel = new Label("Jogadas do paciente");
		movesLabel.setBackground(backgroundColor);
		mainPanel.add(movesLabel, new GridBagConstraints(0, 1, 3, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		moves = new TextArea();
		moves.setRows(8);
		moves.setColumns(80);
		moves.setEditable(false);
		mainPanel.add(moves, new GridBagConstraints(0, 2, 3, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		moves.append(" ------ Fase " + Session.getInstance().getCurrentPhase() + " ------------- \n");
		
		Label commentsLabel = new Label("Entre com os comentarios");
		commentsLabel.setBackground(backgroundColor);
		mainPanel.add(commentsLabel, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		commentsText = new TextArea();
		commentsText.setColumns(80);
		commentsText.setRows(3);
		mainPanel.add(commentsText, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		Button ok = new Button("OK");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerComment();
			}
		});
		mainPanel.add(ok, new GridBagConstraints(2, 3, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		comments = new TextArea();
		comments.setRows(8);
		comments.setColumns(80);
		comments.setEditable(false);
		mainPanel.add(comments, new GridBagConstraints(0, 4, 3, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		comments.append(" ------ Fase " + Session.getInstance().getCurrentPhase() + " ------------- \n");
		
		Label interventionsLabel = new Label("Entre com os estimulos do NPC:");
		interventionsLabel.setBackground(backgroundColor);
		mainPanel.add(interventionsLabel, new GridBagConstraints(0, 5, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		stimulusText = new TextField();
		mainPanel.add(stimulusText, new GridBagConstraints(1, 5, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		Button ok2 = new Button("OK");
		ok2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerStimulus();
			}
		});
		mainPanel.add(ok2, new GridBagConstraints(2, 5, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		stimulus = new TextArea();
		stimulus.setRows(8);
		stimulus.setColumns(80);
		stimulus.setEditable(false);
		mainPanel.add(stimulus, new GridBagConstraints(0, 6, 3, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));
		
		stimulus.append(" ------ Fase " + Session.getInstance().getCurrentPhase() + " ------------- \n");
		
		changePhase = new Button("Mudar fase");
		changePhase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerPhaseChange();
				comments.append(" ------ Fase " + Session.getInstance().getCurrentPhase() + " ------------- \n");	
				stimulus.append(" ------ Fase " + Session.getInstance().getCurrentPhase() + " ------------- \n");
				moves.append(" ------ Fase " + Session.getInstance().getCurrentPhase() + " ------------- \n");
			}
		});
		mainPanel.add(changePhase, new GridBagConstraints(0, 7, 3, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));
			
		add(mainPanel, BorderLayout.CENTER);
		
		repaint();
	}
	
	protected boolean registerPhaseChange() {
		boolean ok = super.registerPhaseChange();
		if (ok) {
			if (Session.getInstance().getCurrentPhase() == 4) {
				changePhase.setLabel("Encerrar jogo");
			} else if (Session.getInstance().getCurrentPhase() == 5) {
				registerSessionEnd();
				setGameOver(true);
			}
		}
		
		return ok;
	}
	
	protected void registerComment() {
		if (super.registerComment(commentsText.getText())) {
			comments.append(commentsText.getText() + "\n");	
			commentsText.setText("");			
		} 
	}
	
	protected void registerStimulus() {
		if (super.registerStimulus(stimulusText.getText())) {
			stimulus.append(stimulusText.getText() + "\n");
			stimulusText.setText("");
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

	@Override
	public String getImagesPackageName() {
		return IMAGES_PACKAGE;
	}
}
