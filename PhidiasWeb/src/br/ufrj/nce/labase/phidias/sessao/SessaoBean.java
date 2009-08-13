package br.ufrj.nce.labase.phidias.sessao;

import java.util.ArrayList;
import java.util.List;

import br.ufrj.nce.labase.phidias.common.ManagedBean;
import br.ufrj.nce.labase.phidias.persistence.dao.ActionDAO;
import br.ufrj.nce.labase.phidias.persistence.dao.SessionDAO;
import br.ufrj.nce.labase.phidias.persistence.model.Action;
import br.ufrj.nce.labase.phidias.persistence.model.ActionMovement;
import br.ufrj.nce.labase.phidias.persistence.model.Session;

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
	
	public List<ActionMovement> getEventosJogo(){
		int sessaoId = ((SessaoBean) getSessionAttribute("sessaoBean")).getSessaoAtiva().getId();
		
		ActionDAO dao = new ActionDAO();
		List<Action> actionList = dao.listActions(sessaoId, 1);
		
		List<ActionMovement> resultList = new ArrayList<ActionMovement>();
		for (Action action : actionList) {
			resultList.addAll(action.getActionMovements());
		}
		return resultList;
	}
}
