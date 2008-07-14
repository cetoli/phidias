package br.ufrj.nce.labase.phidias.action.http;

import br.ufrj.nce.labase.phidias.communication.CommunicationProtocol;
import br.ufrj.nce.labase.phidias.controller.action.http.IAction;

public class ActionFactory {

	public static IAction getAction(String operacao) {
		if (operacao.equals(CommunicationProtocol.REGISTER_EVENT_ACTION))
			return new RegisterEventAction();
		else if (operacao.equals(CommunicationProtocol.REGISTER_SESSION_ACTION))
			return new RegisterSessionAction();
		else if (operacao.equals(CommunicationProtocol.JOIN_SESSION_ACTION))
			return new JoinSessionAction();
		else if (operacao
				.equals(CommunicationProtocol.REGISTER_SESSION_END_ACTION))
			return new RegisterSessionEndAction();
		else if (operacao.equals(CommunicationProtocol.REGISTER_COMMENT_ACTION))
			return new RegisterCommentAction();
		else if (operacao
				.equals(CommunicationProtocol.GET_NEXT_STIMULUS_ACTION))
			return new GetNextStimulusAction();
		else if (operacao
				.equals(CommunicationProtocol.GET_MOVES_ACTION))
			return new GetMovesAction();
		else if (operacao
				.equals(CommunicationProtocol.REGISTER_STIMULUS_ACTION))
			return new RegisterStimulusAction();
		else if (operacao
				.equals(CommunicationProtocol.LIST_OPEN_SESSION_ACTION))
			return new ListOpenSessionsAction();
		else if (operacao
				.equals(CommunicationProtocol.GET_SESSION_ACTION))
			return new GetSessionAction();
		
		return null;
	}
}
