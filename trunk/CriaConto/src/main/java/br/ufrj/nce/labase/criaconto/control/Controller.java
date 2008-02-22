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
	
	public static void registerSession(int attendant, int game, String patient) {
		if (sendDataToServer) {
			SessionBean sessaoContainer = new SessionBean();
			sessaoContainer.setAttendantId(attendant);
			sessaoContainer.setGameId(game);
			sessaoContainer.setPatientId(patient);
			
			SessionResponseBean sessionBean = 
				(SessionResponseBean) CommunicationProtocol.execute(CommunicationProtocol.REGISTER_SESSION_ACTION, sessaoContainer);
			
			Session.getInstance().setSessionBean(sessionBean);
		} 
	}
	
	public static void registerGiveUpEvent(long time, String object) {
		EventBean eventoContainer = new EventBean();
		eventoContainer.setPhaseId(Session.getInstance().getActualPhase());
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
			eventoContainer.setPhaseId(Session.getInstance().getActualPhase());
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
			eventoContainer.setPhaseId(Session.getInstance().getActualPhase());
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
			eventoContainer.setPhaseId(Session.getInstance().getActualPhase());
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
			eventoContainer.setPhaseId(Session.getInstance().getActualPhase());
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
			commentContainer.setPhaseId(1);
			commentContainer.setSessionId(1);
			commentContainer.setCommentText(comment);

			CommunicationProtocol.execute(CommunicationProtocol.REGISTER_COMMENT_ACTION, commentContainer);
		}
		
		return true;
	}
	
	public static boolean registerStimulus(String stimulus) {
		if (sendDataToServer) {
			StimulusBean stimulusContainer = new StimulusBean();
			stimulusContainer.setPhaseId(1);
			stimulusContainer.setStimulusTypeId(StimulusBean.SHOW_NPC);
			stimulusContainer.setSessionId(1);
			stimulusContainer.setStimulusText(stimulus);

			CommunicationProtocol.execute(CommunicationProtocol.REGISTER_STIMULUS_ACTION, stimulusContainer);
		}
		
		return true;
	}
	
	public static boolean registerPhaseChange() {
		if (sendDataToServer) {
			StimulusBean stimulusContainer = new StimulusBean();
			stimulusContainer.setPhaseId(1);
			stimulusContainer.setStimulusTypeId(StimulusBean.CHANGE_PHASE);
			stimulusContainer.setSessionId(1);
			stimulusContainer.setStimulusText("mudanca de fase");

			CommunicationProtocol.execute(CommunicationProtocol.REGISTER_STIMULUS_ACTION, stimulusContainer);
		}
		
		return true;
	}
}
