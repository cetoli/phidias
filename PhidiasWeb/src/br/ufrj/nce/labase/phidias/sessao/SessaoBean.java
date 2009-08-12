package br.ufrj.nce.labase.phidias.sessao;

import java.util.List;

import br.ufrj.nce.labase.phidias.common.ManagedBean;
import br.ufrj.nce.labase.phidias.persistence.dao.ActionDAO;
import br.ufrj.nce.labase.phidias.persistence.dao.SessionDAO;
import br.ufrj.nce.labase.phidias.persistence.dao.SessionGamePhaseStimulusTypeDAO;
import br.ufrj.nce.labase.phidias.persistence.model.Action;
import br.ufrj.nce.labase.phidias.persistence.model.Session;
import br.ufrj.nce.labase.phidias.persistence.model.SessionGamePhaseStimulusType;

public class SessaoBean extends ManagedBean {

	private Session sessaoAtiva;

	public Session getSessaoAtiva() {
		return sessaoAtiva;
	}

	public void setSessaoAtiva(Session sessaoAtiva) {
		this.sessaoAtiva = sessaoAtiva;
	}

	public SessaoBean() {

	}

	public String listarSessoes() {
		// TODO Auto-generated constructor stub
		SessionDAO sessaoDAO = new SessionDAO();
		this.setSessionAttribute("sessoes", sessaoDAO.findAll());

		return "listagem_sessao";
	}
	
	public List<Action> getEventosJogo(){
		int sessaoId = ((SessaoBean) getSessionAttribute("sessaoBean")).getSessaoAtiva().getId();
		
		ActionDAO dao = new ActionDAO();
		return dao.listActions(sessaoId, 1);
	}
}
