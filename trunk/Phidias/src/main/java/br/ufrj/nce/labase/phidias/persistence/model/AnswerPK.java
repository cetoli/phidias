package br.ufrj.nce.labase.phidias.persistence.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AnswerPK implements java.io.Serializable {
	private static final long serialVersionUID = -3316455528778318384L;

	@Column(name = "QUE_ID_QUESTIONARIO")
	private long questionnaireId;
	
	@Column(name = "PEF_ID_PERGUNTA")
	private long questionId;
	
	@Column(name = "RES_ID_RESPOSTA")
	private long answerId;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (answerId ^ (answerId >>> 32));
		result = prime * result + (int) (questionId ^ (questionId >>> 32));
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
		AnswerPK other = (AnswerPK) obj;
		if (answerId != other.answerId)
			return false;
		if (questionId != other.questionId)
			return false;
		if (questionnaireId != other.questionnaireId)
			return false;
		return true;
	}

	public long getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(long questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(long answerId) {
		this.answerId = answerId;
	}
}
