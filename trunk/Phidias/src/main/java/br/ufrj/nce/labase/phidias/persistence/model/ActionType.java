package br.ufrj.nce.labase.phidias.persistence.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * @generated
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "tipo_acao")
public class ActionType implements java.io.Serializable {

	/**
	 * @generated
	 */
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@javax.persistence.Column(name = "tia_id_tipoacao", length = 10, nullable = false)
	private int id;
	/**
	 * @generated
	 */
	@javax.persistence.Column(name = "tia_ds_tipoacao", length = 50)
	private String description;
	/**
	 * @generated
	 */
	private static final long serialVersionUID = 1496418459L;

	/**
	 * @generated
	 */
	public ActionType() {
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
	public String toString() {
		return "TipoAcao" + " id=" + id + " tiaDsTipoacao=" + getDescription();
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}