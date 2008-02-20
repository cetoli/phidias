package br.ufrj.nce.labase.criaconto.site.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufrj.nce.labase.controller.action.htp.IAction;
import br.ufrj.nce.labase.phidias.action.http.ActionFactory;
import br.ufrj.nce.labase.phidias.communication.CommunicationProtocol;

public class Controller {

	public static void execute(HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter(CommunicationProtocol.ACTION_PARAMETER);
		if (action != null) {
			IAction acao = ActionFactory.getAcao(action);
			acao.execute(request, response);
		}

	}
}
