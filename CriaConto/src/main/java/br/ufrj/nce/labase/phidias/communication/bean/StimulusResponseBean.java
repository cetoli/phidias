package br.ufrj.nce.labase.phidias.communication.bean;

import br.ufrj.nce.labase.phidias.communication.container.ActionResponseContainer;

public class StimulusResponseBean extends ActionResponseContainer {
	private Integer stimulusTypeId;
	private String stimulusText;
	
	public Integer getStimulusTypeId() {
		return stimulusTypeId;
	}
	
	public void setStimulusTypeId(Integer stimulusTypeId) {
		this.stimulusTypeId = stimulusTypeId;
	}

	public void setStimulusText(String stimulusText) {
		this.stimulusText = stimulusText;
	}

	public String getStimulusText() {
		return stimulusText;
	}

	
}
