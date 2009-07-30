package br.ufrj.nce.labase.phidias.aplicador;

import br.ufrj.nce.labase.phidias.common.ManagedBean;
import br.ufrj.nce.labase.phidias.persistence.model.Session;
import br.ufrj.nce.labase.phidias.sessao.SessaoBean;

public class AplicadorBean extends ManagedBean {

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
		// TODO Auto-generated constructor stub
	}

	public String aderirSessaoJogo() {
		SessaoBean sessao = (SessaoBean) this.getSessionAttribute("sessaoBean");
		sessao.getSessoes();
		
		System.out.println(getParameter("paramsessaoid"));
		return "acompanhamento";
	}
}
