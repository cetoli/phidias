package br.ufrj.nce.labase.phidias.questionario;

import java.util.ArrayList;

import org.richfaces.component.html.HtmlDataTable;

import br.ufrj.nce.labase.phidias.common.Util;
import br.ufrj.nce.labase.phidias.persistence.EntityManagerHelper;
import br.ufrj.nce.labase.phidias.persistence.dao.QuestionnaireDAO;
import br.ufrj.nce.labase.phidias.persistence.model.Questionnaire;

public class QuestionarioBean {
	
	private Questionnaire questionario = null;	
	
	private HtmlDataTable tabela = null;
	
	public QuestionarioBean() {	
		questionario = new Questionnaire();		
	}
	
	public Questionnaire getQuestionnaire() {
		return questionario;
	}
	public void setQuestionnaire(Questionnaire questionario) {
		this.questionario = questionario;
	}	

	public String goToQuestionario(){		
		return "questionario";
	}
	
	public HtmlDataTable getTabela() {
		return tabela;
	}

	public void setTabela(HtmlDataTable tabela) {
		this.tabela = tabela;
	}

	public String cadastrar(){
		
		EntityManagerHelper.getInstance().startTransaction();

		QuestionnaireDAO dao = new QuestionnaireDAO();
		dao.create(questionario);		
				
		EntityManagerHelper.getInstance().commitTransaction();
		
		return "sucesso";
	}
	
	public String consultaedicao(){		
		this.questionario = (Questionnaire)tabela.getRowData();		
		return "editar";
	}
	
	public String alterar(){
		EntityManagerHelper.getInstance().startTransaction();

		QuestionnaireDAO dao = new QuestionnaireDAO();
		dao.update(this.questionario);		
				
		EntityManagerHelper.getInstance().commitTransaction();	
		
		return "sucesso";
	}
	
	public String excluir(){		
		questionario = (Questionnaire)tabela.getRowData();
		
		EntityManagerHelper.getInstance().startTransaction();

		QuestionnaireDAO dao = new QuestionnaireDAO();
		dao.delete(questionario);		
				
		EntityManagerHelper.getInstance().commitTransaction();
		
		return "sucesso";
	}
	
	public String consultar(){
		
		QuestionnaireDAO dao = new QuestionnaireDAO();
		questionario = dao.findById(Questionnaire.class, questionario.getQuestionnaireId());		
		
		return "sucesso";
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Questionnaire> getConsultarTodos(){
				
		String sql = "select * from questionarios";
		ArrayList<Questionnaire> resultList = Util.convertListForArrayList( EntityManagerHelper.getInstance().getEntityManager().createNativeQuery(sql,Questionnaire.class).getResultList() );
		/*for (Questionnaire jogo : resultList) {
			System.out.println("-----------> nome do jogo : " + jogo.getName());
		}*/
		return resultList;
	}
	
}
