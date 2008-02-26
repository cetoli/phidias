package br.ufrj.nce.labase.phidias.action.http;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import br.ufrj.nce.labase.controller.action.http.IAction;
import br.ufrj.nce.labase.phidias.business.SessionBusiness;
import br.ufrj.nce.labase.phidias.communication.bean.CommentBean;
import br.ufrj.nce.labase.phidias.communication.bean.CommentResponseBean;
import br.ufrj.nce.labase.phidias.exception.PhidiasException;

public class RegisterCommentAction implements IAction {

	public void execute(Map<String, String> requestParameterMap, ObjectOutputStream objectOutputStream) {
		CommentResponseBean evt = new CommentResponseBean();

		try {
			CommentBean container = new CommentBean();
			container.loadPropertyValues(requestParameterMap);

			SessionBusiness session = new SessionBusiness();
			session.registerComment(container);

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
