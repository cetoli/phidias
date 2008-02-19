package br.ufrj.nce.labase.phidias.action.http;

import br.ufrj.nce.labase.controller.action.htp.IAction;
import br.ufrj.nce.labase.phidias.communication.CommunicationProtocol;

public class ActionFactory {

	public static IAction getAcao(String operacao) {
		if (operacao
				.equals(CommunicationProtocol.REGISTER_EVENT_ACTION))
			return new RegisterEventAction();
		else if (operacao
				.equals(CommunicationProtocol.REGISTER_SESSION_ACTION))
			return new RegisterSessionAction();
		else if (operacao
				.equals(CommunicationProtocol.REGISTER_SESSION_END_ACTION))
			return new RegisterSessionEndAction();
		else if (operacao
				.equals(CommunicationProtocol.REGISTER_COMMENT_ACTION))
			return new RegisterCommentAction();
		return null;
	}
}
