package br.ufrj.nce.labase.criaconto.control;

import br.ufrj.nce.labase.phidias.communication.CommunicationProtocol;
import br.ufrj.nce.labase.phidias.communication.bean.CommentBean;
import br.ufrj.nce.labase.phidias.communication.bean.EventBean;
import br.ufrj.nce.labase.phidias.communication.bean.SessionBean;
import br.ufrj.nce.labase.phidias.communication.bean.SessionResponseBean;
import br.ufrj.nce.labase.phidias.communication.bean.StimulusBean;
import br.ufrj.nce.labase.phidias.controller.Session;

public class Controller {
	private static boolean sendDataToServer = true;
	
	public static void setSendDataToServer(boolean send){
		sendDataToServer = send;
	}
	
	public static boolean registerSession(String attendant, int game) {
		if (sendDataToServer) {
			SessionBean sessionContainer = new SessionBean();
			sessionContainer.setAttendantId(attendant);
			sessionContainer.setGameId(game);
			
			SessionResponseBean sessionBean = 
				(SessionResponseBean) CommunicationProtocol.execute(CommunicationProtocol.REGISTER_SESSION_ACTION, sessionContainer);
			
			if (sessionBean != null) {
				Session.getInstance().setSessionBean(sessionBean);
				return true;
			}
			
			return false;
		} 
		
		return true;
	}
	
	public static boolean registerSessionEnd(int game) {
		if (sendDataToServer) {
			SessionBean sessionContainer = new SessionBean();
			sessionContainer.setId(Session.getInstance().getId());
			sessionContainer.setGameId(game);
			
			SessionResponseBean sessionBean = 
				(SessionResponseBean) CommunicationProtocol.execute(CommunicationProtocol.REGISTER_SESSION_END_ACTION, sessionContainer);
			
			if (sessionBean != null) {
				Session.getInstance().setSessionBean(sessionBean);
				return true;
			}
			
			return false;
		} 
		
		return true;
	}
	
	public static boolean joinSession(String patient, int game) {
		if (sendDataToServer) {
			SessionBean sessaoContainer = new SessionBean();
			sessaoContainer.setGameId(game);
			sessaoContainer.setPatientId(patient);
			
			SessionResponseBean sessionBean = 
				(SessionResponseBean) CommunicationProtocol.execute(CommunicationProtocol.JOIN_SESSION_ACTION, sessaoContainer);
			
			if (sessionBean != null) {
				Session.getInstance().setSessionBean(sessionBean);
				return true;
			}
			
			return false;
		} 
		
		return true;
	}
	
	public static void registerGiveUpEvent(long time, String object) {
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
	
	public static void registerTakeFromSceneEvent(long time, String object) {
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
	
	public static void registerMoveOnSceneEvent(long time, String object) {
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
	
	public static void registerPutOnSceneEvent(long time, String object) {
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
	
	public static void registerCollisionEvent(String object1, String object2) {
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
	
	public static boolean registerComment(String comment) {
		if (sendDataToServer) {
			CommentBean commentContainer = new CommentBean();
			commentContainer.setPhaseId(Session.getInstance().getCurrentPhase());
			commentContainer.setSessionId(Session.getInstance().getId());
			commentContainer.setCommentText(comment);

			CommunicationProtocol.execute(CommunicationProtocol.REGISTER_COMMENT_ACTION, commentContainer);
		}
		
		return true;
	}
	
	public static boolean registerStimulus(String stimulus) {
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
	
	public static boolean registerPhaseChange() {
		if (sendDataToServer) {
			StimulusBean stimulusContainer = new StimulusBean();
			stimulusContainer.setPhaseId(Session.getInstance().getCurrentPhase());
			stimulusContainer.setStimulusTypeId(StimulusBean.CHANGE_PHASE);
			stimulusContainer.setSessionId(Session.getInstance().getId());
			stimulusContainer.setStimulusText("mudanca de fase");

			CommunicationProtocol.execute(CommunicationProtocol.REGISTER_STIMULUS_ACTION, stimulusContainer);
		}
		
		Session.getInstance().changePhase();
		return true;
	}
}
