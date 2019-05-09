package omarrm.ufps.web.apirest.entity;

import java.io.Serializable;
import java.util.Date;

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

@Entity
@Table(name = "amistad")
public class Amistad implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_solicitud", nullable = false, updatable = false)
	private Date fechaSolicitud;

	@Enumerated(EnumType.STRING)
	private AmistadEstado estado;

	@ManyToOne
	private Usuario usuario;

	@ManyToOne
	private Usuario amistad;

	@PrePersist
	protected void prePersist() {
		this.fechaSolicitud = new Date();
		this.estado = AmistadEstado.ENVIADA;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public AmistadEstado getEstado() {
		return estado;
	}

	public void setEstado(AmistadEstado estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getAmistad() {
		return amistad;
	}

	public void setAmistad(Usuario amistad) {
		this.amistad = amistad;
	}

	private static final long serialVersionUID = 1L;

}
