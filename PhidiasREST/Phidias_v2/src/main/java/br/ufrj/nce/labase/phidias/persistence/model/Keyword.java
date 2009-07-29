package br.ufrj.nce.labase.phidias.persistence.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * @generated
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "palavras_chave")
public class Keyword implements java.io.Serializable {
	/**
	 * @generated
	 */
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@javax.persistence.Column(name = "pac_id_palavra", length = 10, nullable = false)
	private int id;
	/**
	 * @generated
	 */
	@javax.persistence.Column(name = "pac_ds_palavra", length = 40, nullable = false)
	private String description;
	/**
	 * @generated
	 */
	private static final long serialVersionUID = 82003840L;

	/**
	 * @generated
	 */
	public Keyword() {
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
	public String getDescription() {
		return this.description;
	}

	/**
	 * @generated
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "Keyword" + " id=" + id + " pacDsPalavra=" + description;
	}
}