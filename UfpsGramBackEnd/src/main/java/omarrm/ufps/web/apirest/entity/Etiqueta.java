package omarrm.ufps.web.apirest.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "etiqueta")
public class Etiqueta implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Usuario usuario;

	@ManyToOne
	private Publicacion publicacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha", nullable = false, updatable = false)
	private Date fecha;

	@Column(name = "mensaje", nullable = true)
	private String mensaje;

	@PrePersist
	protected void prePersist() {
		this.fecha = new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	private static final long serialVersionUID = 1L;

}
