package br.ufrj.nce.labase.phidias.action.http;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufrj.nce.labase.controller.action.htp.IAction;
import br.ufrj.nce.labase.phidias.business.StimulusBusiness;
import br.ufrj.nce.labase.phidias.communication.bean.StimulusBean;
import br.ufrj.nce.labase.phidias.communication.bean.StimulusResponseBean;
import br.ufrj.nce.labase.phidias.persistence.model.SessionGamePhaseStimulusType;

public class GetNextStimulusAction implements IAction {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter printWriter = null;
		try {
			response.setContentType("text/html");
			printWriter = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			Map<String, String> parameterMap = request.getParameterMap();

			StimulusBean container = new StimulusBean();
			container.loadPropertyValues(parameterMap);

			StimulusBusiness estimuloBusiness = new StimulusBusiness();
			SessionGamePhaseStimulusType stimulus = estimuloBusiness.getNextStimulus(container);

			printWriter.print("class=" + StimulusResponseBean.class.getName() + ";");
			printWriter.print("success=true;");
			if (stimulus != null)
				printWriter.print("stimulusTypeId="+ stimulus.getStimulusTypeId());

		} catch (Throwable e) {
			printWriter.println("success=false");
		} finally {
			printWriter.flush();
			printWriter.close();
		}
	}

}
