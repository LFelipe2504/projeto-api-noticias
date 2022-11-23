package com.gft.api.dto.historicoEtiqueta;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaHistoricoEtiquetaDTO {
	
	private Long id;	
	private String nomeEtiqueta;
	private LocalDate dataAcesso;

}
