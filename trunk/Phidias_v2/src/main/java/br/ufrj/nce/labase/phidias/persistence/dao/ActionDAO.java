package br.ufrj.nce.labase.phidias.persistence.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.ufrj.nce.labase.phidias.persistence.model.Action;

public class ActionDAO extends GenericDAO<Action> {
	@SuppressWarnings("unchecked")
	public List<Action> listActions(Integer sessionId, Integer phaseId) {
		try {
			Query query = this
					.getSession()
					.createQuery(
							"select f from Action f where f.sessionGamePhase.id.sessionId = :idSessao and f.sessionGamePhase.id.phaseId = :idFaseJogo and (f.sentToAttendant is null or f.sentToAttendant = false) order by f.id");
			query.setParameter("idSessao", sessionId);
			query.setParameter("idFaseJogo", phaseId);

			List<Action> events = (List<Action>) query.getResultList();
			
			return events;
		} catch (NoResultException e) {
			return null;
		}
	}
}
