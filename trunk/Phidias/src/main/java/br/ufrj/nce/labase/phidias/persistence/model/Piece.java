package br.ufrj.nce.labase.phidias.persistence.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pecas")
public class Piece implements java.io.Serializable {
	private static final long serialVersionUID = 203454539017805327L;

	@EmbeddedId
	private PiecePK pk;
	
	@Column(name = "ENX_ID_ENXOVAL")
	private Long pieceCollectionId;
	
	@Column(name = "PEC_JOG_ID_JOGO")
	private Long gameId;
	
	@Column(name = "PEC_NM_PECA")
	private String pieceName;
	
	@Column(name = "PEC_DS_PECA")
	private String pieceDesc;
	
	@Column(name = "PEC_TP_PECA")
	private String pieceType;

	public PiecePK getPk() {
		return pk;
	}

	public void setPk(PiecePK pk) {
		this.pk = pk;
	}

	public Long getPieceCollectionId() {
		return pieceCollectionId;
	}

	public void setPieceCollectionId(Long pieceCollectionId) {
		this.pieceCollectionId = pieceCollectionId;
	}

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public String getPieceName() {
		return pieceName;
	}

	public void setPieceName(String pieceName) {
		this.pieceName = pieceName;
	}

	public String getPieceDesc() {
		return pieceDesc;
	}

	public void setPieceDesc(String pieceDesc) {
		this.pieceDesc = pieceDesc;
	}

	public String getPieceType() {
		return pieceType;
	}

	public void setPieceType(String pieceType) {
		this.pieceType = pieceType;
	}
}
