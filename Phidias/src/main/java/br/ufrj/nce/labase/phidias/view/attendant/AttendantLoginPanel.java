package br.ufrj.nce.labase.phidias.view.attendant;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.Timer;

import br.ufrj.nce.labase.phidias.communication.bean.SessionListResponseBean;
import br.ufrj.nce.labase.phidias.communication.bean.SessionResponseBean;
import br.ufrj.nce.labase.phidias.controller.Controller;


public class AttendantLoginPanel extends Panel{
	private static final long serialVersionUID = 1L;
	private TextField login;
	private JComboBox player;
	private Button loginButton;
	private Image backgroundImage;
	private Timer playerTimer;
		
	public AttendantLoginPanel(Image backgroundImage, Color backgroundColor) {
		startPlayerTimer();
		
		this.backgroundImage = backgroundImage;
		setLayout(new GridBagLayout());
		
		Label loginLabel = new Label("Nome:");
		loginLabel.setBackground(backgroundColor);
		add(loginLabel, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(1, 1, 1, 1), 0, 0));
        
		login = new TextField();
		login.setPreferredSize(new Dimension(100, 20));
		add(login, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(1, 1, 1, 1), 0, 0));
        
		Label playerLabel = new Label("Jogador:");
		playerLabel.setBackground(backgroundColor);
		add(playerLabel, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(1, 1, 1, 1), 0, 0));
        
		player = new JComboBox();
		player.setPreferredSize(new Dimension(100, 20));
		add(player, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(1, 1, 1, 1), 0, 0));
		
		player.setRenderer(new PatientRenderer());
        
		loginButton = new Button("Entrar");
		add(loginButton, new GridBagConstraints(0, 2, 2, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(1, 1, 1, 1), 0, 0));
	}
	
	private void addPlayersToList() {
		player.removeAllItems();
		SessionListResponseBean sessions = Controller.listOpenSessions();
		for (SessionResponseBean bean : sessions.getSessions()) {
			player.addItem(bean);
		}
	}
	
	private void startPlayerTimer() {
		playerTimer = new Timer(2000, new PlayerTimer());
		playerTimer.start();
	}
	
	public void paint(Graphics g) {
	     super.paint(g);
	     g.drawImage(backgroundImage, 0, 0, null);  
	} 
	
	public void addActionListener(ActionListener listener) {
		loginButton.addActionListener(listener);
	}
	
	public String getLogin() {
		return login.getText();
	}
	
	public int getSessionId() {
		return ((SessionResponseBean) player.getSelectedItem()).getSessionId();
	}
	
	private class PatientRenderer extends JLabel implements ListCellRenderer {
		public Component getListCellRendererComponent(JList list, Object value, int arg2, boolean arg3, boolean arg4) {
			if (value != null) {
				setText(((SessionResponseBean) value).getPatient());
			}
			return this;
		}
		
	}
	
	private class PlayerTimer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			addPlayersToList();
		}
	}
}
