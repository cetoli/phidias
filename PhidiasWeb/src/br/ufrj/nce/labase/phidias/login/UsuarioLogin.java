package br.ufrj.nce.labase.phidias.login;

public class UsuarioLogin {

	private String usuario;


	private String senha;
	
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
		return "administracao";
	}
}
