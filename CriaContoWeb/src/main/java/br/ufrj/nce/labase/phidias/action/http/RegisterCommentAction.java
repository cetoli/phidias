package br.ufrj.nce.labase.phidias.action.http;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufrj.nce.labase.phidias.business.SessionBusiness;
import br.ufrj.nce.labase.phidias.communication.bean.CommentBean;
import br.ufrj.nce.labase.phidias.communication.bean.CommentResponseBean;

public class RegisterCommentAction implements br.ufrj.nce.labase.controller.action.htp.IAction {

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

			CommentBean container = new CommentBean();
			container.loadPropertyValues(parameterMap);

			SessionBusiness session = new SessionBusiness();
			session.registerComment(container);

			printWriter.print("class=" + CommentResponseBean.class.getName() + ";");
			printWriter.print("success=true;");

		} catch (Throwable e) {
			printWriter.println("success=false");
		} finally {
			printWriter.flush();
			printWriter.close();
		}
	}

}
