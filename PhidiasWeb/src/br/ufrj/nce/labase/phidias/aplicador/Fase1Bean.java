package br.ufrj.nce.labase.phidias.aplicador;

import java.util.ArrayList;
import java.util.List;

import br.ufrj.nce.labase.phidias.persistence.model.Question;

public class Fase1Bean {

	private List<QuestionUI> questoesUI;

	private Object respostasQuestionario;
	
	private String respostaJogador;

	private String estimuloNPC;

	private String comentario;

	public Object getRespostasQuestionario() {
		return respostasQuestionario;
	}

	public void setRespostasQuestionario(Object respostasQuestionario) {
		this.respostasQuestionario = respostasQuestionario;
	}

	public List<QuestionUI> getQuestoesUI() {
		return questoesUI;
	}

	public void setQuestoesUI(List<QuestionUI> questoesUI) {
		this.questoesUI = questoesUI;
	}

	public void setQuestoes(int sessionId, List<Question> questoes) {
		if (questoes != null) {
			this.questoesUI = new ArrayList<QuestionUI>();
			for (Question questao : questoes) {
				this.questoesUI.add(new QuestionUI(sessionId, questao));
			}
		}
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
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
