package br.ufrj.nce.labase.phidias.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ActionMovementPK implements Serializable {
	private static final long serialVersionUID = -385382439380465401L;

	@Column(name = "ACJ_ID_ACAO")
	private int actionId;
	
	@Column(name = "JGD_ID_JOGADA")
	private int movementId;

	public int getActionId() {
		return actionId;
	}

	public void setActionId(int actionId) {
		this.actionId = actionId;
	}

	public int getMovementId() {
		return movementId;
	}

	public void setMovementId(int movementId) {
		this.movementId = movementId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (actionId ^ (actionId >>> 32));
		result = prime * result + (int) (movementId ^ (movementId >>> 32));
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
		ActionMovementPK other = (ActionMovementPK) obj;
		if (actionId != other.actionId)
			return false;
		if (movementId != other.movementId)
			return false;
		return true;
	}
}
