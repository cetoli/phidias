package br.ufrj.nce.labase.phidias.communication.bean;

import java.util.Date;

import br.ufrj.nce.labase.phidias.communication.container.ActionResponseContainer;

public class SessionResponseBean extends ActionResponseContainer {
	private Integer sessionId;
	private String patient;
	private String attendant;
	private Date sessionEndDate;
	
	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

	public String getPatient() {
		return patient;
	}

	public void setAttendant(String attendant) {
		this.attendant = attendant;
	}

	public String getAttendant() {
		return attendant;
	}

	public void setSessionEndDate(Date sessionEndDate) {
		this.sessionEndDate = sessionEndDate;
	}

	public Date getSessionEndDate() {
		return sessionEndDate;
	}
}
