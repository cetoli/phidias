package br.ufrj.nce.labase.phidias.aplicador;

import java.util.List;

import br.ufrj.nce.labase.phidias.persistence.model.Question;

public class Fase1Bean {

	private List<Question> questoes;

	private String respostaJogador;

	private String estimuloNPC;

	private String comentario;

	public List<Question> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Question> questoes) {
		this.questoes = questoes;
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
