package br.ufrj.nce.labase.phidias.persistence.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


/**
 * @generated
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "sessao_perguntas")
public class SessionQuestion implements java.io.Serializable {
	/**
	 * @generated
	 */
	@javax.persistence.EmbeddedId
	@GeneratedValue(strategy = GenerationType.AUTO)
	private SessionQuestionId id;
	/**
	 * @generated
	 */
	@javax.persistence.Column(name = "spf_tx_resposta", length = 200, nullable = false)
	private String answer;
	
	@Column(name = "SEP_PROB_DIAG", length = 500)
	private String diagProb;
	
	/**
	 * @generated
	 */
	private static final long serialVersionUID = -2001521282L;

	/**
	 * @generated
	 */
	public SessionQuestion() {
	}

	/**
	 * @generated
	 */
	public SessionQuestionId getId() {
		return this.id;
	}

	/**
	 * @generated
	 */
	public void setId(SessionQuestionId id) {
		this.id = id;
	}

	/**
	 * @generated
	 */
	public String getAnswer() {
		return this.answer;
	}

	/**
	 * @generated
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "SessaoPerguntasFormulario" + " spfTxResposta=" + answer;
	}

	public String getDiagProb() {
		return diagProb;
	}

	public void setDiagProb(String diagProb) {
		this.diagProb = diagProb;
	}
}