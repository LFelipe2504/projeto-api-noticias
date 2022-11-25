package com.gft.api.dto.etiqueta;

import com.gft.api.entities.Etiqueta;

public class EtiquetaMapper {

	public static Etiqueta fromEtiquetaDTO( RegistroEtiquetaDTO dto) {		
		return new Etiqueta(null, dto.getNome(), null, 0);
	}	
	
	public static ConsultaEtiquetaDTO fromEtiqueta(Etiqueta etiqueta) {		
		return new ConsultaEtiquetaDTO(etiqueta.getId(), etiqueta.getNome()); 
	}
	
	public static ConsultaEtiquetaComContagemDTO fromEtiquetaToContagemDTO(Etiqueta etiqueta) {		
		return new ConsultaEtiquetaComContagemDTO(etiqueta.getId(), etiqueta.getNome(),
				etiqueta.getNumeroAcessos()); 
	}
}
