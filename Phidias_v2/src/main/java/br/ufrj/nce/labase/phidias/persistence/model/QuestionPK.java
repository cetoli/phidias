package br.ufrj.nce.labase.phidias.persistence.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class QuestionPK implements java.io.Serializable {
	private static final long serialVersionUID = 8769878340163638125L;

	@Column(name = "QUE_ID_QUESTIONARIO")
	private long questionnaireId;
	
	@Column(name = "PEF_ID_PERGUNTA")
	private long questionID;

	public long getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(long questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	public long getQuestionID() {
		return questionID;
	}

	public void setQuestionID(long questionID) {
		this.questionID = questionID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (questionID ^ (questionID >>> 32));
		result = prime * result
				+ (int) (questionnaireId ^ (questionnaireId >>> 32));
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
		QuestionPK other = (QuestionPK) obj;
		if (questionID != other.questionID)
			return false;
		if (questionnaireId != other.questionnaireId)
			return false;
		return true;
	}
}
