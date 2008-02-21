package br.ufrj.nce.labase.phidias.action.http;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufrj.nce.labase.controller.action.http.IAction;
import br.ufrj.nce.labase.phidias.business.EventBusiness;
import br.ufrj.nce.labase.phidias.communication.bean.EventBean;
import br.ufrj.nce.labase.phidias.communication.bean.EventResponseBean;

public class RegisterEventAction implements IAction {

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

			EventBean container = new EventBean();
			container.loadPropertyValues(parameterMap);

			EventBusiness event = new EventBusiness();
			event.registerEvent(container);

			EventResponseBean evt = new EventResponseBean();
			evt.setSuccess(true);

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
