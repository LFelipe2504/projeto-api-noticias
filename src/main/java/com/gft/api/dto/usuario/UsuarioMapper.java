package com.gft.api.dto.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gft.api.entities.Usuario;

public class UsuarioMapper {

	public static Usuario fromUsuarioDTO( RegistroUsuarioDTO dto) {
		
		return new Usuario(null, dto.getNome(),new BCryptPasswordEncoder().encode(dto.getSenha())
				, null, null, null);
	}	
	
	public static ConsultaUsuarioDTO fromUsuario(Usuario usuario) {
		
		return new ConsultaUsuarioDTO(usuario.getId(), usuario.getNome(),
				usuario.getPerfil().getNome(), usuario.getEtiquetas()); 
	}
}
