package com.wyden.AgendaCompromisso.dto;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * DTO (Data Transfer Object) utilizado para o transporte de dados
 * ao criar um novo Compromisso. Contém apenas os campos necessários
 * para o agendamento (título, descrição, data/hora).
 */
public class CompromisooCriarDto {
	/**TITULO DO COMPROMISSO*/
	private String titulo;
	
	/**DESCRIÇÃO DO COMPROMISSO*/
	private String descricao;
	
	/**DATA/HORA DO COMPROMISSO*/
	private LocalDateTime dataHora;
	
	/**
	 * Construtor padrão (vazio).
	 */
	public CompromisooCriarDto() {
		super();
	}
	/**
	 * Construtor completo.
	 * * @param titulo O título do compromisso.
     * @param descricao A descrição detalhada do compromisso.
     * @param dataHora A data e hora do compromisso.
	 */
	public CompromisooCriarDto(String titulo, String descricao, LocalDateTime dataHora) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataHora = dataHora;
	}
	
	/**RETORNA O TITULO*/
	public String getTitulo() {
		return titulo;
	}
	
	/**DEFINE O TITULO*/
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	/**DEFINE A DESCRIÇÃO*/
	public String getDescricao() {
		return descricao;
	}
	
	/**RETORNA A DESCRIÇÃO*/
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	/**DEFINE A HORA E A DATA*/
	public LocalDateTime getDataHora() {
		return dataHora;
	}
	
	/**RETORNA A HORA E A DATA*/
	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
	@Override
	public int hashCode() {
		return Objects.hash(dataHora, descricao, titulo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompromisooCriarDto other = (CompromisooCriarDto) obj;
		return Objects.equals(dataHora, other.dataHora) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(titulo, other.titulo);
	}
	@Override
	public String toString() {
		return "CompromisooCriarDto [titulo=" + titulo + ", descricao=" + descricao + ", dataHora=" + dataHora + "]";
	}
	
	
}
