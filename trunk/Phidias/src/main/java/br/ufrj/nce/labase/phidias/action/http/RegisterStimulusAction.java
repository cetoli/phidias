package br.ufrj.nce.labase.phidias.action.http;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import br.ufrj.nce.labase.controller.action.http.IAction;
import br.ufrj.nce.labase.phidias.business.StimulusBusiness;
import br.ufrj.nce.labase.phidias.communication.bean.StimulusBean;
import br.ufrj.nce.labase.phidias.communication.bean.StimulusResponseBean;
import br.ufrj.nce.labase.phidias.exception.PhidiasException;

public class RegisterStimulusAction implements IAction {

	public void execute(Map<String, String> requestParameterMap, ObjectOutputStream objectOutputStream) {
		StimulusResponseBean evt = new StimulusResponseBean();

		try {
			StimulusBean container = new StimulusBean();
			container.loadPropertyValues(requestParameterMap);

			StimulusBusiness stimulus = new StimulusBusiness();
			stimulus.registerStimulus(container);

			evt.setSuccess(true);

		} catch (Throwable e) {
			e.printStackTrace();
			evt.setSuccess(false);
			evt.setGeneratedException(new PhidiasException(e));
		}
		finally{
			try {
				objectOutputStream.writeObject(evt);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
