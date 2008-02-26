package br.ufrj.nce.labase.criaconto.site.controller;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufrj.nce.labase.controller.action.http.IAction;
import br.ufrj.nce.labase.phidias.action.http.ActionFactory;
import br.ufrj.nce.labase.phidias.communication.CommunicationProtocol;

public class Controller {

	@SuppressWarnings("unchecked")
	public static void execute(HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter(CommunicationProtocol.ACTION_PARAMETER);
		if (action != null) {

			// Transforma a requisição e um mapa de parametros.
			Map<String, String> parameterMap = request.getParameterMap();

			// Cria o objeto de resposta e passa para a ação.
			ObjectOutputStream responseObjectOutputStream = null;
			try {
				response.setContentType("application/octet-stream");
				responseObjectOutputStream = new ObjectOutputStream(response.getOutputStream());

				// Chama a ção responsável pelo parametro
				IAction acao = ActionFactory.getAcao(action);
				acao.execute(parameterMap, responseObjectOutputStream);

			} catch (IOException e1) {
				e1.printStackTrace();
			}
			// trata o fechamento do objeto de resposta.
			finally {
				try {
					responseObjectOutputStream.flush();
					responseObjectOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}
}
