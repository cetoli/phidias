package br.ufrj.nce.labase.phidias.persistence.model;


/**
 * @generated
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "aplicador")
public class Attendant implements java.io.Serializable {
	/**
	 * @generated
	 */
	@javax.persistence.Id
	@javax.persistence.Column(name = "apl_id_login", length = 10, nullable = false)
	private Integer id;
	/**
	 * @generated
	 */
	@javax.persistence.Column(name = "apl_nm_aplicador", length = 50, nullable = false)
	private String name;
	/**
	 * @generated
	 */
	@javax.persistence.Column(name = "apl_ds_profissao", length = 50)
	private String profession;
	/**
	 * @generated
	 */
	@javax.persistence.Column(name = "apl_ds_instituicao", length = 70)
	private String institution;

	/**
	 * @generated
	 */
	private static final long serialVersionUID = -954480346L;

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
	public String toString() {
		return "Aplicador" + " id=" + id + " aplNmAplicador=" + name + " aplDsProfissao=" + profession + " aplDsInstituicao=" + institution;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getProfession() {
		return profession;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getInstitution() {
		return institution;
	}

}