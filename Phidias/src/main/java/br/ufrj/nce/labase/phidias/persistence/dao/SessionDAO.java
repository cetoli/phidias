package br.ufrj.nce.labase.phidias.persistence.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.ufrj.nce.labase.phidias.persistence.model.Session;

public class SessionDAO extends GenericDAO<Session> {
	public List<Session> findByStatus(int status){
		try {
			Query query = this.getSession().createQuery("select s from Session s where s.status = :status and s.sessionEndDate is null order by s.id desc");
			query.setParameter("status", status);
			
			List<Session> list = (List<Session>) query.getResultList();
			
			return list;
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Session> findDeadSession(String patient){
		try {
			Query query = this.getSession().createQuery("select s from Session s where s.sessionEndDate is null and s.patient.id = :patient order by s.id desc");
			
			query.setParameter("patient", patient);
			
			List<Session> list = (List<Session>) query.getResultList();
			
			return list;
		} catch (NoResultException e) {
			return null;
		}
	}
}
