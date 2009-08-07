package br.ufrj.nce.labase.phidias.persistence.dao;

import java.util.List;

import javax.persistence.Query;

import br.ufrj.nce.labase.phidias.persistence.model.Patient;

public class PatientDAO extends GenericDAO<Patient> {

	@SuppressWarnings("unchecked")
	public List<Patient> findAll() {
		return getSession().createQuery("select p from Patient p").getResultList();
	}
	
	public Patient findByName(String name) {
		Query query = this.getSession().createQuery("select p from Patient p Where p.name = :name");
		
		query.setParameter("name", name);
		
		return (Patient) query.getSingleResult();
	}

}
