package com.gft.api.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlterarUsuarioDTO {
	
	private String email;	
	private String senha;	
}
