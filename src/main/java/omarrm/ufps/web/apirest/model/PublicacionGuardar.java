package omarrm.ufps.web.apirest.model;

import java.io.Serializable;

public class PublicacionGuardar implements Serializable {
	private static final long serialVersionUID = 1L;

	private String descripcion;

	private String imagen;

	private String[] etiquetas;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String[] getEtiquetas() {
		return etiquetas;
	}

	public void setEtiquetas(String[] etiquetas) {
		this.etiquetas = etiquetas;
	}

}
