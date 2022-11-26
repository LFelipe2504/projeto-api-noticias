package com.gft.api.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_perfil")
public class Perfil implements GrantedAuthority{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O nome do perfil não pode ser nulo ou em branco") 
	private String nome;
	
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.getNome();
	}

}
