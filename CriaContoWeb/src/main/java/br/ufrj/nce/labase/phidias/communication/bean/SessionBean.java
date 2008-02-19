package br.ufrj.nce.labase.phidias.communication.bean;

import br.ufrj.nce.labase.phidias.communication.container.AbstractActionContainer;


public class SessionBean extends AbstractActionContainer {
	private Integer id;
	private String patientId;
	private Integer attendantId;
	private Integer gameId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public Integer getAttendantId() {
		return attendantId;
	}

	public void setAttendantId(Integer attendantId) {
		this.attendantId = attendantId;
	}

	public Integer getGameId() {
		return gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}
}
