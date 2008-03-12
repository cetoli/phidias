package br.ufrj.nce.labase.phidias.view.player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NPCTimer implements ActionListener {
	private Piece npc;   
	private Board board;
	private Player caller;
	
	public NPCTimer(Board board, Piece npc, Player caller) {
		this.board = board;
		this.npc = npc;
		this.caller = caller;
	}
	
	public void actionPerformed(ActionEvent arg0) {	
		if (npc != null) {
			board.removeSpriteFromList(npc);
			board.removeSpritesFromListNow();	
			board.repaint();
			
			caller.clearNPCTimer();
		}
	}		
}
