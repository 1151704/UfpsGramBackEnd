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
@Table(name = "mensaje")
public class Mensaje implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "mensaje", nullable = false)
	private String mensaje;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_mensaje", nullable = false, updatable = false)
	private Date fechaMensaje;

	@ManyToOne
	private Usuario envia;

	@ManyToOne
	private Usuario recibe;

	@PrePersist
	protected void prePersist() {
		this.fechaMensaje = new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Date getFechaMensaje() {
		return fechaMensaje;
	}

	public void setFechaMensaje(Date fechaMensaje) {
		this.fechaMensaje = fechaMensaje;
	}

	public Usuario getEnvia() {
		return envia;
	}

	public void setEnvia(Usuario envia) {
		this.envia = envia;
	}

	public Usuario getRecibe() {
		return recibe;
	}

	public void setRecibe(Usuario recibe) {
		this.recibe = recibe;
	}

	private static final long serialVersionUID = 1L;

}
