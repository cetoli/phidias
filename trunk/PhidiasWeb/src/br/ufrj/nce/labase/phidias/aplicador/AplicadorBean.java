package br.ufrj.nce.labase.phidias.aplicador;

import javax.faces.event.ActionEvent;

import br.ufrj.nce.labase.phidias.common.ManagedBean;
import br.ufrj.nce.labase.phidias.communication.bean.SessionBean;
import br.ufrj.nce.labase.phidias.login.UsuarioLogin;
import br.ufrj.nce.labase.phidias.persistence.dao.SessionDAO;
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
		System.out.println(this.getFase1().getEstimuloNPC());
		this.setMensagem("Estimulo enviado com sucesso!");
	}

	public void registrarRespostaJogador() {
		System.out.println(this.getFase1().getRespostaJogador());
		this.setMensagem("Resposta do jogador enviada com sucesso!");
	}

	public void registrarComentario(ActionEvent event) {
		System.out.println(getFase1().getComentario());
		this.setMensagem("Comentário enviado com sucesso!");
	}

	public String aderirSessaoJogo() {
		SessaoBean sessao = (SessaoBean) getSessionAttribute("sessaoBean");
		sessao.getSessoes();

		int sessaoId = Integer.valueOf(getParameter("paramsessaoid"));
		int aplicadorId = ((UsuarioLogin) getSessionAttribute("usuarioLogin"))
				.getAtendente().getId();

		SessionDAO dao = new SessionDAO();

		SessaoBean sessaoBean = (SessaoBean) this.getSessionAttribute("sessaoBean");

		sessaoBean.setSessaoAtiva(dao.updateSessionAttendant(
				sessaoId, aplicadorId));

		return "acompanhamento";
	}
}
