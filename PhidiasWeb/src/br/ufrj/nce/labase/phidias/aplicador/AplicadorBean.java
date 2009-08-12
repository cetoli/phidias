package br.ufrj.nce.labase.phidias.aplicador;

import java.util.List;

import javax.faces.event.ActionEvent;

import br.ufrj.nce.labase.phidias.common.ManagedBean;
import br.ufrj.nce.labase.phidias.login.UsuarioLogin;
import br.ufrj.nce.labase.phidias.persistence.dao.QuestionnaireDAO;
import br.ufrj.nce.labase.phidias.persistence.dao.SessionDAO;
import br.ufrj.nce.labase.phidias.persistence.dao.SessionGamePhaseDAO;
import br.ufrj.nce.labase.phidias.persistence.dao.SessionGamePhaseStimulusTypeDAO;
import br.ufrj.nce.labase.phidias.persistence.model.Question;
import br.ufrj.nce.labase.phidias.persistence.model.SessionGamePhase;
import br.ufrj.nce.labase.phidias.persistence.model.SessionGamePhaseId;
import br.ufrj.nce.labase.phidias.persistence.model.SessionGamePhaseStimulusType;
import br.ufrj.nce.labase.phidias.sessao.SessaoBean;

public class AplicadorBean extends ManagedBean {

	private Fase1Bean fase1;

	public Fase1Bean getFase1() {
		return fase1;
	}

	public void setFase1(Fase1Bean fase1) {
		this.fase1 = fase1;
	}

	private String paramsessaoid;

	public String getParamsessaoid() {
		return paramsessaoid;
	}

	public void setParamsessaoid(String paramsessaoid) {
		this.paramsessaoid = paramsessaoid;
	}

	public AplicadorBean() {
		this.fase1 = new Fase1Bean();

	}

	public void registrarEstimulo(ActionEvent event) {
		int sessaoId = ((SessaoBean) getSessionAttribute("sessaoBean")).getSessaoAtiva().getId();
		String estimulo = this.getFase1().getEstimuloNPC();

		SessionGamePhaseStimulusType stimulus = new    SessionGamePhaseStimulusType();
		stimulus.setSessionId(sessaoId);
		//Fase1
		stimulus.setPhaseId(1);
		//Tipo de estimulo NPC
		stimulus.setStimulusTypeId(1);
		stimulus.setStimulusText(estimulo);

		SessionGamePhaseStimulusTypeDAO sgpstDAO = new       SessionGamePhaseStimulusTypeDAO();
		sgpstDAO.create(stimulus);

		this.setMensagem("Estimulo enviado com sucesso!");
	}

	public void registrarRespostaJogador() {
		System.out.println(this.getFase1().getRespostaJogador());
		this.setMensagem("Resposta do jogador enviada com sucesso!");
	}

	public void registrarComentario(ActionEvent event) {
		int sessaoId = ((SessaoBean) getSessionAttribute("sessaoBean")).getSessaoAtiva().getId();
		String comentario = getFase1().getComentario(); 
		
		SessionGamePhaseDAO gpsDAO = new SessionGamePhaseDAO();
		gpsDAO.atualizaComentarioSessaofase(sessaoId,1,comentario);
		
		this.setMensagem("Comentário enviado com sucesso!");
	}

	public String goTeste() {
		return "teste";
	}
	
	public String aderirSessaoJogo() {
		int sessaoId = Integer.valueOf(getParameter("paramsessaoid"));
		int paramjogoid = Integer.valueOf(getParameter("paramjogoid"));
		int aplicadorId = ((UsuarioLogin) getSessionAttribute("usuarioLogin")).getAtendente().getId();

		SessionDAO dao = new SessionDAO();

		SessaoBean sessaoBean = (SessaoBean) this.getSessionAttribute("sessaoBean");
		sessaoBean.setSessaoAtiva(dao.updateSessionAttendant(sessaoId, aplicadorId));
		
		QuestionnaireDAO daoQ = new QuestionnaireDAO();
		
		//fase 1
		List<Question> questoes = daoQ.findQuestionarieByGameFaseId(paramjogoid, 1);
		
		this.getFase1().setQuestoes(questoes);

		return "acompanhamento";
	}
}
