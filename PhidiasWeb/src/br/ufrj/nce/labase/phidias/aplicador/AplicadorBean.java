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
import br.ufrj.nce.labase.phidias.persistence.model.Session;
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

		SessionGamePhaseStimulusType stimulus = new SessionGamePhaseStimulusType();
		stimulus.setSessionId(sessaoId);
		// Fase1
		stimulus.setPhaseId(1);
		// Tipo de estimulo NPC
		stimulus.setStimulusTypeId(1);
		stimulus.setStimulusText(estimulo);

		SessionGamePhaseStimulusTypeDAO sgpstDAO = new SessionGamePhaseStimulusTypeDAO();
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
		gpsDAO.atualizaComentarioSessaofase(sessaoId, 1, comentario);

		this.setMensagem("Comentário enviado com sucesso!");
	}

	public String goTeste() {
		return "teste";
	}

	public String aderirSessaoJogo() {
		int sessaoId = Integer.valueOf(getParameter("paramsessaoid"));
		int paramjogoid = Integer.valueOf(getParameter("paramjogoid"));
		
		// Iniciar na fase 1
		return aderirSessaoJogo(1, sessaoId, paramjogoid);
	}
	
	public String aderirSessaoJogo(int phaseId, int sessionId, int gameId) {
		int aplicadorId = ((UsuarioLogin) getSessionAttribute("usuarioLogin")).getAtendente().getId();

		SessionDAO dao = new SessionDAO();

		SessaoBean sessaoBean = (SessaoBean) getSessionAttribute("sessaoBean");
		sessaoBean.setSessaoAtiva(dao.updateSessionAttendant(sessionId, aplicadorId));

		QuestionnaireDAO daoQ = new QuestionnaireDAO();

		List<Question> questoes = daoQ.findQuestionarieByGameFaseId(gameId, phaseId);

		this.getFase1().setQuestoes(questoes);

		return "acompanhamento";
	}
	
	public void mudarFase1Jogo(ActionEvent event) {
		mudarFaseJogo(1);
		
		Session session = ((SessaoBean) getSessionAttribute("sessaoBean")).getSessaoAtiva();
		int sessaoId = session.getId();
		int gameId = session.getGame().getId();
		aderirSessaoJogo(1, sessaoId, gameId);
	}
	
	public void mudarFase2Jogo(ActionEvent event) {
		mudarFaseJogo(2);
		
		Session session = ((SessaoBean) getSessionAttribute("sessaoBean")).getSessaoAtiva();
		int sessaoId = session.getId();
		int gameId = session.getGame().getId();
		aderirSessaoJogo(2, sessaoId, gameId);
	}
	
	public void mudarFase3Jogo(ActionEvent event) {
		mudarFaseJogo(3);
		
		Session session = ((SessaoBean) getSessionAttribute("sessaoBean")).getSessaoAtiva();
		int sessaoId = session.getId();
		int gameId = session.getGame().getId();
		aderirSessaoJogo(3, sessaoId, gameId);
	}
	
	public void mudarFase4Jogo(ActionEvent event) {
		mudarFaseJogo(4);
		
		Session session = ((SessaoBean) getSessionAttribute("sessaoBean")).getSessaoAtiva();
		int sessaoId = session.getId();
		int gameId = session.getGame().getId();
		aderirSessaoJogo(4, sessaoId, gameId);
	}
	
	public void mudarFase5Jogo(ActionEvent event) {
		mudarFaseJogo(5);
		
		Session session = ((SessaoBean) getSessionAttribute("sessaoBean")).getSessaoAtiva();
		int sessaoId = session.getId();
		int gameId = session.getGame().getId();
		aderirSessaoJogo(5, sessaoId, gameId);
	}

	public void mudarFaseJogo(int phaseId) {
		int sessaoId = ((SessaoBean) getSessionAttribute("sessaoBean")).getSessaoAtiva().getId();
		SessionDAO dao = new SessionDAO();
		dao.updateSessionPhase(sessaoId, phaseId);
	}

	public void salvarQuestionarioFase1(ActionEvent event){
		Object o = this.getFase1().getRespostasQuestionario();
		System.out.println("");
	}
}
