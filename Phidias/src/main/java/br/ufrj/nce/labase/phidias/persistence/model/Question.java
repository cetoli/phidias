package br.ufrj.nce.labase.phidias.persistence.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * @generated
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "perguntas_formulario")
public class Question implements java.io.Serializable {
	/**
	 * @generated
	 */
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@javax.persistence.Column(name = "pef_id_perguntaformulario", length = 10, nullable = false)
	private int id;
	/**
	 * @generated
	 */
	@javax.persistence.Column(name = "pef_tx_pergunta", length = 200)
	private String question;
	/**
	 * @generated
	 */
	private static final long serialVersionUID = -837200834L;

	/**
	 * @generated
	 */
	public Question() {
	}

	/**
	 * @generated
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @generated
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @generated
	 */
	public String getQuestion() {
		return this.question;
	}

	/**
	 * @generated
	 */
	public void setPefTxPergunta(String question) {
		this.question = question;
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "PerguntasFormulario" + " id=" + id + " pefTxPergunta=" + question;
	}
}