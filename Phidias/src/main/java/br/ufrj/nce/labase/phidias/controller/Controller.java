package br.ufrj.nce.labase.phidias.controller;

import br.ufrj.nce.labase.common.MidiSound;
import br.ufrj.nce.labase.phidias.communication.CommunicationProtocol;
import br.ufrj.nce.labase.phidias.communication.bean.CommentBean;
import br.ufrj.nce.labase.phidias.communication.bean.EventBean;
import br.ufrj.nce.labase.phidias.communication.bean.EventResponseBean;
import br.ufrj.nce.labase.phidias.communication.bean.SessionBean;
import br.ufrj.nce.labase.phidias.communication.bean.SessionListResponseBean;
import br.ufrj.nce.labase.phidias.communication.bean.SessionResponseBean;
import br.ufrj.nce.labase.phidias.communication.bean.StimulusBean;
import br.ufrj.nce.labase.phidias.communication.bean.StimulusResponseBean;
import br.ufrj.nce.labase.phidias.exception.PhidiasException;

public class Controller {
	private static boolean sendDataToServer = true;
	private static MidiSound currentSound;
		
	public static void setSendDataToServer(boolean send){
		sendDataToServer = send;
	}
	
	public static SessionListResponseBean listOpenSessions() throws PhidiasException {
		if (sendDataToServer) {
			SessionBean sessionContainer = new SessionBean();
			sessionContainer.setGameId(Session.getInstance().getGame());
			
			SessionListResponseBean sessionListBean =
				(SessionListResponseBean) CommunicationProtocol.execute(CommunicationProtocol.LIST_OPEN_SESSION_ACTION, sessionContainer);

			if (sessionListBean != null) {				
				if (sessionListBean.getGeneratedException() != null) {
					throw sessionListBean.getGeneratedException();
				}
				
				return sessionListBean;
			}
		}
		
		return new SessionListResponseBean();
	}
	
	public static boolean registerSession(String patient) throws PhidiasException {
		if (sendDataToServer) {
			SessionBean sessionContainer = new SessionBean();
			sessionContainer.setPatientId(patient);
			sessionContainer.setGameId(Session.getInstance().getGame());

			SessionResponseBean sessionBean =
				(SessionResponseBean) CommunicationProtocol.execute(CommunicationProtocol.REGISTER_SESSION_ACTION, sessionContainer);

			if (sessionBean != null) {				
				if (sessionBean.getGeneratedException() != null) {
					throw sessionBean.getGeneratedException();
				}
				
				Session.getInstance().setSessionBean(sessionBean);
				return true;
			}

			return false;
		}

		return true;
	}

	public static boolean registerSessionEnd() throws PhidiasException {
		if (sendDataToServer) {
			SessionBean sessionContainer = new SessionBean();
			sessionContainer.setId(Session.getInstance().getId());
			sessionContainer.setGameId(Session.getInstance().getGame());

			SessionResponseBean sessionBean =
				(SessionResponseBean) CommunicationProtocol.execute(CommunicationProtocol.REGISTER_SESSION_END_ACTION, sessionContainer);

			if (sessionBean != null) {
				if (sessionBean.getGeneratedException() != null) {
					throw sessionBean.getGeneratedException();
				}
				
				Session.getInstance().setSessionBean(sessionBean);
				return true;
			}

			return false;
		}

		return true;
	}
	
	public static boolean getSessionAttendant() throws PhidiasException {
		if (sendDataToServer) {
			SessionBean sessionContainer = new SessionBean();
			sessionContainer.setGameId(Session.getInstance().getGame());
			sessionContainer.setId(Session.getInstance().getId());

			SessionResponseBean sessionBean =
				(SessionResponseBean) CommunicationProtocol.execute(CommunicationProtocol.GET_SESSION_ACTION, sessionContainer);

			if (sessionBean != null) {
				if (sessionBean.getGeneratedException() != null) {
					throw sessionBean.getGeneratedException();
				}
				
				if (sessionBean.getAttendant() != null) {
					Session.getInstance().setSessionBean(sessionBean);
					return true;
				}				
			}

			return false;
		}

		return true;
	}
	
