package br.ufrj.nce.labase.phidias.action.http;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufrj.nce.labase.controller.action.http.IAction;
import br.ufrj.nce.labase.phidias.business.SessionBusiness;
import br.ufrj.nce.labase.phidias.communication.bean.SessionBean;
import br.ufrj.nce.labase.phidias.communication.bean.SessionResponseBean;
import br.ufrj.nce.labase.phidias.persistence.model.Session;

public class RegisterSessionAction implements IAction {

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

			SessionBean container = new SessionBean();
			container.loadPropertyValues(parameterMap);

			SessionBusiness session = new SessionBusiness();
			Session sessao = session.registerSession(container);

			SessionResponseBean evt = new SessionResponseBean();
			evt.setSuccess(true);
			evt.setSessionId(sessao.getId());

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
