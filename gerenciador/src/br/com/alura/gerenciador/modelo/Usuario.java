package br.com.alura.gerenciador.modelo;

public class Usuario {

	public String login;
	public String senha;
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public boolean ehIgual(String login, String senha) {
		if(!this.login.equals(login)) {
			return false;
		}
		if(!this.senha.equals(senha)) {
			return false;
		}
		return true; 
	}
	
}
