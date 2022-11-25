package com.gft.api.dto.historicoEtiqueta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaHistoricoUsuarioEtiquetaDTO {
	
	private Long id;
	private String nomeEtiqueta;
	private String dataAcesso;

}
