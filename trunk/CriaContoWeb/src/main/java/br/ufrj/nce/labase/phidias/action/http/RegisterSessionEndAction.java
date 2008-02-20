package br.ufrj.nce.labase.phidias.action.http;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufrj.nce.labase.controller.action.http.IAction;
import br.ufrj.nce.labase.phidias.business.SessionBusiness;
import br.ufrj.nce.labase.phidias.communication.bean.SessionBean;
import br.ufrj.nce.labase.phidias.communication.bean.SessionResponseBean;
import br.ufrj.nce.labase.phidias.persistence.model.Session;

public class RegisterSessionEndAction implements IAction {

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

			SessionBean container = new SessionBean();
			container.loadPropertyValues(parameterMap);

			SessionBusiness session = new SessionBusiness();
			Session sessao =  session.registerSessionEnd(container);

			printWriter.print("class=" + SessionResponseBean.class.getName() + ";");
			printWriter.print("success=true;");
			printWriter.print("sessionId="+ sessao.getId());

		} catch (Throwable e) {
			e.printStackTrace();
			printWriter.println("success=false");
		} finally {
			printWriter.flush();
			printWriter.close();
		}
	}

}
