package br.ufrj.nce.labase.phidias.communication.bean;

import java.util.List;

import br.ufrj.nce.labase.phidias.communication.container.ActionResponseContainer;

public class EventResponseBean extends ActionResponseContainer {
	private List<String> moves;

	public List<String> getMoves() {
		return moves;
	}
	
	public void setMoves(List<String> moves) {
		this.moves = moves;
	}
}
