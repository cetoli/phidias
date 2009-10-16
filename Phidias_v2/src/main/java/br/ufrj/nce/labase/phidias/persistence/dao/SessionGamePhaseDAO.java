package br.ufrj.nce.labase.phidias.persistence.dao;

import javax.persistence.EntityManager;

import br.ufrj.nce.labase.phidias.persistence.model.SessionGamePhase;
import br.ufrj.nce.labase.phidias.persistence.model.SessionGamePhaseId;

public class SessionGamePhaseDAO extends GenericDAO<SessionGamePhase> {

	public void atualizaComentarioSessaofase(int sessaoId, int faseId, String comentario) {
		
		EntityManager em = this.getSession();
		em.getTransaction().begin();
		
		SessionGamePhase gamePhase = em.find(SessionGamePhase.class, new SessionGamePhaseId(faseId, sessaoId));
		gamePhase.setComments(gamePhase.getComments() + comentario);
		
		em.merge(gamePhase);
		
		em.getTransaction().commit();
	}
}
