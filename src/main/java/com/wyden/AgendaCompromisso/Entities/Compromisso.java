package com.wyden.AgendaCompromisso.Entities;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="tb_compromisso")
public class Compromisso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataHora;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Compromisso() {}

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