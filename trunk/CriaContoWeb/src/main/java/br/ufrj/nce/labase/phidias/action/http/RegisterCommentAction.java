package br.ufrj.nce.labase.phidias.action.http;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufrj.nce.labase.controller.action.http.IAction;
import br.ufrj.nce.labase.phidias.business.SessionBusiness;
import br.ufrj.nce.labase.phidias.communication.bean.CommentBean;
import br.ufrj.nce.labase.phidias.communication.bean.CommentResponseBean;

public class RegisterCommentAction implements IAction {

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

			CommentBean container = new CommentBean();
			container.loadPropertyValues(parameterMap);

			SessionBusiness session = new SessionBusiness();
			session.registerComment(container);

			CommentResponseBean evt = new CommentResponseBean();
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
