package omarrm.ufps.web.apirest.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "usuario", unique = true, nullable = false)
	private String username;

	@Column(name = "email", unique = true, nullable = false)
	private String email;

	@Column(name = "nombre", length = 30, nullable = false)
	private String nombre;

	@Column(name = "password", nullable = false)
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_registro", nullable = false, updatable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date fechaRegistro;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_nacimiento", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date fechaNacimiento;

	@OneToMany(mappedBy = "usuario")
	private List<Publicacion> publicaciones;

	@OneToMany(mappedBy = "usuario")
	private List<Etiqueta> etiquetas;

	@OneToMany(mappedBy = "usuario")
	private List<Comentario> comentarios;

	@OneToMany(mappedBy = "envia")
	private List<Mensaje> mensajesEnviados;

	@OneToMany(mappedBy = "recibe")
	private List<Mensaje> mensajesRecibidos;

	@OneToMany(mappedBy = "usuario")
	private List<Amistad> amistadesEnviadas;

	@OneToMany(mappedBy = "amistad")
	private List<Amistad> amistadesRecibidas;

//	public Usuario() {
//
//		this.etiquetas = new ArrayList<Etiqueta>();
//		this.publicaciones = new ArrayList<Publicacion>();
//		this.comentarios = new ArrayList<Comentario>();
//		this.mensajesEnviados = new ArrayList<Mensaje>();
//		this.mensajesRecibidos = new ArrayList<Mensaje>();
//		this.amistadesEnviadas = new ArrayList<Amistad>();
//		this.amistadesRecibidas = new ArrayList<Amistad>();
//
//	}

	@PrePersist
	protected void prePersist() {
		fechaRegistro = new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", email=" + email + ", nombre=" + nombre
				+ ", password=" + password + ", fechaRegistro=" + fechaRegistro + ", fechaNacimiento=" + fechaNacimiento
				+ "]";
	}

	private static final long serialVersionUID = 1L;

}
