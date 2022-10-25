package com.co.amr.mcsvusuario.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 0L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nombre;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String password;
	
	private Boolean activo;
	
	@Column(name = "create_at")
	private LocalDateTime createAt;
	
	@PrePersist
	private void initFields() {
		this.activo = Boolean.TRUE;
		this.createAt = LocalDateTime.now();
	}

	public Usuario() {
	}

	public Usuario(Long id, @NotBlank String nombre, @NotBlank @Email String email, @NotBlank String password,
			Boolean activo, LocalDateTime createAt) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.password = password;
		this.activo = activo;
		this.createAt = createAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", email=" + email + ", password=" + password + ", activo="
				+ activo + ", createAt=" + createAt + "]";
	}

}
