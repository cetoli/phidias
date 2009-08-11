package br.ufrj.nce.labase.phidias.sessao;

import java.util.List;

import br.ufrj.nce.labase.phidias.persistence.dao.SessionDAO;
import br.ufrj.nce.labase.phidias.persistence.model.Session;

public class SessaoBean {

	private Session sessaoAtiva;

	private List<Session> sessoes;

	public Session getSessaoAtiva() {
		return sessaoAtiva;
	}

	public void setSessaoAtiva(Session sessaoAtiva) {
		this.sessaoAtiva = sessaoAtiva;
	}

	public List<Session> getSessoes() {
		return sessoes;
	}

	public void setSessoes(List<Session> sessoes) {
		this.sessoes = sessoes;
	}

	public SessaoBean() {

	}

	public String listarSessoes() {
		// TODO Auto-generated constructor stub
		SessionDAO sessaoDAO = new SessionDAO();

		this.sessoes = sessaoDAO.findAll();

		return "listagem_sessao";
	}
}
