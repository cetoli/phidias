package br.ufrj.nce.labase.phidias.business;

import java.util.Date;

import br.ufrj.nce.labase.persistence.EntityManagerHelper;
import br.ufrj.nce.labase.phidias.communication.bean.CommentBean;
import br.ufrj.nce.labase.phidias.communication.bean.SessionBean;
import br.ufrj.nce.labase.phidias.exception.PhidiasException;
import br.ufrj.nce.labase.phidias.persistence.dao.AttendantDAO;
import br.ufrj.nce.labase.phidias.persistence.dao.GameDAO;
import br.ufrj.nce.labase.phidias.persistence.dao.PatientDAO;
import br.ufrj.nce.labase.phidias.persistence.dao.SessionDAO;
import br.ufrj.nce.labase.phidias.persistence.dao.SessionGamePhaseDAO;
import br.ufrj.nce.labase.phidias.persistence.model.Attendant;
import br.ufrj.nce.labase.phidias.persistence.model.Game;
import br.ufrj.nce.labase.phidias.persistence.model.Patient;
import br.ufrj.nce.labase.phidias.persistence.model.Session;
import br.ufrj.nce.labase.phidias.persistence.model.SessionGamePhase;
import br.ufrj.nce.labase.phidias.persistence.model.SessionGamePhaseId;

public class SessionBusiness {
	
	private final int WAITING_FOR_PATIENT = 0;
	private final int PLAYING_GAME = 1;
	private final int GAME_OVER = 2;
	
	public Session registerSession(SessionBean sessionContainer) {
		try {
			if (sessionContainer != null) {

				if (sessionContainer.getId() != null)
					throw new RuntimeException("Session id cannot be set, it is created automatically!");

				EntityManagerHelper.getInstance().startTransaction();
				Session session = new Session();

				AttendantDAO aDAO = new AttendantDAO();
				session.setAttendant(aDAO.findById(Attendant.class, sessionContainer.getAttendantId()));

				GameDAO gDAO = new GameDAO();
				session.setGame(gDAO.findById(Game.class, sessionContainer.getGameId()));

				session.setSessionStartDate(new Date());
				session.setStatus(WAITING_FOR_PATIENT);

				SessionDAO sesDao = new SessionDAO();
				sesDao.create(session);

				EntityManagerHelper.getInstance().commitTransaction();

				return session;
			}
		} catch (RuntimeException e) {
			EntityManagerHelper.getInstance().rollbackTransaction();
			throw e;

		}
		
		throw new PhidiasException("Null parameter not allowed!");
	}
	
	public Session joinSession(SessionBean sessionContainer) {
		try {
			if (sessionContainer != null) {

				if (sessionContainer.getId() != null)
					throw new RuntimeException("Session id cannot be set, it is created automatically!");

				EntityManagerHelper.getInstance().startTransaction();
				
				PatientDAO pDAO = new PatientDAO();
				Patient p = pDAO.findById(Patient.class, sessionContainer.getPatientId());
				
				SessionDAO sDAO = new SessionDAO();
				Session session = sDAO.findByStatus(WAITING_FOR_PATIENT);
				
				if (session == null) {
					throw new RuntimeException("No attendant online!");
				}
				
				session.setPatient(p);
				session.setStatus(PLAYING_GAME);

				sDAO.update(session);

				EntityManagerHelper.getInstance().commitTransaction();

				return session;
			}
		} catch (RuntimeException e) {
			EntityManagerHelper.getInstance().rollbackTransaction();
			throw e;
		}
		throw new PhidiasException("Null parameter not allowed!");
	}

	public Session registerSessionEnd(SessionBean sessionContainer) {
		try {
			if (sessionContainer != null) {

				if (sessionContainer.getId() == null)
					throw new RuntimeException("Session id must not be null!");

				EntityManagerHelper.getInstance().startTransaction();

				SessionDAO sDAO = new SessionDAO();
				Session session = sDAO.findById(Session.class, sessionContainer.getId());
				session.setSessionEndDate(new Date());
				session.setStatus(GAME_OVER);

				EntityManagerHelper.getInstance().commitTransaction();

				return session;
			}
		} catch (RuntimeException e) {
			EntityManagerHelper.getInstance().rollbackTransaction();
			throw e;
		}
		throw new PhidiasException("Null parameter not allowed!");
	}

	public SessionGamePhase registerComment(CommentBean commentContainer) {
		try {
			if (commentContainer != null) {

				EntityManagerHelper.getInstance().startTransaction();

				SessionGamePhaseDAO gpsDAO = new SessionGamePhaseDAO();
				SessionGamePhase gamePhase = gpsDAO.findById(SessionGamePhase.class, new SessionGamePhaseId(commentContainer.getPhaseId(), commentContainer.getSessionId()));
				if (gamePhase == null) {
					gamePhase = new SessionGamePhase(commentContainer.getPhaseId(), commentContainer.getSessionId());
					gamePhase = gpsDAO.create(gamePhase);
				}
				gamePhase.setComments(gamePhase.getComments() + commentContainer.getCommentText());

				EntityManagerHelper.getInstance().commitTransaction();

				return gamePhase;
			}
		} catch (RuntimeException e) {
			EntityManagerHelper.getInstance().rollbackTransaction();
			throw e;
		}
		throw new PhidiasException("Null parameter not allowed!");
	}
}
