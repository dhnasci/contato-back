package br.com.creathus.contato.dto;

import java.io.Serializable;

import br.com.creathus.contato.domain.enums.Perfil;

public class UsuarioDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String email;
	private String password;
	@SuppressWarnings("unused")
	private Integer perfil;
	
	public UsuarioDTO() {
		
	}

	public UsuarioDTO(Integer id, String name, String email, String password, Perfil perfil) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.perfil = (perfil==null)? Perfil.COLB.getId(): perfil.getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Perfil getPerfil() {
		return Perfil.toEnum(id);
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil.getId();
	}

	public void setPerfil(Integer perfil) {
		this.perfil = perfil;
	}
	
	
}
