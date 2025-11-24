package com.wyden.AgendaCompromisso.dto;

import java.util.List;
import java.util.Objects;

import com.wyden.AgendaCompromisso.Entities.Compromisso;

/**Contém dados essenciais de um usuário, como ID, nome, email e a lista de compromissos associados.
 * Este DTO é geralmente usado em respostas da API para não expor informações sensíveis como senha.
 * */

public class UsuarioDto {
	/** ID do usuário */
	private Long Id;
	
	/** NOME do usuário */
	private String nome;
	
	/** email do usuário */
	private String email;
	
	/** lista de compromissos do usuário */
	private List<Compromisso> compromissos;
	
	/** construtor vazio */
	public UsuarioDto() {
		super();
	}
	
	/** construtor com os parametro
	 * nome
	 *  email
	 * lista de compromissos */
	public UsuarioDto(Long id, String nome, String email, List<Compromisso> compromissos) {
		super();
		Id = id;
		this.nome = nome;
		this.email = email;
		this.compromissos = compromissos;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Compromisso> getCompromissos() {
		return compromissos;
	}
	public void setCompromissos(List<Compromisso> compromissos) {
		this.compromissos = compromissos;
	}
	@Override
	public int hashCode() {
		return Objects.hash(Id, compromissos, email, nome);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioDto other = (UsuarioDto) obj;
		return Objects.equals(Id, other.Id) && Objects.equals(compromissos, other.compromissos)
				&& Objects.equals(email, other.email) && Objects.equals(nome, other.nome);
	}
	@Override
	public String toString() {
		return "UsuarioDto [Id=" + Id + ", nome=" + nome + ", email=" + email + ", compromissos=" + compromissos + "]";
	}
	
	
}
