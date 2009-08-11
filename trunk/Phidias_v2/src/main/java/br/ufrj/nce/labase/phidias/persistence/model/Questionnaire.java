package br.ufrj.nce.labase.phidias.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "questionarios")
public class Questionnaire implements java.io.Serializable {
	private static final long serialVersionUID = -7510294653727352496L;

	@Id
	@Column(name = "QUE_ID_QUESTIONARIO")
	private long questionnaireId;
	
	@Column(name = "QUE_NM_QUESTIONARIO")
	private String questionnaireName;

	public long getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(long questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	/**
	 * @return the questionnaireName
	 */
	public String getQuestionnaireName() {
		return questionnaireName;
	}

	/**
	 * @param questionnaireName the questionnaireName to set
	 */
	public void setQuestionnaireName(String questionnaireName) {
		this.questionnaireName = questionnaireName;
	}
}
