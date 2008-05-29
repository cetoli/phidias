package br.ufrj.nce.labase.phidias.persistence.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * @generated
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "tipo_estimulo")
public class StimulusType implements java.io.Serializable {

	/**
	 * @generated
	 */
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@javax.persistence.Column(name = "TIE_ID_TIPO_ESTIMULO", length = 10, nullable = false)
	private int id;
	/**
	 * @generated
	 */
	@javax.persistence.Column(name = "TIE_DS_TIPO_ESTIMULO", length = 50)
	private String description;
	/**
	 * @generated
	 */
	private static final long serialVersionUID = 1496418459L;

	/**
	 * @generated
	 */
	public StimulusType() {
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
		return "TipoAcao" + " id=" + id + " tieDsTipoestimulo=" + description;
	}

	public String getDescription() {
		return description;
	}

	public void setTieDsTipoestimulo(String description) {
		this.description = description;
	}
}