	public static boolean getSessionEnded() throws PhidiasException {
		if (sendDataToServer) {
			SessionBean sessionContainer = new SessionBean();
			sessionContainer.setGameId(Session.getInstance().getGame());
			sessionContainer.setId(Session.getInstance().getId());

			SessionResponseBean sessionBean =
				(SessionResponseBean) CommunicationProtocol.execute(CommunicationProtocol.GET_SESSION_ACTION, sessionContainer);

			if (sessionBean != null) {
				if (sessionBean.getGeneratedException() != null) {
					throw sessionBean.getGeneratedException();
				}
				
				if (sessionBean.getSessionEndDate() != null) {
					Session.getInstance().setSessionBean(sessionBean);
					return true;
				}				
			}

			return false;
		}

		return true;
	}

	public static boolean joinSession(String attendant, int sessionId) throws PhidiasException {
		if (sendDataToServer) {
			SessionBean sessionContainer = new SessionBean();
			sessionContainer.setGameId(Session.getInstance().getGame());
			sessionContainer.setAttendantId(attendant);
			sessionContainer.setId(sessionId);

			SessionResponseBean sessionBean =
				(SessionResponseBean) CommunicationProtocol.execute(CommunicationProtocol.JOIN_SESSION_ACTION, sessionContainer);

			if (sessionBean != null) {
				if (sessionBean.getGeneratedException() != null) {
					throw sessionBean.getGeneratedException();
				}
				
				Session.getInstance().setSessionBean(sessionBean);
				return true;
			}

			return false;
		}

		return true;
	}

	public static void registerGiveUpEvent(long time, String object) throws PhidiasException {
		EventBean eventoContainer = new EventBean();
		eventoContainer.setPhaseId(Session.getInstance().getCurrentPhase());
		eventoContainer.setActionTypeId(EventBean.GIVE_UP_EVENT);
		eventoContainer.setSessionId(Session.getInstance().getId());
		eventoContainer.setObject1(object);
		eventoContainer.setValidMove(true);
		eventoContainer.setMoveTime(time);
		
		if (sendDataToServer) {
			CommunicationProtocol.execute(CommunicationProtocol.REGISTER_EVENT_ACTION, eventoContainer);
		}
	}

	public static void registerTakeFromSceneEvent(long time, String object) throws PhidiasException {
		if (sendDataToServer) {
			EventBean eventoContainer = new EventBean();
			eventoContainer.setPhaseId(Session.getInstance().getCurrentPhase());
			eventoContainer.setActionTypeId(EventBean.TAKE_FROM_SCENE_EVENT);
			eventoContainer.setSessionId(Session.getInstance().getId());
			eventoContainer.setObject1(object);
			eventoContainer.setValidMove(true);
			eventoContainer.setMoveTime(time);

			CommunicationProtocol.execute(CommunicationProtocol.REGISTER_EVENT_ACTION, eventoContainer);
		}
	}

	public static void registerMoveOnSceneEvent(long time, String object) throws PhidiasException {
		if (sendDataToServer) {
			EventBean eventoContainer = new EventBean();
			eventoContainer.setPhaseId(Session.getInstance().getCurrentPhase());
			eventoContainer.setActionTypeId(EventBean.MOVE_ON_SCENE_EVENT);
			eventoContainer.setSessionId(Session.getInstance().getId());
			eventoContainer.setObject1(object);
			eventoContainer.setValidMove(true);
			eventoContainer.setMoveTime(time);

			CommunicationProtocol.execute(CommunicationProtocol.REGISTER_EVENT_ACTION, eventoContainer);
		}
	}

	public static void registerPutOnSceneEvent(long time, String object) throws PhidiasException {
		if (sendDataToServer) {
			EventBean eventoContainer = new EventBean();
			eventoContainer.setPhaseId(Session.getInstance().getCurrentPhase());
			eventoContainer.setActionTypeId(EventBean.PUT_ON_SCENE_EVENT);
			eventoContainer.setSessionId(Session.getInstance().getId());
			eventoContainer.setObject1(object);
			eventoContainer.setValidMove(true);
			eventoContainer.setMoveTime(time);

			CommunicationProtocol.execute(CommunicationProtocol.REGISTER_EVENT_ACTION, eventoContainer);
		}
	}
	
	public static void registerMouseMoveEvent(long time, String object) throws PhidiasException {
		if (sendDataToServer) {
			EventBean eventoContainer = new EventBean();
			eventoContainer.setPhaseId(Session.getInstance().getCurrentPhase());
			eventoContainer.setActionTypeId(EventBean.MOUSE_MOVE_EVENT);
			eventoContainer.setSessionId(Session.getInstance().getId());
			eventoContainer.setObject1(object);
			eventoContainer.setValidMove(true);
			eventoContainer.setMoveTime(time);

			CommunicationProtocol.execute(CommunicationProtocol.REGISTER_EVENT_ACTION, eventoContainer);
		}
	}

