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
	private Long gameId;
	
	@Column(name = "PEC_ID_PECA")
	private Long pieceId;

	public ActionMovementPK getPk() {
		return pk;
	}

	public void setPk(ActionMovementPK pk) {
		this.pk = pk;
	}

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public Long getPieceId() {
		return pieceId;
	}

	public void setPieceId(Long pieceId) {
		this.pieceId = pieceId;
	}
}
