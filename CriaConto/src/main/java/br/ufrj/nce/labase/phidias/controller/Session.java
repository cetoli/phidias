package br.ufrj.nce.labase.phidias.controller;

import br.ufrj.nce.labase.phidias.communication.bean.SessionResponseBean;
import br.ufrj.nce.labase.phidias.persistence.model.GamePhase;

public class Session {
	private SessionResponseBean session;
	private static Session instance;
	private GamePhase actualPhase;
	
	private Session() {
		//session = Controller.registerSession();
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
		return actualPhase.getId();
	}

}
