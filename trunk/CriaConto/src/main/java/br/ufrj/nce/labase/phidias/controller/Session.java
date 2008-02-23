package br.ufrj.nce.labase.phidias.controller;

import br.ufrj.nce.labase.phidias.communication.bean.SessionResponseBean;

public class Session {
	private SessionResponseBean session;
	private static Session instance;
	private Integer currentPhase = 0;

	private Session() {
	}
	
	public void setSessionBean(SessionResponseBean sessionBean) {
		session = sessionBean;
	}

	public static Session getInstance() {
		if (instance == null) {
			instance = new Session();
		}

		return instance;
	}

	public Integer getId() {
		if (session == null) {
			return null;
		}
		return session.getSessionId();
	}

	public Integer getCurrentPhase() {
		return 1;
	}
	
	public void setCurrentPhase(Integer phase) {
		currentPhase = phase;
	}
	
	public void changePhase() {
		currentPhase++;
	}

}
