package br.com.creathus.contato.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.creathus.contato.domain.Contato;
import br.com.creathus.contato.domain.enums.Sexo;

public class ContatoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer Id;
	
	@NotEmpty(message = "Nome é obrigatório!")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	
	private Integer sexo;
	
	private String telefone;
	
	@NotEmpty(message = "E-mail é obrigatório")
	private String email;
	
	public ContatoDTO() {
		
	}

	public ContatoDTO(Contato obj) {
		super();
		Id = obj.getId();
		this.nome = obj.getNome();
		this.sexo = (sexo == null) ? Sexo.MASCULINO.getCod() : obj.getSexo().getCod();
		this.telefone = obj.getTelefone();
		this.email = obj.getEmail();
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Sexo getSexo() {
		return Sexo.toEnum(sexo);
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo.getCod();
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
