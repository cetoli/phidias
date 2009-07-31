package br.ufrj.nce.labase.phidias.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jogada")
public class Movement implements java.io.Serializable {
	private static final long serialVersionUID = -798703463166593772L;

	@Id
	@Column(name = "JGD_ID_JOGADA")
	private long movementId;
	
	@Column(name = "JGD_DS_JOGADA")
	private String movementDesc;

	public long getMovementId() {
		return movementId;
	}

	public void setMovementId(long movementId) {
		this.movementId = movementId;
	}

	public String getMovementDesc() {
		return movementDesc;
	}

	public void setMovementDesc(String movementDesc) {
		this.movementDesc = movementDesc;
	}
}