	public static void registerCollisionEvent(String object1, String object2) throws PhidiasException {
		if (sendDataToServer) {
			EventBean eventoContainer = new EventBean();
			eventoContainer.setPhaseId(Session.getInstance().getCurrentPhase());
			eventoContainer.setActionTypeId(EventBean.COLLISION_EVENT);
			eventoContainer.setSessionId(Session.getInstance().getId());
			eventoContainer.setObject1(object1);
			eventoContainer.setObject2(object2);
			eventoContainer.setValidMove(true);
			eventoContainer.setMoveTime(0);

			CommunicationProtocol.execute(CommunicationProtocol.REGISTER_EVENT_ACTION, eventoContainer);
		}
	}

	public static boolean registerComment(String comment) throws PhidiasException {
		if (sendDataToServer) {
			CommentBean commentContainer = new CommentBean();
			commentContainer.setPhaseId(Session.getInstance().getCurrentPhase());
			commentContainer.setSessionId(Session.getInstance().getId());
			commentContainer.setCommentText(comment);

			CommunicationProtocol.execute(CommunicationProtocol.REGISTER_COMMENT_ACTION, commentContainer);
		}

		return true;
	}

	public static EventResponseBean getMoves() throws PhidiasException {
		EventBean event = new EventBean();
		event.setPhaseId(Session.getInstance().getCurrentPhase());
		event.setSessionId(Session.getInstance().getId());

		EventResponseBean eventBean =
			(EventResponseBean) CommunicationProtocol.execute(CommunicationProtocol.GET_MOVES_ACTION, event);
		
		if (eventBean != null) {
			if (eventBean.getGeneratedException() != null) {
				throw eventBean.getGeneratedException();
			}
			
			return eventBean;			
		}
		
		return null;
	}

	public static boolean registerStimulus(String stimulus) throws PhidiasException {
		if (sendDataToServer) {
			StimulusBean stimulusContainer = new StimulusBean();
			stimulusContainer.setPhaseId(Session.getInstance().getCurrentPhase());
			stimulusContainer.setStimulusTypeId(StimulusBean.SHOW_NPC);
			stimulusContainer.setSessionId(Session.getInstance().getId());
			stimulusContainer.setStimulusText(stimulus);

			CommunicationProtocol.execute(CommunicationProtocol.REGISTER_STIMULUS_ACTION, stimulusContainer);
		}

		return true;
	}

	public static boolean registerPhaseChange() throws PhidiasException {
		if (sendDataToServer) {
			StimulusBean stimulusContainer = new StimulusBean();
			stimulusContainer.setPhaseId(Session.getInstance().getCurrentPhase());
			stimulusContainer.setStimulusTypeId(StimulusBean.CHANGE_PHASE);
			stimulusContainer.setSessionId(Session.getInstance().getId());
			stimulusContainer.setStimulusText("mudanca de fase");

			CommunicationProtocol.execute(CommunicationProtocol.REGISTER_STIMULUS_ACTION, stimulusContainer);
		}

		return true;
	}
	
	public static StimulusResponseBean getNextStimulus() throws PhidiasException {
		if (sendDataToServer) {
			StimulusBean stimulusContainer = new StimulusBean();
			stimulusContainer.setPhaseId(Session.getInstance().getCurrentPhase());
			stimulusContainer.setSessionId(Session.getInstance().getId());
			
			StimulusResponseBean stimulusBean = 
				(StimulusResponseBean) CommunicationProtocol.execute(CommunicationProtocol.GET_NEXT_STIMULUS_ACTION, stimulusContainer);
			
			if (stimulusBean != null) {
				if (stimulusBean.getGeneratedException() != null) {
					throw stimulusBean.getGeneratedException();
				}
				
				return stimulusBean;
			}
		}
		
		return null;
	}

	public static void setCurrentSound(MidiSound sound) {
		if (currentSound != null) {
			currentSound.stop();
		}
		
		currentSound = sound;
	}
	
	public static void startSound() {
		if (currentSound != null) {
			currentSound.start();
		}
	}
	
	public static void stopSound() {
		if (currentSound != null) {
			currentSound.stop();
		}
	}
}
