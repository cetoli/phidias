package br.ufrj.nce.labase.phidias.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "acao_jogo_jogada")
public class ActionMovement implements Serializable {
	private static final long serialVersionUID = 8982136559978323590L;

	@EmbeddedId
	private ActionMovementPK pk;
	
	@Column(name = "JOG_ID_JOGO")
	private Integer gameId;
	
	@Column(name = "PEC_ID_PECA")
	private Integer pieceId;

	public ActionMovementPK getPk() {
		return pk;
	}

	public void setPk(ActionMovementPK pk) {
		this.pk = pk;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public int getPieceId() {
		return pieceId;
	}

	public void setPieceId(int pieceId) {
		this.pieceId = pieceId;
	}
}
