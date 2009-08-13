package br.ufrj.nce.labase.phidias.pergunta;

import java.util.ArrayList;

import javax.faces.model.SelectItem;

import org.richfaces.component.html.HtmlDataTable;

import br.ufrj.nce.labase.phidias.common.Util;
import br.ufrj.nce.labase.phidias.dao.QuestionDAO;
import br.ufrj.nce.labase.phidias.persistence.EntityManagerHelper;

import br.ufrj.nce.labase.phidias.persistence.model.Question;
import br.ufrj.nce.labase.phidias.persistence.model.QuestionPK;
import br.ufrj.nce.labase.phidias.persistence.model.Questionnaire;

public class PerguntaBean {
	
	private Question question;
	private HtmlDataTable tabela = null;
	//private Integer questionarioAtual;
	
	public Question getQuestion() {
		//question.getPk().getQuestionID();
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public HtmlDataTable getTabela() {
		return tabela;
	}
	public void setTabela(HtmlDataTable tabela) {
		this.tabela = tabela;
	}

	public PerguntaBean() {
		this.question = new Question();
		this.question.setPk(new QuestionPK());		
	}
	
	public String goToPergunta(){		
		return "pergunta";
	}

	public ArrayList<SelectItem> getQuestionarios(){
		
		ArrayList<SelectItem> lista = new ArrayList<SelectItem>();
		String sql = "select * from questionarios";		
		ArrayList<Questionnaire> resultList = Util.convertListForArrayList( EntityManagerHelper.getInstance().getEntityManager().createNativeQuery(sql,Questionnaire.class).getResultList() );
				
		for (Questionnaire q : resultList){
			lista.add(new SelectItem(q.getQuestionnaireId(),q.getQuestionnaireName()));
		}
			
		/*
		lista.add(new SelectItem("1","A"));
		lista.add(new SelectItem("2","B"));
		lista.add(new SelectItem("3","C"));*/
		
		return lista;
	}	
	/*
	public Integer getQuestionarioAtual() {
		return questionarioAtual;
	}
	public void setQuestionarioAtual(Integer questionarioAtual) {
		this.questionarioAtual = questionarioAtual;
	}	
	*/
	public String cadastrar(){
		
		EntityManagerHelper.getInstance().startTransaction();
		
		QuestionDAO dao = new QuestionDAO();
		dao.create(question);
		//GameDAO dao = new GameDAO();
		//dao.create(game);		
				
		EntityManagerHelper.getInstance().commitTransaction();
		
		return "sucesso";
	}
	
	public String consultar(){
		
		QuestionDAO dao = new QuestionDAO();
		question = dao.findById(Question.class, question.getPk().getQuestionID());		
		
		return "sucesso";
	}
	
	public ArrayList<Question> getConsultarTodos(){
		
		String sql = "select * from perguntas";
		ArrayList<Question> perguntas = Util.convertListForArrayList( EntityManagerHelper.getInstance().getEntityManager().createNativeQuery(sql,Question.class).getResultList() );
		/*System.out.println("------------------------------------------> " + perguntas.size());
		for (Question q : perguntas){
			System.out.println("pergunta " + q.getQuestion());
		}*/
		
		return perguntas;
	}
	public String excluir(){
		
		this.question = (Question)tabela.getRowData();
				
		EntityManagerHelper.getInstance().startTransaction();

		QuestionDAO dao = new QuestionDAO();
		dao.delete(this.question);		
				
		EntityManagerHelper.getInstance().commitTransaction();
		
		return "sucesso";
	}
	
	public String consultaedicao(){		
		this.question = (Question)tabela.getRowData();		
		return "editar";
	}
	
	public String alterar(){
		EntityManagerHelper.getInstance().startTransaction();

		QuestionDAO dao = new QuestionDAO();
		dao.update(this.question);		
				
		EntityManagerHelper.getInstance().commitTransaction();	
		
		return "sucesso";
	}
}
