package br.ufrj.nce.labase.phidias.persistence.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @generated
 */
@Entity
@Table(name = "perguntas")
public class Question implements java.io.Serializable {
	private static final long serialVersionUID = 8303415617187735765L;

	@EmbeddedId
	private QuestionPK pk;

	@Column(name = "PEF_TX_PERGUNTA", length = 200)
	private String question;
	
	@Column(name = "PEF_TP_PERGUNTA")
	private String questionType;
	
	@Column(name = "PEF_TP_CRIVO")
	private String evalType;
	
	@Column(name = "PEF_COMENTARIO", length = 200)
	private String comments;

	public QuestionPK getPk() {
		return pk;
	}

	public void setPk(QuestionPK pk) {
		this.pk = pk;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getEvalType() {
		return evalType;
	}

	public void setEvalType(String evalType) {
		this.evalType = evalType;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}