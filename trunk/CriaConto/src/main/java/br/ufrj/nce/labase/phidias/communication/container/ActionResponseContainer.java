package br.ufrj.nce.labase.phidias.communication.container;

import java.io.Serializable;

public abstract class ActionResponseContainer implements Serializable{
	private Boolean success;

	public Boolean isSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

}
