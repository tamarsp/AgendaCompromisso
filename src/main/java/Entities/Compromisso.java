package Entities;

import java.time.LocalDateTime;
import java.util.Objects;

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
	private Long id;
	private String titulo;
	private String descricao;
	private LocalDateTime dataHora;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	public Compromisso() {
		super();
	}

	public Compromisso(Long id, String titulo, String descricao, LocalDateTime dataHora, Usuario usuario) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataHora = dataHora;
		this.usuario = usuario;
	}
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
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

	@Override
	public int hashCode() {
		return Objects.hash(dataHora, descricao, id, titulo, usuario);
	}
	@Override
	public String toString() {
		return "Compromisso [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", dataHora=" + dataHora
				+ ", usuario=" + usuario + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compromisso other = (Compromisso) obj;
		return Objects.equals(dataHora, other.dataHora) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(id, other.id) && Objects.equals(titulo, other.titulo)
				&& Objects.equals(usuario, other.usuario);
	}	
}
