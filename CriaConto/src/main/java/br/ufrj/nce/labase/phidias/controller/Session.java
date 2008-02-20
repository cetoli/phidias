package br.ufrj.nce.labase.phidias.controller;

import br.ufrj.nce.labase.phidias.communication.bean.SessionResponseBean;

public class Session {
	private SessionResponseBean session;
	private static Session instance;
	private Integer actualPhase;

	private Session() {
		// session = Controller.registerSession();
	}

	public static Session getInstance() {
		if (instance == null) {
			instance = new Session();
		}

		return instance;
	}

	public Integer getId() {
		return session.getSessionId();
	}

	public Integer getActualPhase() {
		return null;
	}

}
