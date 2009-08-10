package br.ufrj.nce.labase.phidias.aplicador;

import java.util.ArrayList;
import java.util.List;

import br.ufrj.nce.labase.phidias.persistence.model.Answer;
import br.ufrj.nce.labase.phidias.persistence.model.Question;
import br.ufrj.nce.labase.phidias.persistence.model.QuestionPK;

public class Fase1Bean {

	private List<Question> questoes;
	
	private String respostaJogador;
	
	private String estimuloNPC;

	public List<Question> getQuestoes() {
		//Recupera questoes fase1
		List<Question> questoes= new ArrayList<Question>();
		Question questao1= new Question();
		questoes.add(questao1);
		
		QuestionPK questionPk1 = new QuestionPK();
		questionPk1.setQuestionID(1);
		questionPk1.setQuestionnaireId(1);
		
		questao1.setPk(questionPk1);
		questao1.setQuestion("Coloca os personagens na posição de sujeito.");
		
		Answer resposta_q1_1 = new Answer();
		resposta_q1_1.setAnswerDesc("Sim");
		Answer resposta_q1_2 = new Answer();
		resposta_q1_2.setAnswerDesc("Não");
		
		questao1.add(resposta_q1_1);
		questao1.add(resposta_q1_2);
		
		Question questao2= new Question();
		questoes.add(questao2);
		
		QuestionPK questionPk2 = new QuestionPK();
		questionPk2.setQuestionID(2);
		questionPk2.setQuestionnaireId(1);
		
		questao2.setPk(questionPk1);
		questao2.setQuestion("Utiliza mais de um complemento para o verbo.");


		Answer resposta_q2_1 = new Answer();
		resposta_q1_1.setAnswerDesc("Sim");
		Answer resposta_q2_2 = new Answer();
		resposta_q1_2.setAnswerDesc("Não");

		questao2.add(resposta_q2_1);
		questao2.add(resposta_q2_2);
		
		Question questao3= new Question();
		questao3.setQuestion("Utiliza um personagem para cada ação.");
		questoes.add(questao3);
		
		Question questao4= new Question();
		questao4.setQuestion("Utiliza um sujeito, um verbo e um complemento como complemento de outra ação.");
		questoes.add(questao4);
		
		Question questao5= new Question();
		questao5.setQuestion("Cria complementos de tempo, modo e lugar para as ações.");
		questoes.add(questao5);
		
		Question questao6= new Question();
		questao6.setQuestion("Utiliza personagens sem ações, mas com complemento.");
		questoes.add(questao6);
		
		Question questao7= new Question();
		questao7.setQuestion("Utiliza diferentes sujeitos praticando a mesma ação.");
		questoes.add(questao7);
		
		Question questao8= new Question();
		questao8.setQuestion("Utiliza mesmo sujeito com diferentes ações.");
		questoes.add(questao8);
		
		Question questao9= new Question();
		questao9.setQuestion("Utiliza diferentes sujeitos praticando diferentes ações.");
		questoes.add(questao9);
		this.setQuestoes(questoes);

		
		return questoes;
	}

	public void setQuestoes(List<Question> questoes) {
		this.questoes = questoes;
	}

	public String getRespostaJogador() {
		return respostaJogador;
	}

	public void setRespostaJogador(String respostaJogador) {
		this.respostaJogador = respostaJogador;
	}

	public String getEstimuloNPC() {
		return estimuloNPC;
	}

	public void setEstimuloNPC(String estimuloNPC) {
		this.estimuloNPC = estimuloNPC;
	}
}
