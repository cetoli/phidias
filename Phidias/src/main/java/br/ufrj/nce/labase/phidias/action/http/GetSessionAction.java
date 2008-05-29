package br.ufrj.nce.labase.phidias.action.http;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import br.ufrj.nce.labase.controller.action.http.IAction;
import br.ufrj.nce.labase.phidias.business.SessionBusiness;
import br.ufrj.nce.labase.phidias.communication.bean.SessionBean;
import br.ufrj.nce.labase.phidias.communication.bean.SessionResponseBean;
import br.ufrj.nce.labase.phidias.exception.PhidiasException;
import br.ufrj.nce.labase.phidias.persistence.model.Session;

public class GetSessionAction implements IAction {

	public void execute(Map<String, String> requestParameterMap, ObjectOutputStream objectOutputStream) {
		SessionResponseBean evt = new SessionResponseBean();

		try {
			SessionBean container = new SessionBean();
			container.loadPropertyValues(requestParameterMap);

			SessionBusiness sessionBusiness = new SessionBusiness();
			Session session = sessionBusiness.getSession(container);

			evt.setSuccess(true);
			evt.setSessionId(session.getId());
			evt.setSessionEndDate(session.getSessionEndDate());
			
			if (session.getAttendant() != null) {
				evt.setAttendant(session.getAttendant().getId());
			}
			
			if (session.getPacient() != null) {
				evt.setPatient(session.getPacient().getId());
			}
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
