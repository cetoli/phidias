package br.ufrj.nce.labase.phidias.action.http;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.ufrj.nce.labase.phidias.business.EventBusiness;
import br.ufrj.nce.labase.phidias.communication.bean.EventBean;
import br.ufrj.nce.labase.phidias.communication.bean.EventResponseBean;
import br.ufrj.nce.labase.phidias.controller.action.http.IAction;
import br.ufrj.nce.labase.phidias.exception.PhidiasException;
import br.ufrj.nce.labase.phidias.persistence.model.Action;

public class GetMovesAction implements IAction {

	public void execute(Map<String, String> requestParameterMap, ObjectOutputStream objectOutputStream) {

		EventResponseBean evt = new EventResponseBean();
		try {
			EventBean container = new EventBean();
			container.loadPropertyValues(requestParameterMap);

			EventBusiness eventBusiness = new EventBusiness();
			List<Action> moves = eventBusiness.getMoves(container);
			List<String> movesStrArr = new ArrayList<String>();

			for (Action move : moves) {
				StringBuffer sb = new StringBuffer();
				sb.append("[Objeto manipulado]= ").append(move.getObject1()).append(" - [Tipo de Evento]= ").append(move.getActionType().getDescription()).append(" - [Objeto de Colisão]= ").append(
						move.getObject2()).append(" - [Tempo de Jogada]= ").append(move.getMoveTime());

				movesStrArr.add(sb.toString());
			}

			evt.setSuccess(true);
			evt.setMoves(movesStrArr);

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
