package br.ufrj.nce.labase.phidias.business;

import br.ufrj.nce.labase.phidias.communication.bean.StimulusBean;
import br.ufrj.nce.labase.phidias.persistence.EntityManagerHelper;
import br.ufrj.nce.labase.phidias.persistence.dao.SessionGamePhaseDAO;
import br.ufrj.nce.labase.phidias.persistence.dao.SessionGamePhaseStimulusTypeDAO;
import br.ufrj.nce.labase.phidias.persistence.model.SessionGamePhase;
import br.ufrj.nce.labase.phidias.persistence.model.SessionGamePhaseId;
import br.ufrj.nce.labase.phidias.persistence.model.SessionGamePhaseStimulusType;


public class StimulusBusiness {
	/**
	 * @param eventoContainer
	 * @return
	 */
	public SessionGamePhaseStimulusType registerStimulus(StimulusBean stimulusContainer) {
		try {
			if (stimulusContainer != null) {

				if (stimulusContainer.getId() != null)
					throw new RuntimeException("Session id cannot be set, it is created automatically!");

				EntityManagerHelper.getInstance().startTransaction();
				
				SessionGamePhaseStimulusType stimulus = new SessionGamePhaseStimulusType();
				stimulus.setSessionId(stimulusContainer.getSessionId());
				stimulus.setPhaseId(stimulusContainer.getPhaseId());
				stimulus.setStimulusTypeId(stimulusContainer.getStimulusTypeId());
				stimulus.setStimulusText(stimulusContainer.getStimulusText());

				SessionGamePhaseDAO sgpDAO = new SessionGamePhaseDAO();
				SessionGamePhase faseJogoSessao = sgpDAO.findById(SessionGamePhase.class, new SessionGamePhaseId(stimulusContainer.getPhaseId(), stimulusContainer.getSessionId()));
				if (faseJogoSessao == null)
					faseJogoSessao = sgpDAO.create(new SessionGamePhase(stimulusContainer.getPhaseId(), stimulusContainer.getSessionId()));

				SessionGamePhaseStimulusTypeDAO sgpstDAO = new SessionGamePhaseStimulusTypeDAO();
				sgpstDAO.create(stimulus);

				EntityManagerHelper.getInstance().commitTransaction();

				return stimulus;
			}
		} catch (RuntimeException e) {
			EntityManagerHelper.getInstance().rollbackTransaction();
			throw e;
		}
		throw new RuntimeException("Null parameter not allowed!");
	}

	public SessionGamePhaseStimulusType getNextStimulus(StimulusBean stimulusContainer) {
		try {
			if (stimulusContainer != null) {

				if (stimulusContainer.getId() != null)
					throw new RuntimeException("Session id cannot be set, it is created automatically!");

				EntityManagerHelper.getInstance().startTransaction();

				SessionGamePhaseStimulusTypeDAO sgpstDAO = new SessionGamePhaseStimulusTypeDAO();
				SessionGamePhaseStimulusType stimulus = sgpstDAO.getNextStimulus(stimulusContainer.getSessionId(), stimulusContainer.getPhaseId());

				if (stimulus != null) {
					// Atualiza o estimulo e informa que já foi enviado.
					stimulus.setStimulusSent(true);
					sgpstDAO.update(stimulus);
				}

				EntityManagerHelper.getInstance().commitTransaction();

				return stimulus;
			}
		} catch (RuntimeException e) {
			EntityManagerHelper.getInstance().rollbackTransaction();
			throw e;
		}
		throw new RuntimeException("Null parameter not allowed!");
	}
}
