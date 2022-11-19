package com.gft.api.dto.usuario;

import java.util.List;

import com.gft.api.entities.Etiqueta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaUsuarioDTO {
	
	private Long id;
	private String email;
	private String nomePerfil;
	private List<Etiqueta> etiquetas;
	
}
