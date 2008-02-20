package br.ufrj.nce.labase.phidias.communication.bean;

import br.ufrj.nce.labase.phidias.communication.container.ActionResponseContainer;

public class SessionResponseBean extends ActionResponseContainer {
	private Integer sessionId;

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}
}
