package com.wyden.AgendaCompromisso.Entities;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entidade que representa um compromisso (evento agendado) de um usuário no sistema.
 * Mapeada para a tabela "tb_compromisso" no banco de dados.
 */
@Entity
@Table(name="tb_compromisso")
public class Compromisso {
	/** Identificador único do compromisso. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataHora;
    
    /**
     * Usuário associado a este compromisso (relação Muitos para Um).
     * Mapeado para a coluna 'usuario_id' no banco de dados.
     */
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    /**
     * Construtor padrão (necessário pelo JPA).
     */
    public Compromisso() {}
    /**
     * Construtor completo para inicialização de Compromisso.
     *
     * @param id Identificador único do compromisso.
     * @param titulo O título do compromisso.
     * @param descricao A descrição detalhada do compromisso.
     * @param dataHora A data e hora do compromisso.
     * @param usuario O usuário associado a este compromisso.
     */
    public Compromisso(long id, String titulo, String descricao, LocalDateTime dataHora, Usuario usuario) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.usuario = usuario;
    }

   
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}