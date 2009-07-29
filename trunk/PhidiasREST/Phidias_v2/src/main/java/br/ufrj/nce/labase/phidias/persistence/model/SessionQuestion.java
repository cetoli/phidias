package br.ufrj.nce.labase.phidias.persistence.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


/**
 * @generated
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "sessao_perguntas_formulario")
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
}