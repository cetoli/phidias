package br.ufrj.nce.labase.phidias.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "area_disciplinar")
public class DisciplinaryArea implements java.io.Serializable {
	private static final long serialVersionUID = -4614228308066185319L;

	@Id
	@Column(name = "ADI_ID_AREADISCIPLINAR")
	private long disciplinaryAreaId;
	
	@Column(name = "ADI_NM_AREADISCIPLINAR")
	private String disciplinaryAreaName;

	public long getDisciplinaryAreaId() {
		return disciplinaryAreaId;
	}

	public void setDisciplinaryAreaId(long disciplinaryAreaId) {
		this.disciplinaryAreaId = disciplinaryAreaId;
	}

	public String getDisciplinaryAreaName() {
		return disciplinaryAreaName;
	}

	public void setDisciplinaryAreaName(String disciplinaryAreaName) {
		this.disciplinaryAreaName = disciplinaryAreaName;
	}
}
