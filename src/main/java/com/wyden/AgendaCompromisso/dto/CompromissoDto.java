package com.wyden.AgendaCompromisso.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class CompromissoDto {
	private String titulo;
	private String descricao;
	private LocalDateTime dataHora;
	private Long UsuarioId;
	public CompromissoDto() {
		super();
	}
	public CompromissoDto(String titulo, String descricao, LocalDateTime dataHora, Long usuarioId) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataHora = dataHora;
		UsuarioId = usuarioId;
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
	public Long getUsuarioId() {
		return UsuarioId;
	}
	public void setUsuarioId(Long usuarioId) {
		UsuarioId = usuarioId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(UsuarioId, dataHora, descricao, titulo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompromissoDto other = (CompromissoDto) obj;
		return Objects.equals(UsuarioId, other.UsuarioId) && Objects.equals(dataHora, other.dataHora)
				&& Objects.equals(descricao, other.descricao) && Objects.equals(titulo, other.titulo);
	}
	@Override
	public String toString() {
		return "CompromissoDto [titulo=" + titulo + ", descricao=" + descricao + ", dataHora=" + dataHora
				+ ", UsuarioId=" + UsuarioId + "]";
	}
	
}
