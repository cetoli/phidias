package br.ufrj.nce.labase.phidias.persistence.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.ufrj.nce.labase.phidias.persistence.model.Question;
import br.ufrj.nce.labase.phidias.persistence.model.Questionnaire;

public class QuestionnaireDAO extends GenericDAO<Questionnaire> {

	@SuppressWarnings("unchecked")
	public List<Question> findQuestionarieByGameFaseId(int gameId, int faseId) {
		try {
			Query query = this.getSession().createQuery("select gamePhase.questionList from GamePhase gamePhase where gamePhase.id = :faseId and gamePhase.game.id =:gameId ");
			query.setParameter("faseId", faseId);
			query.setParameter("gameId", gameId);
			
			List<Question> list = (List<Question>) query.getResultList();

			return list;
		} catch (NoResultException e) {
			return null;
		}
	}

}
