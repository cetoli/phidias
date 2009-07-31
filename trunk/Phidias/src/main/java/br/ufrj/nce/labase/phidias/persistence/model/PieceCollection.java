package br.ufrj.nce.labase.phidias.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "enxoval")
public class PieceCollection implements java.io.Serializable {
	private static final long serialVersionUID = -6888230773194990029L;

	@Id
	@Column(name = "ENX_ID_ENXOVAL")
	private long pieceCollectionId;
	
	@Column(name = "ENX_NM_ENXOVAL")
	private String pieceCollectionName;
	
	@Column(name = "ENX_DS_ENXOVAL")
	private String pieceCollectionDesc;
	
	@Column(name = "ENX_TP_ENXOVAL")
	private String pieceCollectionType;
	
	@Column(name = "ENX_TP_NIV_REP_SIMB")
	private String simbType;

	public long getPieceCollectionId() {
		return pieceCollectionId;
	}

	public void setPieceCollectionId(long pieceCollectionId) {
		this.pieceCollectionId = pieceCollectionId;
	}

	public String getPieceCollectionName() {
		return pieceCollectionName;
	}

	public void setPieceCollectionName(String pieceCollectionName) {
		this.pieceCollectionName = pieceCollectionName;
	}

	public String getPieceCollectionDesc() {
		return pieceCollectionDesc;
	}

	public void setPieceCollectionDesc(String pieceCollectionDesc) {
		this.pieceCollectionDesc = pieceCollectionDesc;
	}

	public String getPieceCollectionType() {
		return pieceCollectionType;
	}

	public void setPieceCollectionType(String pieceCollectionType) {
		this.pieceCollectionType = pieceCollectionType;
	}

	public String getSimbType() {
		return simbType;
	}

	public void setSimbType(String simbType) {
		this.simbType = simbType;
	}
}
