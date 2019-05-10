package omarrm.ufps.web.apirest.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "comentario")
public class Comentario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String comentario;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_publicacion", nullable = false, updatable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone="America/Bogota")
	private Date fechaPublicacion;

	@Enumerated(EnumType.STRING)
	@Column(name = "estado", nullable = false)
	private ComentarioEstado estado;

	@ManyToOne
	private Usuario usuario;

	@ManyToOne(cascade = CascadeType.ALL)
	@JsonBackReference
	private Publicacion publicacion;

	@PrePersist
	protected void prePersist() {
		this.fechaPublicacion = new Date();
		this.estado = ComentarioEstado.VISIBLE;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public ComentarioEstado getEstado() {
		return estado;
	}

	public void setEstado(ComentarioEstado estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Publicacion getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}

	private static final long serialVersionUID = 1L;

}
