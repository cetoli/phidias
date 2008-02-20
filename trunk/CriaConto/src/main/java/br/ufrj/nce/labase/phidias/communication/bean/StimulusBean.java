package br.ufrj.nce.labase.phidias.communication.bean;

import br.ufrj.nce.labase.phidias.communication.container.AbstractActionContainer;

public class StimulusBean extends AbstractActionContainer {
	public static final Integer INVOCA_NPC = 1;

	private String id;
	private Integer sessionId;
	private Integer phaseId;
	private Integer stimulusTypeId;
	private String stimulusText;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	public Integer getSessionId() {
		return sessionId;
	}

	public void setPhaseId(Integer phaseId) {
		this.phaseId = phaseId;
	}

	public Integer getPhaseId() {
		return phaseId;
	}

	public void setStimulusTypeId(Integer stimulusTypeId) {
		this.stimulusTypeId = stimulusTypeId;
	}

	public Integer getStimulusTypeId() {
		return stimulusTypeId;
	}

	public void setStimulusText(String stimulusText) {
		this.stimulusText = stimulusText;
	}

	public String getStimulusText() {
		return stimulusText;
	}
}
