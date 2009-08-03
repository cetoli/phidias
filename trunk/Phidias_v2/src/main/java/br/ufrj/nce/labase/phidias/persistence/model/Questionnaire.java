package br.ufrj.nce.labase.phidias.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "questionarios")
public class Questionnaire implements java.io.Serializable {
	private static final long serialVersionUID = -7510294653727352496L;

	@Id
	@Column(name = "QUE_ID_QUESTIONARIO")
	private long questionnaireId;
	
	@Column(name = "JOG_ID_JOGO")
	private Long gameId;
	
	@Column(name = "APL_ID_LOGIN")
	private String loginId;

	public long getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(long questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
}
