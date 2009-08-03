package br.ufrj.nce.labase.phidias.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipos_habilidade")
public class SkillType implements java.io.Serializable {
	private static final long serialVersionUID = -8785456257602375649L;

	@Id
	@Column(name = "TPH_ID_TIPO_HABILIDADE")
	private long skillTypeId;
	
	@Column(name = "TPH_DS_TIPO_HABILIDADE")
	private String skillTypeDesc;

	public long getSkillTypeId() {
		return skillTypeId;
	}

	public void setSkillTypeId(long skillTypeId) {
		this.skillTypeId = skillTypeId;
	}

	public String getSkillTypeDesc() {
		return skillTypeDesc;
	}

	public void setSkillTypeDesc(String skillTypeDesc) {
		this.skillTypeDesc = skillTypeDesc;
	}
}
