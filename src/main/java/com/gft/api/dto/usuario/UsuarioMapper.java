package com.gft.api.dto.usuario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gft.api.dto.etiqueta.ConsultaEtiquetaDTO;
import com.gft.api.dto.etiqueta.EtiquetaMapper;
import com.gft.api.entities.Usuario;

public class UsuarioMapper {

	public static Usuario fromUsuarioDTO( RegistroUsuarioDTO dto) {
		
		return new Usuario(null, dto.getNome(),new BCryptPasswordEncoder().encode(dto.getSenha())
				, null, null);
	}	
	
	public static ConsultaUsuarioDTO fromUsuario(Usuario usuario) {
		
		if(usuario.getEtiquetas() == null)
			usuario.setEtiquetas(new ArrayList<>());
		
		List<ConsultaEtiquetaDTO> consultaEtiquetasDto = usuario.getEtiquetas().stream()
				.map(etiqueta -> EtiquetaMapper.fromEtiqueta(etiqueta)).toList();
		
		return new ConsultaUsuarioDTO(usuario.getId(), usuario.getNome(),
				usuario.getPerfil().getNome(),consultaEtiquetasDto); 
	}
}
