package br.ufrj.nce.labase.phidias.persistence.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @generated
 */
@Embeddable
public class SessionGamePhaseId implements java.io.Serializable {

	/**
	 * @generated
	 */
	@Column(name = "FAJ_ID_FASEJOGO")
	private Integer phaseId;
	/**
	 * @generated
	 */
	@Column(name = "SES_ID_SESSAO")
	private Integer sessionId;
	/**
	 * @generated
	 */
	private static final long serialVersionUID = 965003916L;

	/**
	 * @generated
	 */
	public SessionGamePhaseId(Integer phaseId, Integer sessionId) {
		this.phaseId = phaseId;
		this.sessionId = sessionId;
	}

	public SessionGamePhaseId() {
		super();
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
		return (this.phaseId.equals(id.getIdPerguntasFormularios()) && this.sessionId
				.equals(id.getIdSessao()));
	}

	/**
	 * @generated
	 */
	public int hashCode() {
		return this.phaseId.hashCode() + this.sessionId.hashCode();
	}

	public Integer getPhaseId() {
		return phaseId;
	}

	public Integer getSessionId() {
		return sessionId;
	}
}