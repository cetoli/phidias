package br.ufrj.nce.labase.phidias.persistence.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.ufrj.nce.labase.phidias.persistence.model.Session;

public class SessionDAO extends GenericDAO<Session> {
	public Session findByStatus(int status){
		try {
			Query query = this.getSession().createQuery("select s from Session s where s.status = :status");
			query.setParameter("status", status);
			query.setMaxResults(1);
			
			Object session = query.getSingleResult();
			return (Session) session;
		} catch (NoResultException e) {
			return null;
		}
	}
}
