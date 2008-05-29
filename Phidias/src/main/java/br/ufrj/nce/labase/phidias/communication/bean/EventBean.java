package br.ufrj.nce.labase.phidias.communication.bean;

import br.ufrj.nce.labase.phidias.communication.EventHelper;
import br.ufrj.nce.labase.phidias.communication.container.AbstractActionContainer;

public class EventBean extends AbstractActionContainer implements EventHelper {
	private String id;
	private Integer sessionId;
	private Integer phaseId;
	private Integer actionTypeId;
	private long moveTime;
	private Boolean validMove;
	private String object1;
	private String object2;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	public Integer getPhaseId() {
		return phaseId;
	}

	public void setPhaseId(Integer phaseId) {
		this.phaseId = phaseId;
	}

	public Integer getActionTypeId() {
		return actionTypeId;
	}

	public void setActionTypeId(Integer actionTypeId) {
		this.actionTypeId = actionTypeId;
	}

	public long getMoveTime() {
		return moveTime;
	}

	public void setMoveTime(long moveTime) {
		this.moveTime = moveTime;
	}

	public String getObject1() {
		return object1;
	}

	public void setObject1(String object1) {
		this.object1 = object1;
	}

	public String getObject2() {
		return object2;
	}

	public void setObject2(String object2) {
		this.object2 = object2;
	}

	public Boolean getValidMove() {
		return validMove;
	}

	public void setValidMove(Boolean validMove) {
		this.validMove = validMove;
	}
}
