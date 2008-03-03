package br.ufrj.nce.labase.phidias.persistence.dao;

import java.util.List;

import br.ufrj.nce.labase.phidias.persistence.model.Attendant;

/**
 * @generated
 */
public class AttendantDAO extends GenericDAO<Attendant> {
	public List<Attendant> findAll() {
		return getSession().createQuery("select a from Attendant a").getResultList();
	}
}