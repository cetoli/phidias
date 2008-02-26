package br.ufrj.nce.labase.phidias.action.http;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import br.ufrj.nce.labase.controller.action.http.IAction;
import br.ufrj.nce.labase.phidias.business.StimulusBusiness;
import br.ufrj.nce.labase.phidias.communication.bean.StimulusBean;
import br.ufrj.nce.labase.phidias.communication.bean.StimulusResponseBean;
import br.ufrj.nce.labase.phidias.exception.PhidiasException;
import br.ufrj.nce.labase.phidias.persistence.model.SessionGamePhaseStimulusType;

public class GetNextStimulusAction implements IAction {

	public void execute(Map<String, String> requestParameterMap, ObjectOutputStream objectOutputStream) {
		StimulusResponseBean evt = new StimulusResponseBean();

		try {

			StimulusBean container = new StimulusBean();
			container.loadPropertyValues(requestParameterMap);

			StimulusBusiness stimulusBusiness = new StimulusBusiness();
			SessionGamePhaseStimulusType stimulus = stimulusBusiness.getNextStimulus(container);

			evt.setSuccess(true);
			if (stimulus != null)
				evt.setStimulusTypeId(stimulus.getStimulusTypeId());

		} catch (Throwable e) {
			e.printStackTrace();
			evt.setSuccess(false);
			evt.setGeneratedException(new PhidiasException(e));
		} finally {
			try {
				objectOutputStream.writeObject(evt);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
