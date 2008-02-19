package br.ufrj.nce.labase.phidias.business;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import br.ufrj.nce.labase.persistence.EntityManagerHelper;
import br.ufrj.nce.labase.phidias.communication.bean.PatientBean;
import br.ufrj.nce.labase.phidias.persistence.dao.PatientDAO;
import br.ufrj.nce.labase.phidias.persistence.model.Patient;

public class PatientBusiness {

	public Patient registerPatient(PatientBean patientContainer) {
		if (patientContainer != null) {
			Patient patient = new Patient();
			try {
				BeanUtils.copyProperties(patient, patientContainer);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
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
		
		throw new RuntimeException("Null parameter not allowed!");
	}
}
