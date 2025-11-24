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

/**
 * Entidade que representa um usuário no sistema.
 * Mapeada para a tabela "tb_usuario" no banco de dados.
 */
@Entity
@Table(name="tb_usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	/**
	 * A senha é ignorada na serialização JSON para segurança.
	 */
	@JsonIgnore
	private String senha;
	
	/**
	 * Lista de compromissos associados a este usuário.
	 * O relacionamento é mapeado pelo campo "usuario" na entidade Compromisso.
	 * Operações de cascata (ALL) e remoção órfã estão ativas.
	 */
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Compromisso> compromissos;
	/**
	 * Construtor padrão (necessário pelo JPA).
	 */
	public Usuario() {
		super();
	}
	/**
     * Construtor completo para criação de usuário.
     *
     * @param nome O nome do usuário.
     * @param email O email único do usuário.
     * @param senha A senha do usuário.
     * @param id O ID do usuário (geralmente nulo na criação).
     * @param compromissos A lista de compromissos associados (geralmente vazia na criação).
     */
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
		
