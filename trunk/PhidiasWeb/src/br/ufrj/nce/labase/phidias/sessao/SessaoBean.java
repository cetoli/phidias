package br.ufrj.nce.labase.phidias.sessao;

import java.util.ArrayList;
import java.util.List;

import br.ufrj.nce.labase.phidias.persistence.model.Attendant;
import br.ufrj.nce.labase.phidias.persistence.model.Game;
import br.ufrj.nce.labase.phidias.persistence.model.GamePhase;
import br.ufrj.nce.labase.phidias.persistence.model.Patient;
import br.ufrj.nce.labase.phidias.persistence.model.Session;

public class SessaoBean {

	private List<Session> sessoes;
	public List<Session> getSessoes() {
		return sessoes;
	}
	public void setSessoes(List<Session> sessoes) {
		this.sessoes = sessoes;
	}
	public SessaoBean() {
		// TODO Auto-generated constructor stub
		Session session = new Session();
		session.setId(10);
		
		Game game = new Game();
		game.setId(1);
		game.setName("Jogo da Roda da Linguagem");		
		session.setGame(game);
		
		Attendant aplicador = new Attendant();
		aplicador.setName("André Moraes");
		session.setAttendant(aplicador);

		Patient paciente = new Patient();
		paciente.setName("Criança 1");
		session.setPatient(paciente);
		
		GamePhase fase = new GamePhase();
		fase.setGame(game);
		//session.setGamePhase(fase);
		
		sessoes = new ArrayList<Session>();
		sessoes.add(session);

	}

	public String listarSessoes(){
		return "listagem_sessao";		
	}
}
