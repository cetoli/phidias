package br.ufrj.nce.labase.phidias.aplicador;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import br.ufrj.nce.labase.phidias.common.ManagedBean;
import br.ufrj.nce.labase.phidias.persistence.model.Answer;
import br.ufrj.nce.labase.phidias.persistence.model.Question;
import br.ufrj.nce.labase.phidias.persistence.model.QuestionPK;
import br.ufrj.nce.labase.phidias.persistence.model.Session;
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

	private Session session;

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public AplicadorBean() {
		this.fase1 = new Fase1Bean();
		
	}

	public void registrarEstimulo(ActionEvent event) {
		SessaoBean sessao = (SessaoBean) getSessionAttribute("sessaoBean");
		sessao.getSessoes();
		System.out.println(getParameter("paramsessaoid"));

		System.out.println(this.getFase1().getEstimuloNPC());
		this.setMensagem("Estimulo enviado com sucesso!");
	}

	public String registrarRespostaJogador() {
		SessaoBean sessao = (SessaoBean) getSessionAttribute("sessaoBean");
		sessao.getSessoes();

		System.out.println(this.getFase1().getRespostaJogador());
		return "acompanhamento";
	}

	public String aderirSessaoJogo() {
		SessaoBean sessao = (SessaoBean) getSessionAttribute("sessaoBean");
		sessao.getSessoes();

		System.out.println(getParameter("paramsessaoid"));
		return "acompanhamento";
	}
}
