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
	@Column(name = "PEF_ID_PERGUNTAFORMULARIO")
	private Integer questionId;
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
	public SessionQuestionId(Integer questionId, Integer sessionId) {
		this.questionId = questionId;
		this.sessionId = sessionId;
	}

	/**
	 * @generated
	 */
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		SessionQuestionId id = (SessionQuestionId) obj;
		return (this.questionId.equals(id.getIdPerguntasFormularios()) && this.sessionId.equals(id.getIdSessao()));
	}

	/**
	 * @generated
	 */
	public int hashCode() {
		return this.questionId.hashCode() + this.sessionId.hashCode();
	}

	public Integer getIdPerguntasFormularios() {
		return questionId;
	}

	public Integer getIdSessao() {
		return sessionId;
	}
}