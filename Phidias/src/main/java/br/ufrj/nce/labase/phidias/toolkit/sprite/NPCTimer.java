package br.ufrj.nce.labase.phidias.toolkit.sprite;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufrj.nce.labase.phidias.toolkit.GameBoard;

/**
 * Timer used by the GameBoard to control the total time the NPC will be 
 * displayed in the board.
 * An ActionListener that must be used with an instance of javax.swing.Timer.  to fire an ActionEvent.
 * Whenever the actionPerformed is called, the NPC is hidden.
 * 
 * @author Sabrina Bettini
 * 
 */
public class NPCTimer implements ActionListener {
	private GameBoard board;
	
	public NPCTimer(GameBoard board) {
		this.board = board;
	}	
	
	public void actionPerformed(ActionEvent e) {
		if (board != null) {
			board.hideNpc();
		}
	}
}
