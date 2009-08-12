package br.ufrj.nce.labase.phidias.persistence.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.model.SelectItem;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @generated
 */
@Entity
@Table(name = "perguntas")
public class Question implements java.io.Serializable {
	public boolean add(Answer arg0) {
		if(answerList == null)
			answerList = new HashSet<Answer>();
		
		return answerList.add(arg0);
	}

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
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumns({
    @JoinColumn(name="QUE_ID_QUESTIONARIO", referencedColumnName="QUE_ID_QUESTIONARIO"),
    @JoinColumn(name="PEF_ID_PERGUNTA", referencedColumnName="PEF_ID_PERGUNTA")})
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
	
	public List<SelectItem> getAnswersSelectItems(){
		List<SelectItem> listAnswer = new ArrayList<SelectItem>();
		for(Answer answer: this.getAnswerList()){
			SelectItem si = new SelectItem();
			si.setLabel(answer.getAnswerDesc());
			si.setValue(String.valueOf(answer.getPk().getAnswerId()));
			listAnswer.add(si);
		}
		
		return listAnswer;
	}
}