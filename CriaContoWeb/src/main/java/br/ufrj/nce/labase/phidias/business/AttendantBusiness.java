package br.ufrj.nce.labase.phidias.business;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import br.ufrj.nce.labase.persistence.EntityManagerHelper;
import br.ufrj.nce.labase.phidias.communication.bean.AttendantBean;
import br.ufrj.nce.labase.phidias.persistence.dao.AttendantDAO;
import br.ufrj.nce.labase.phidias.persistence.model.Attendant;

public class AttendantBusiness {

	public Attendant registerAttendant(AttendantBean attendantContainer) {
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

		throw new RuntimeException("Null parameter not allowed!");
	}
}
