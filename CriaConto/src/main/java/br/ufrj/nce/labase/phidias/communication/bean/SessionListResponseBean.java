package br.ufrj.nce.labase.phidias.communication.bean;

import java.util.List;

import br.ufrj.nce.labase.phidias.communication.container.ActionResponseContainer;

public class SessionListResponseBean extends ActionResponseContainer {
	private List<SessionResponseBean> sessions;

	public List<SessionResponseBean> getSessions() {
		return sessions;
	}

	public void setSessions(List<SessionResponseBean> sessions) {
		this.sessions = sessions;
	}
}
