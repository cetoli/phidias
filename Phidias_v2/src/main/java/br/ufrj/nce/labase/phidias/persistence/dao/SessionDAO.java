package br.ufrj.nce.labase.phidias.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.ufrj.nce.labase.phidias.persistence.model.Attendant;
import br.ufrj.nce.labase.phidias.persistence.model.Session;

public class SessionDAO extends GenericDAO<Session> {
	@SuppressWarnings("unchecked")
	public List<Session> findByStatus(int status) {
		try {
			Query query = this
					.getSession()
					.createQuery(
							"select s from Session s where s.status = :status and s.sessionEndDate is null order by s.id desc");
			query.setParameter("status", status);

			List<Session> list = (List<Session>) query.getResultList();

			return list;
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Session> findAll() {
		try {
			Query query = this.getSession().createQuery(
					"select s from Session s order by s.id asc");

			List<Session> list = (List<Session>) query.getResultList();

			return list;
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Session> findDeadSession(String patient) {
		try {
			Query query = this
					.getSession()
					.createQuery(
							"select s from Session s where s.sessionEndDate is null and s.patient.id = :patient order by s.id desc");

			query.setParameter("patient", patient);

			List<Session> list = (List<Session>) query.getResultList();

			return list;
		} catch (NoResultException e) {
			return null;
		}
	}

	public void removeDeadSessions(String patient) {
		Query query = this
				.getSession()
				.createQuery(
						"Delete from Session s where s.sessionEndDate is null and s.patient.id = :patient");
		
		query.setParameter("patient", patient);
		
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public Integer findCurrentPatientSessionId(String patient) {
		try {
			Query query = this
					.getSession()
					.createQuery(
							"select s from Session s where s.sessionEndDate is null and s.patient.name = :patient order by s.id desc");

			query.setParameter("patient", patient);

			List<Session> list = (List<Session>) query.getResultList();

			if (!list.isEmpty()) {
				return list.get(0).getId();
			}

			return null;
		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * @param idSession
	 * @param idPattient
	 */
	public Session updateSessionAttendant(int idSession, int idPattient) {

		EntityManager em = this.getSession();
		em.getTransaction().begin();

		Session session = this.getSession().find(Session.class, idSession);
		session.setAttendant((Attendant) this.getSession().find(Attendant.class, idPattient));
		session.setStatus(Session.STATUS_PLAYING_GAME);
		
		em.persist(session);
		em.getTransaction().commit();
		return session;
	}
}
