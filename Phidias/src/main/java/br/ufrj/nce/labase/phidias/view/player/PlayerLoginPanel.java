package br.ufrj.nce.labase.phidias.view.player;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionListener;

public class PlayerLoginPanel extends Panel{
	private static final long serialVersionUID = 1L;
	private TextField login;
	private Button loginButton;
	private Image backgroundImage;
	
	public PlayerLoginPanel(Image backgroundImage, Color backgroundColor) {
		this.backgroundImage = backgroundImage;
    	
    	setLayout(new GridBagLayout());
		
		Label loginLabel = new Label("Nome:");
		loginLabel.setBackground(backgroundColor);
		add(loginLabel, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(1, 1, 1, 1), 0, 0));
        
		login = new TextField();
		login.setPreferredSize(new Dimension(100, 20));
		add(login, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(1, 1, 1, 1), 0, 0));
        
		loginButton = new Button("Entrar");
		add(loginButton, new GridBagConstraints(0, 1, 2, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(1, 1, 1, 1), 0, 0));
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
}
