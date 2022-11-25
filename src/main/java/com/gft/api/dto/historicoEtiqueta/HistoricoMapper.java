package com.gft.api.dto.historicoEtiqueta;

import com.gft.api.entities.HistoricoUsuarioEtiqueta;

public class HistoricoMapper {
	
public static ConsultaHistoricoUsuarioEtiquetaDTO fromHistoricoUsuarioEtiqueta(HistoricoUsuarioEtiqueta historico) {
		
		return new ConsultaHistoricoUsuarioEtiquetaDTO(historico.getId(),
				historico.getNomeEtiqueta(),historico.getDataAcesso() ); 
	}

}
