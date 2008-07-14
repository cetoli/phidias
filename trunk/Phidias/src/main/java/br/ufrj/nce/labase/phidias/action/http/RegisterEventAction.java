package br.ufrj.nce.labase.phidias.action.http;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import br.ufrj.nce.labase.phidias.business.EventBusiness;
import br.ufrj.nce.labase.phidias.communication.bean.EventBean;
import br.ufrj.nce.labase.phidias.communication.bean.EventResponseBean;
import br.ufrj.nce.labase.phidias.controller.action.http.IAction;
import br.ufrj.nce.labase.phidias.exception.PhidiasException;

public class RegisterEventAction implements IAction {

	public void execute(Map<String, String> requestParameterMap, ObjectOutputStream objectOutputStream) {
		EventResponseBean evt = new EventResponseBean();

		try {
			EventBean container = new EventBean();
			container.loadPropertyValues(requestParameterMap);

			EventBusiness event = new EventBusiness();
			event.registerEvent(container);
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
