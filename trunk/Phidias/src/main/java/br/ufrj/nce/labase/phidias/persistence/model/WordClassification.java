package br.ufrj.nce.labase.phidias.persistence.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @generated
 */
@Entity
@Table(name = "classificacao_palavra")
public class WordClassification implements java.io.Serializable {
	/**
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CLP_ID_CLASSIFICACAO", length = 10, nullable = false)
	private Integer id;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "CLP_ID_CLASSIFICACAO", nullable = false)
	private java.util.Set<Keyword> keywords = new java.util.HashSet<Keyword>();

	/**
	 * @generated
	 */
	@Column(name = "CLP_DS_CLASSIFICACAO", length = 40, nullable = false)
	private String classification;
	/**
	 * @generated
	 */
	private static final long serialVersionUID = 82003840L;

	/**
	 * @generated
	 */
	public WordClassification() {
	}

	/**
	 * @generated
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * @generated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @generated
	 */
	public String getClassification() {
		return this.classification;
	}

	/**
	 * @generated
	 */
	public void setClassification(String classification) {
		this.classification = classification;
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "ClassificacaoPalavra" + " id=" + id + " clpDsClassificicacao=" + classification;
	}

	/**
	 * @generated
	 */
	public java.util.Set<Keyword> getKeywords() {
		return keywords;
	}

	/**
	 * @generated
	 */
	public void setKeywords(java.util.Set<Keyword> keywords) {
		this.keywords = keywords;
	}

	/**
	 * @generated
	 */
	public void addKeyword(Keyword keyword) {
		getKeywords().add(keyword);
	}

	/**
	 * @generated
	 */
	public void removeKeyword(Keyword keyword) {
		getKeywords().remove(keyword);
	}
}