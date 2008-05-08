package br.ufrj.nce.labase.phidias.persistence.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.ufrj.nce.labase.phidias.persistence.model.SessionGamePhaseStimulusType;

public class SessionGamePhaseStimulusTypeDAO extends GenericDAO<SessionGamePhaseStimulusType> {
	
	public SessionGamePhaseStimulusType getNextStimulus(Integer sessionId, Integer phaseId){
		try {
			Query query = this.getSession().createQuery("select f from SessionGamePhaseStimulusType f where f.sessionId = :idSessao and f.phaseId = :idFaseJogo and (f.stimulusSent = false or f.stimulusSent is null ) order by f.id");
			query.setParameter("idSessao", sessionId);
			query.setParameter("idFaseJogo", phaseId);
			query.setMaxResults(1);
			
			Object stimulus = query.getSingleResult();
			return (SessionGamePhaseStimulusType) stimulus;
		} catch (NoResultException e) {
			return null;
		}
	}
}
