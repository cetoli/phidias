package br.ufrj.nce.labase.phidias.action.http;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.ufrj.nce.labase.controller.action.http.IAction;
import br.ufrj.nce.labase.phidias.business.SessionBusiness;
import br.ufrj.nce.labase.phidias.communication.bean.SessionBean;
import br.ufrj.nce.labase.phidias.communication.bean.SessionListResponseBean;
import br.ufrj.nce.labase.phidias.communication.bean.SessionResponseBean;
import br.ufrj.nce.labase.phidias.exception.PhidiasException;
import br.ufrj.nce.labase.phidias.persistence.model.Session;

public class ListOpenSessionsAction implements IAction {

	public void execute(Map<String, String> requestParameterMap, ObjectOutputStream objectOutputStream) {
		SessionListResponseBean evt = new SessionListResponseBean();

		try {
			SessionBean container = new SessionBean();
			container.loadPropertyValues(requestParameterMap);

			SessionBusiness session = new SessionBusiness();
			List<Session> sessions = session.listOpenSessions(container);
			
			List<SessionResponseBean> sessionResponseBeans = new ArrayList<SessionResponseBean>();
			for (Session s : sessions) {
				SessionResponseBean b = new SessionResponseBean();
				b.setSessionId(s.getId());
				b.setPatient(s.getPacient().getId());
				sessionResponseBeans.add(b);
			}
			
			evt.setSuccess(true);
			evt.setSessions(sessionResponseBeans);

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
