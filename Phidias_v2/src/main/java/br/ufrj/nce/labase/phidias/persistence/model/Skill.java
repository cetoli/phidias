package br.ufrj.nce.labase.phidias.persistence.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "habilidades")
public class Skill implements java.io.Serializable {
	private static final long serialVersionUID = 7572663531325594047L;

	@EmbeddedId
	private SkillPK pk;
	
	@Column(name = "HAB_NM_HABILIDADE")
	private String skillName;
	
	@Column(name = "HAB_PESO_HABILIDADE")
	private Integer skillLoad;
	
	@Column(name = "HAB_COMPORTAMENTO")
	private String skillBehavior;

	public SkillPK getPk() {
		return pk;
	}

	public void setPk(SkillPK pk) {
		this.pk = pk;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public Integer getSkillLoad() {
		return skillLoad;
	}

	public void setSkillLoad(Integer skillLoad) {
		this.skillLoad = skillLoad;
	}

	public String getSkillBehavior() {
		return skillBehavior;
	}

	public void setSkillBehavior(String skillBehavior) {
		this.skillBehavior = skillBehavior;
	}
}
