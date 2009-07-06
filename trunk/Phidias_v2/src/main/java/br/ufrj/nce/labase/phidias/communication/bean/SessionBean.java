package br.ufrj.nce.labase.phidias.communication.bean;

import br.ufrj.nce.labase.phidias.communication.container.AbstractActionContainer;


public class SessionBean extends AbstractActionContainer {
	private Integer id;
	private String patientId;
	private String attendantId;
	private Integer gameId;
	private Integer status;

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

	public String getAttendantId() {
		return attendantId;
	}

	public void setAttendantId(String attendantId) {
		this.attendantId = attendantId;
	}

	public Integer getGameId() {
		return gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}
}
