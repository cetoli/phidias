package br.ufrj.nce.labase.phidias.login;

import br.ufrj.nce.labase.phidias.persistence.dao.AttendantDAO;
import br.ufrj.nce.labase.phidias.persistence.model.Attendant;

public class UsuarioLogin {
	
	private Attendant atendente;
	
	private String usuario;

	private String senha;
	
	public Attendant getAtendente() {
		return atendente;
	}

	public void setAtendente(Attendant atendente) {
		this.atendente = atendente;
	}

	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public UsuarioLogin() {
		// TODO Auto-generated constructor stub
	}
	
	public String autenticarUsuario(){
		try {
			AttendantDAO dao = new AttendantDAO();
			this.atendente = dao.findByName(this.getUsuario());
		} catch (Exception e) {
			return null;
		}
		
		return "administracao";
	}
}
