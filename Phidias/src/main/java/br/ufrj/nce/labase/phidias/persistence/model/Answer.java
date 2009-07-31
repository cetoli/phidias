package br.ufrj.nce.labase.phidias.persistence.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "respostas")
public class Answer implements java.io.Serializable {
	private static final long serialVersionUID = 363451181742958087L;

	@EmbeddedId
	private AnswerPK pk;
	
	@Column(name = "RES_DS_RESPOSTA")
	private String answerDesc;
	
	@Column(name = "RES_PESO")
	private Integer answerLoad;

	public AnswerPK getPk() {
		return pk;
	}

	public void setPk(AnswerPK pk) {
		this.pk = pk;
	}

	public String getAnswerDesc() {
		return answerDesc;
	}

	public void setAnswerDesc(String answerDesc) {
		this.answerDesc = answerDesc;
	}

	public int getAnswerLoad() {
		return answerLoad;
	}

	public void setAnswerLoad(int answerLoad) {
		this.answerLoad = answerLoad;
	}
}
