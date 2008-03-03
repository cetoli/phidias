package br.ufrj.nce.labase.phidias.persistence.dao;

import java.util.List;

import br.ufrj.nce.labase.phidias.persistence.model.Patient;

public class PatientDAO extends GenericDAO<Patient> {

	public List<Patient> findAll() {
		return getSession().createQuery("select p from Patient p").getResultList();
	}

}
