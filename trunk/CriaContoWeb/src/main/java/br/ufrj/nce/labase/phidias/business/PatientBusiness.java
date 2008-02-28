package br.ufrj.nce.labase.phidias.business;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import br.ufrj.nce.labase.persistence.EntityManagerHelper;
import br.ufrj.nce.labase.phidias.communication.bean.PatientBean;
import br.ufrj.nce.labase.phidias.persistence.dao.PatientDAO;
import br.ufrj.nce.labase.phidias.persistence.model.Patient;

public class PatientBusiness {

	public Patient registerPatient(PatientBean patientContainer) {
		try {
			if (patientContainer != null) {
				Patient patient = new Patient();
				try {
					BeanUtils.copyProperties(patient, patientContainer);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}

				EntityManagerHelper.getInstance().startTransaction();

				PatientDAO pDAO = new PatientDAO();
				if (patient.getId() != null)
					pDAO.update(patient);
				else
					pDAO.create(patient);

				EntityManagerHelper.getInstance().commitTransaction();
				
				return patient;
			}
		} catch (RuntimeException e) {
			EntityManagerHelper.getInstance().rollbackTransaction();
			throw e;
		}
		
		throw new RuntimeException("Null parameter not allowed!");
	}
}