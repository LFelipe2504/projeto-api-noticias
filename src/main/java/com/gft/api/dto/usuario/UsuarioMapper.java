package com.gft.api.dto.usuario;

import com.gft.api.entities.Usuario;

public class UsuarioMapper {

	public static Usuario fromUsuarioDTO( RegistroUsuarioDTO dto) {
		
		return new Usuario(null, dto.getEmail(),dto.getSenha()
				, null, null, null);
	}	
	
	public static ConsultaUsuarioDTO fromUsuario(Usuario usuario) {
		
		return new ConsultaUsuarioDTO(usuario.getId(), usuario.getEmail(),
				usuario.getPerfil().getNome(), usuario.getEtiquetas()); 
	}
}
