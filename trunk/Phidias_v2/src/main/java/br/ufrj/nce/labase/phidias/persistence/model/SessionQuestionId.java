package br.ufrj.nce.labase.phidias.persistence.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @generated
 */
@Embeddable
public class SessionQuestionId implements java.io.Serializable {
	/**
	 * @generated
	 */
	@Column(name = "PEF_ID_PERGUNTA")
	private Integer questionId;
	
	@Column(name = "QUE_ID_QUESTIONARIO")
	private Integer questionnaireId;
	
	/**
	 * @generated
	 */
	@Column(name = "SES_ID_SESSAO")
	private Integer sessionId;
	
	/**
	 * @generated
	 */
	private static final long serialVersionUID = 683398969L;

	/**
	 * @generated
	 */
	public SessionQuestionId(Integer questionId, Integer sessionId, Integer questionnaireId) {
		this.questionId = questionId;
		this.sessionId = sessionId;
		this.questionnaireId = questionnaireId;
	}

	public Integer getIdPerguntasFormularios() {
		return questionId;
	}

	public Integer getIdSessao() {
		return sessionId;
	}

	public Integer getQuestionnaireId() {
		return questionnaireId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((questionId == null) ? 0 : questionId.hashCode());
		result = prime * result
				+ ((questionnaireId == null) ? 0 : questionnaireId.hashCode());
		result = prime * result
				+ ((sessionId == null) ? 0 : sessionId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SessionQuestionId other = (SessionQuestionId) obj;
		if (questionId == null) {
			if (other.questionId != null)
				return false;
		} else if (!questionId.equals(other.questionId))
			return false;
		if (questionnaireId == null) {
			if (other.questionnaireId != null)
				return false;
		} else if (!questionnaireId.equals(other.questionnaireId))
			return false;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		} else if (!sessionId.equals(other.sessionId))
			return false;
		return true;
	}
}