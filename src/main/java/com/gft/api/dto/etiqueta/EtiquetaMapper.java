package com.gft.api.dto.etiqueta;

import com.gft.api.entities.Etiqueta;

public class EtiquetaMapper {

	public static Etiqueta fromEtiquetaDTO( RegistroEtiquetaDTO dto) {
		
		return new Etiqueta(null, dto.getNome(), null);
	}	
	
	public static ConsultaEtiquetaDTO fromEtiqueta(Etiqueta etiqueta) {
		
		return new ConsultaEtiquetaDTO(etiqueta.getId(), etiqueta.getNome()); 
	}
}
