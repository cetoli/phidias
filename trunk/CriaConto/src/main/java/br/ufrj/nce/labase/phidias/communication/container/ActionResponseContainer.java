package br.ufrj.nce.labase.phidias.communication.container;

public abstract class ActionResponseContainer {
	private Boolean success;

	public Boolean isSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

}
