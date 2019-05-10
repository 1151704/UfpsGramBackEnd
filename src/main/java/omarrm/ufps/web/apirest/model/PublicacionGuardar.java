package omarrm.ufps.web.apirest.model;

import java.io.Serializable;

public class PublicacionGuardar implements Serializable {
	private static final long serialVersionUID = 1L;

	private String descripcion;

	private String[] etiquetas;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String[] getEtiquetas() {
		return etiquetas;
	}

	public void setEtiquetas(String[] etiquetas) {
		this.etiquetas = etiquetas;
	}

}
