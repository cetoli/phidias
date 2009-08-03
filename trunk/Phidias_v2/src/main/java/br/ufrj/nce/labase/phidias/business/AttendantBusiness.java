package br.ufrj.nce.labase.phidias.business;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import br.ufrj.nce.labase.phidias.communication.bean.AttendantBean;
import br.ufrj.nce.labase.phidias.persistence.EntityManagerHelper;
import br.ufrj.nce.labase.phidias.persistence.dao.AttendantDAO;
import br.ufrj.nce.labase.phidias.persistence.model.Attendant;

public class AttendantBusiness {

	public Attendant registerAttendant(AttendantBean attendantContainer) {
		try {
			if (attendantContainer != null) {
				Attendant attendant = new Attendant();
				try {
					BeanUtils.copyProperties(attendant, attendantContainer);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				EntityManagerHelper.getInstance().startTransaction();

				AttendantDAO aDAO = new AttendantDAO();
				if (attendant.getId() != null)
					aDAO.update(attendant);
				else
					aDAO.create(attendant);

				EntityManagerHelper.getInstance().commitTransaction();

				return attendant;
			}
		} catch (RuntimeException e) {
			EntityManagerHelper.getInstance().rollbackTransaction();
			throw e;
		}

		throw new RuntimeException("Null parameter not allowed!");
	}

	public List<Attendant> listAttendant() {
		try {

			EntityManagerHelper.getInstance().startTransaction();
			
			AttendantDAO pDAO = new AttendantDAO();
			
			List<Attendant> result = pDAO.findAll();
			
			EntityManagerHelper.getInstance().commitTransaction();

			return result;

		} catch (RuntimeException e) {
			EntityManagerHelper.getInstance().rollbackTransaction();
			throw e;
		}
	}

}
