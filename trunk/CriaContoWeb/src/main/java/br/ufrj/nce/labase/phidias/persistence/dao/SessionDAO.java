package br.ufrj.nce.labase.phidias.persistence.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.ufrj.nce.labase.phidias.persistence.model.Session;

public class SessionDAO extends GenericDAO<Session> {
	public Session findByStatus(int status){
		try {
			Query query = this.getSession().createQuery("select s from Session s where s.status = :status and s.sessionEndDate is null order by s.id desc");
			query.setParameter("status", status);
			query.setMaxResults(1);
			
			Object session = query.getSingleResult();
			return (Session) session;
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Session> findDeadSession(){
		try {
			Query query = this.getSession().createQuery("select s from Session s where s.sessionEndDate is null order by s.id desc");
			
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}
