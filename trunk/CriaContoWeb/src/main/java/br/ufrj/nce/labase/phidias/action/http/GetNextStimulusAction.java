package br.ufrj.nce.labase.phidias.action.http;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufrj.nce.labase.controller.action.http.IAction;
import br.ufrj.nce.labase.phidias.business.StimulusBusiness;
import br.ufrj.nce.labase.phidias.communication.bean.StimulusBean;
import br.ufrj.nce.labase.phidias.communication.bean.StimulusResponseBean;
import br.ufrj.nce.labase.phidias.persistence.model.SessionGamePhaseStimulusType;

public class GetNextStimulusAction implements IAction {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ObjectOutputStream o = null;
		try {
			response.setContentType("application/octet-stream");
			o = new ObjectOutputStream(response.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			Map<String, String> parameterMap = request.getParameterMap();

			StimulusBean container = new StimulusBean();
			container.loadPropertyValues(parameterMap);

			StimulusBusiness stimulusBusiness = new StimulusBusiness();
			SessionGamePhaseStimulusType stimulus = stimulusBusiness
					.getNextStimulus(container);

			StimulusResponseBean evt = new StimulusResponseBean();
			evt.setSuccess(true);
			if (stimulus != null)
				evt.setStimulusTypeId(stimulus.getStimulusTypeId());
			
			o.writeObject(evt);

		} catch (Throwable e) {
			e.printStackTrace();

		} finally {
			try {
				o.flush();
				o.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
