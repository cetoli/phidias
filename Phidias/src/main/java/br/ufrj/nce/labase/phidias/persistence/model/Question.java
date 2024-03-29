package br.ufrj.nce.labase.phidias.persistence.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "respostas_perguntas",
        joinColumns = {@JoinColumn(name = "RES_QUE_ID_QUESTIONARIO", referencedColumnName = "QUE_ID_QUESTIONARIO"), 
    		@JoinColumn(name = "RES_PEF_ID_PERGUNTA", referencedColumnName = "PEF_ID_PERGUNTA")},  
        inverseJoinColumns = {@JoinColumn(name = "RES_ID_RESPOSTA", referencedColumnName = "RES_ID_RESPOSTA"), 
    		@JoinColumn(name = "QUE_ID_QUESTIONARIO", referencedColumnName = "QUE_ID_QUESTIONARIO"),
    		@JoinColumn(name = "PEF_ID_PERGUNTA", referencedColumnName = "PEF_ID_PERGUNTA")})
	private Set<Answer> answerList;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "trabalha",
        joinColumns = {@JoinColumn(name = "HAB_ID_HABILIDADE"), @JoinColumn(name = "TPH_ID_TIPO_HABILIDADE")},  
        inverseJoinColumns = {@JoinColumn(name = "QUE_ID_QUESTIONARIO"), @JoinColumn(name = "PEF_ID_PERGUNTA")})
	private Set<Skill> skillList;

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

	public Set<Answer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(Set<Answer> answerList) {
		this.answerList = answerList;
	}

	public Set<Skill> getSkillList() {
		return skillList;
	}

	public void setSkillList(Set<Skill> skillList) {
		this.skillList = skillList;
	}
}