package br.ufrj.nce.labase.phidias.persistence.dao;

import java.util.List;

import javax.persistence.Query;

import br.ufrj.nce.labase.phidias.persistence.model.ActionType;

public class ActionTypeDAO extends GenericDAO<ActionType> {

	@SuppressWarnings("unchecked")
	public List<ActionType> getGameActionTypes(Long gameId) {
		Query query = getSession()
				.createQuery(
						"select a from ActionType a where a.gameId = :gameId");
		query.setParameter("gameId", gameId);

		return query.getResultList();
	}
}
