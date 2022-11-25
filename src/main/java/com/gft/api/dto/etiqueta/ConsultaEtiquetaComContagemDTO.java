package com.gft.api.dto.etiqueta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaEtiquetaComContagemDTO {
	
	private Long id;
	private String nome;
	private int numeroAcessos;
}
