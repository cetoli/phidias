package br.ufrj.nce.labase.phidias.business;

import java.util.List;

import br.ufrj.nce.labase.persistence.EntityManagerHelper;
import br.ufrj.nce.labase.phidias.communication.bean.EventBean;
import br.ufrj.nce.labase.phidias.persistence.dao.ActionDAO;
import br.ufrj.nce.labase.phidias.persistence.dao.ActionTypeDAO;
import br.ufrj.nce.labase.phidias.persistence.dao.SessionGamePhaseDAO;
import br.ufrj.nce.labase.phidias.persistence.model.Action;
import br.ufrj.nce.labase.phidias.persistence.model.ActionType;
import br.ufrj.nce.labase.phidias.persistence.model.SessionGamePhase;
import br.ufrj.nce.labase.phidias.persistence.model.SessionGamePhaseId;

public class EventBusiness {
	/**
	 * @param eventoContainer
	 * @return
	 */
	public Action registerEvent(EventBean eventContainer) {
		if (eventContainer != null) {

			if (eventContainer.getId() != null)
				throw new RuntimeException("Session id cannot be set, it is created automatically!");

			EntityManagerHelper.getInstance().startTransaction();
			Action action = new Action();

			SessionGamePhaseDAO sgpDAO = new SessionGamePhaseDAO();
			SessionGamePhase gamePhase = sgpDAO.findById(SessionGamePhase.class, new SessionGamePhaseId(eventContainer.getPhaseId(), eventContainer.getSessionId()));
			if (gamePhase == null)
				gamePhase = sgpDAO.create(new SessionGamePhase(eventContainer.getPhaseId(), eventContainer.getSessionId()));

			action.setSessionGamePhase(gamePhase);

			ActionTypeDAO atDAO = new ActionTypeDAO();
			action.setActionType(atDAO.findById(ActionType.class, eventContainer.getActionTypeId()));

			action.setValidMove(eventContainer.getValidMove());
			action.setObject1(eventContainer.getObject1());
			action.setObject2(eventContainer.getObject2());
			action.setMoveTime(eventContainer.getMoveTime());

			ActionDAO aDAO = new ActionDAO();
			aDAO.create(action);

			EntityManagerHelper.getInstance().commitTransaction();

			return action;
		}
		throw new RuntimeException("Null parameter not allowed!");
	}
	
	/**
	 * @param eventoContainer
	 * @return
	 */
	public List<Action> getMoves(EventBean eventContainer) {
		if (eventContainer != null) {

			if (eventContainer.getId() != null)
				throw new RuntimeException("Session id cannot be set, it is created automatically!");

			ActionDAO aDAO = new ActionDAO();
			List<Action> events = aDAO.listActions(eventContainer.getSessionId(), eventContainer.getPhaseId());
			
			return events;
		}
		throw new RuntimeException("Null parameter not allowed!");
	}
}
