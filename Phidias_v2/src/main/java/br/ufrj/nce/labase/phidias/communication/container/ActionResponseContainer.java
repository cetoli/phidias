package br.ufrj.nce.labase.phidias.communication.container;

import java.io.Serializable;

import br.ufrj.nce.labase.phidias.exception.PhidiasException;

public abstract class ActionResponseContainer implements Serializable {
	private PhidiasException phidiasException;

	private Boolean success;

	public Boolean isSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public PhidiasException getGeneratedException() {
		return this.phidiasException;
	}

	public void setGeneratedException(PhidiasException phidiasException) {
		this.phidiasException = phidiasException;
	}
}
