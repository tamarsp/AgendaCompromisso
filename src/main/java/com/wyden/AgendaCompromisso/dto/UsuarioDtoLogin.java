package com.wyden.AgendaCompromisso.dto;

import java.util.Objects;

/**DTO (Data Transfer Object) utilizado para realizar login de usuários
 * Contém apenas o email e a senha do usuário, usados para autenticação.*/
public class UsuarioDtoLogin {
	
	 /** Email do usuário */
	private String email;
	
	 /** senha do usuário */
    private String senha;
	public UsuarioDtoLogin() {
		super();
	}
	public UsuarioDtoLogin(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	@Override
	public int hashCode() {
		return Objects.hash(email, senha);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioDtoLogin other = (UsuarioDtoLogin) obj;
		return Objects.equals(email, other.email) && Objects.equals(senha, other.senha);
	}
	@Override
	public String toString() {
		return "UsuarioDtoLogin [email=" + email + ", senha=" + senha + "]";
	}
    
}
