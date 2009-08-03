package br.ufrj.nce.labase.phidias.persistence.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PiecePK implements java.io.Serializable {
	private static final long serialVersionUID = -2916232412212086056L;

	@Column(name = "JOG_ID_JOGO")
	private long gameId;
	
	@Column(name = "PEC_ID_PECA")
	private long pieceId;

	public long getGameId() {
		return gameId;
	}

	public void setGameId(long gameId) {
		this.gameId = gameId;
	}

	public long getPieceId() {
		return pieceId;
	}

	public void setPieceId(long pieceId) {
		this.pieceId = pieceId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (gameId ^ (gameId >>> 32));
		result = prime * result + (int) (pieceId ^ (pieceId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PiecePK other = (PiecePK) obj;
		if (gameId != other.gameId)
			return false;
		if (pieceId != other.pieceId)
			return false;
		return true;
	}
}
