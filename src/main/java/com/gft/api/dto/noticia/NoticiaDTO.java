package com.gft.api.dto.noticia;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticiaDTO {
	
	private String title ;
	private String link ;
	private String date ;

}
