package br.ufrj.nce.labase.phidias.persistence.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SkillPK implements java.io.Serializable {
	private static final long serialVersionUID = 8262522538378102855L;

	@Column(name = "TPH_ID_TIPO_HABILIDADE")
	private long skillTypeId;
	
	@Column(name = "HAB_ID_HABILIDADE")
	private long skillId;

	public long getSkillTypeId() {
		return skillTypeId;
	}

	public void setSkillTypeId(long skillTypeId) {
		this.skillTypeId = skillTypeId;
	}

	public long getSkillId() {
		return skillId;
	}

	public void setSkillId(long skillId) {
		this.skillId = skillId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (skillId ^ (skillId >>> 32));
		result = prime * result + (int) (skillTypeId ^ (skillTypeId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SkillPK other = (SkillPK) obj;
		if (skillId != other.skillId)
			return false;
		if (skillTypeId != other.skillTypeId)
			return false;
		return true;
	}
}
