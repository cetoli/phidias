package br.ufrj.nce.labase.phidias.communication.bean;

import br.ufrj.nce.labase.phidias.communication.container.AbstractActionContainer;

public class CommentBean extends AbstractActionContainer {
	private Integer sessionId;
	private Integer phaseId;
	private String commentText;
	
	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}
	
	public Integer getSessionId() {
		return sessionId;
	}

	public void setPhaseId(Integer phaseId) {
		this.phaseId = phaseId;
	}

	public Integer getPhaseId() {
		return phaseId;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getCommentText() {
		return commentText;
	}

	
}
