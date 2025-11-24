package com.wyden.AgendaCompromisso.Entities;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
/**Entidade que representa um usuário no sistema.*/
@Entity
@Table(name="tb_usuario")
public class Usuario {
	/**
     * Construtor completo.
     * 
     * nome         nome do usuário
     * email        email do usuário
     * senha        senha do usuário
     *id           ID do usuário
     * compromissos lista de compromissos associados
     */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	@JsonIgnore
	private String senha;
	
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Compromisso> compromissos;
	public Usuario() {
		super();
	}

	public Usuario(String nome, String email, String senha, Long id, List<Compromisso> compromissos) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.id = id;
		this.compromissos = compromissos;
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
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public List<Compromisso> getCompromissos() {
		return compromissos;
	}
	public void setCompromissos(List<Compromisso> compromissos) {
		this.compromissos = compromissos;
	}
	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", email=" + email + ", senha=" + senha + ", id=" + id + ", compromissos="
				+ compromissos + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(compromissos, email, id, nome, senha);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(compromissos, other.compromissos) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(senha, other.senha);
	}
	
}	
		
