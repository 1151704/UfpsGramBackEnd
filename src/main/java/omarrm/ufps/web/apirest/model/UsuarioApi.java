package omarrm.ufps.web.apirest.model;

import java.io.Serializable;

import omarrm.ufps.web.apirest.entity.Usuario;

public class UsuarioApi implements Serializable {
	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	private Boolean siguiendo;

	private Boolean seguidor;

	private Boolean pendiente;

	private Boolean aceptar;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Boolean getSiguiendo() {
		return siguiendo;
	}

	public void setSiguiendo(Boolean siguiendo) {
		this.siguiendo = siguiendo;
	}

	public Boolean getSeguidor() {
		return seguidor;
	}

	public void setSeguidor(Boolean seguidor) {
		this.seguidor = seguidor;
	}

	public Boolean getPendiente() {
		return pendiente;
	}

	public void setPendiente(Boolean pendiente) {
		this.pendiente = pendiente;
	}

	public Boolean getAceptar() {
		return aceptar;
	}

	public void setAceptar(Boolean aceptar) {
		this.aceptar = aceptar;
	}

}
