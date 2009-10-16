package br.ufrj.nce.labase.phidias.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.ufrj.nce.labase.phidias.persistence.model.SessionQuestion;
import br.ufrj.nce.labase.phidias.persistence.model.SessionQuestionId;

public class SessionQuestionDAO extends GenericDAO<SessionQuestion> {

	public Long findAnswerId(Integer sessionId, Long questionnaireId, Long questionId) {
		try {
			Query query = this.getSession().createQuery("select s.id.answerId from SessionQuestion s where s.id.sessionId = :sessionId " +
					"and s.id.questionnaireId = :questionnaireId and s.id.questionId = :questionId");
			query.setParameter("sessionId", sessionId);
			query.setParameter("questionnaireId", questionnaireId);
			query.setParameter("questionId", questionId);
			
			return (Long) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public void deleteSessionQuestion(Integer sessionId, Long questionnaireId, Long questionId) {
		EntityManager em = this.getSession();
		em.getTransaction().begin();
		
		Query query = this.getSession().createQuery("delete from SessionQuestion s where s.id.sessionId = :sessionId " +
					"and s.id.questionnaireId = :questionnaireId and s.id.questionId = :questionId");
		query.setParameter("sessionId", sessionId);
		query.setParameter("questionnaireId", questionnaireId);
		query.setParameter("questionId", questionId);
		
		query.executeUpdate();
		
		em.getTransaction().commit();
	}

	public void updateSessionQuestion(Integer sessionId, Long questionnaireId, Long questionId, Long answerId) {
		EntityManager em = this.getSession();
		em.getTransaction().begin();
		
		SessionQuestion sessionQuestion = new SessionQuestion();
		SessionQuestionId id = new SessionQuestionId(questionId, sessionId, questionnaireId, answerId);
		sessionQuestion.setId(id);
		sessionQuestion.setAnswer("N/A");
		
		em.persist(sessionQuestion);
		
		em.getTransaction().commit();
	}
}
