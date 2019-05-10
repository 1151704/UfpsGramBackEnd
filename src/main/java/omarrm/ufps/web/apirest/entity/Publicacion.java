package omarrm.ufps.web.apirest.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "publicacion")
public class Publicacion implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "descripcion")
	private String descripcion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_publicacion", nullable = false, updatable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date fechaPublicacion;

	@ManyToOne
	private Usuario usuario;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "publicacion", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<Etiqueta> etiquetas;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "publicacion", cascade = CascadeType.ALL)
	@JsonManagedReference
	@OrderBy("fechaPublicacion DESC")
	private Set<Comentario> comentarios;

	public Publicacion() {
	}

	@PrePersist
	protected void prePersist() {
		this.fechaPublicacion = new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<Etiqueta> getEtiquetas() {
		return etiquetas;
	}

	public void setEtiquetas(Set<Etiqueta> etiquetas) {
		this.etiquetas = etiquetas;
	}

	public Set<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Set<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	private static final long serialVersionUID = 1L;

}
