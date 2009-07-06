package br.ufrj.nce.labase.phidias.persistence.model;

import java.util.GregorianCalendar;

/**
 * @generated
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "paciente")
public class Patient implements java.io.Serializable {
	/**
	 * @generated
	 */
	@javax.persistence.Id
	@javax.persistence.Column(name = "pac_id_login", length = 10, nullable = false)
	private String id;
	/**
	 * @generated
	 */
	@javax.persistence.Column(name = "pac_nm_paciente", length = 50, nullable = false)
	private String name;
	/**
	 * @generated
	 */
	@javax.persistence.Column(name = "pac_dt_nascimento", length = 10)
	private GregorianCalendar birthday;
	/**
	 * @generated
	 */
	@javax.persistence.Column(name = "pac_id_sexo")
	private Character gender;

	/**
	 * @generated
	 */
	private static final long serialVersionUID = -66586904L;

	/**
	 * @generated
	 */
	public Patient() {
	}

	/**
	 * @generated
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @generated
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GregorianCalendar getBirthday() {
		return birthday;
	}

	public void setBirthday(GregorianCalendar birthday) {
		this.birthday = birthday;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "Paciente" + " id=" + id + " pacNmPaciente=" + name + " pacDtNascimento=" + birthday + " pacIdSexo=" + gender;
	}
}