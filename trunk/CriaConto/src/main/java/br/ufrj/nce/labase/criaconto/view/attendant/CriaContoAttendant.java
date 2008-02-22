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
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.Timer;

import br.ufrj.nce.criaconto.images.Images;
import br.ufrj.nce.labase.criaconto.control.Controller;
import br.ufrj.nce.labase.phidias.communication.CommunicationProtocol;
import br.ufrj.nce.labase.phidias.communication.bean.EventBean;
import br.ufrj.nce.labase.phidias.communication.bean.EventResponseBean;

public class CriaContoAttendant extends Applet implements ActionListener {
	private static final long serialVersionUID = 1L;
	private Image backgroundImage;
	private TextField sessionText;
	private TextField phaseText;
	private TextArea commentsText;
	private TextField stimulusText;
	private TextArea comments;
	private TextArea stimulus;
	private TextArea moves;
	private Timer timer;

	public CriaContoAttendant() {
		timer = new Timer(2000, this);
		timer.start();
	}

	public void init() {
		setSize(1024, 768);
		setBackground(Color.WHITE);
		setLayout(new GridBagLayout());

		backgroundImage = Images.createImage("fundoAplicador.jpg");

		Label sessionLabel = new Label("Identificador da Sessão");
		add(sessionLabel, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		sessionText = new TextField();
		sessionText.setPreferredSize(new Dimension(20, 20));
		add(sessionText, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		Label phaseLabel = new Label("Identificador da Fase");
		add(phaseLabel, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		phaseText = new TextField();
		phaseText.setPreferredSize(new Dimension(20, 20));
		add(phaseText, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		Label movesLabel = new Label("Jogadas do paciente");
		add(movesLabel, new GridBagConstraints(0, 2, 3, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		moves = new TextArea();
		moves.setRows(10);
		moves.setColumns(50);
		moves.setEditable(false);
		add(moves, new GridBagConstraints(0, 3, 3, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		Label commentsLabel = new Label("Entre com os comentarios");
		add(commentsLabel, new GridBagConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		commentsText = new TextArea();
		commentsText.setColumns(100);
		commentsText.setRows(3);
		commentsText.setPreferredSize(new Dimension(525, 20));
		add(commentsText, new GridBagConstraints(1, 4, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		Button ok = new Button("OK");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerComment();
			}
		});
		add(ok, new GridBagConstraints(2, 4, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		comments = new TextArea();
		comments.setRows(10);
		comments.setColumns(100);
		comments.setEditable(false);
		add(comments, new GridBagConstraints(0, 5, 3, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		Label interventionsLabel = new Label("Entre com os estimulos do NPC:");
		add(interventionsLabel, new GridBagConstraints(0, 6, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		stimulusText = new TextField();
		stimulusText.setPreferredSize(new Dimension(525, 20));
		add(stimulusText, new GridBagConstraints(1, 6, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		Button ok2 = new Button("OK");
		ok2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerStimulus();
			}
		});
		add(ok2, new GridBagConstraints(2, 6, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		stimulus = new TextArea();
		stimulus.setRows(10);
		stimulus.setColumns(100);
		stimulus.setEditable(false);
		add(stimulus, new GridBagConstraints(0, 7, 3, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));
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

	public void stop() {
	}

	public void start() {
	}

	public void destroy() {
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(backgroundImage, 0, 0, null);
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
		frame.setSize(1000, 800);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((d.width - frame.getSize().width) / 2, (d.height - frame.getSize().height) / 2);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {
		EventBean event = new EventBean();
		Integer phaseId = ((phaseText.getText() != null && phaseText.getText().trim().length() > 0) ? new Integer(phaseText.getText()) : null);
		Integer sessionId = ((sessionText.getText() != null && phaseText.getText().trim().length() > 0) ? new Integer(sessionText.getText()) : null);
		event.setPhaseId(phaseId);
		event.setSessionId(sessionId);

		if (phaseId != null && sessionId != null) {
			EventResponseBean response = (EventResponseBean) CommunicationProtocol.execute(CommunicationProtocol.GET_MOVES_ACTION, event);
			if (response != null && response.getMoves() != null && response.getMoves().size() > 0) {
				for (String move : response.getMoves()) {
					moves.append(move + "\n");
				}
			}
		}
	}
}
