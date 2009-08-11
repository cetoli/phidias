package br.ufrj.nce.labase.phidias.persistence.dao;

import java.util.List;

import javax.persistence.Query;

import br.ufrj.nce.labase.phidias.persistence.model.Attendant;

/**
 * @generated
 */
public class AttendantDAO extends GenericDAO<Attendant> {
	@SuppressWarnings("unchecked")
	public List<Attendant> findAll() {
		return getSession().createQuery("select a from Attendant a")
				.getResultList();
	}

	public Attendant findByName(String name) {
		Query q = getSession().createQuery(
				"select a from Attendant a where a.name = :name");
		q.setParameter("name", name);

		return (Attendant) q.getSingleResult();
	}
}