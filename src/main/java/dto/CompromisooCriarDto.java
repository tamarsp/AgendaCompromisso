package dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class CompromisooCriarDto {
	private String titulo;
	private String descricao;
	private LocalDateTime dataHora;
	public CompromisooCriarDto() {
		super();
	}
	public CompromisooCriarDto(String titulo, String descricao, LocalDateTime dataHora) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataHora = dataHora;
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
