package com.gft.api.entities;

import java.util.List;

import com.gft.api.dto.noticia.NoticiaDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Noticias {
	
	private String count ;	
	private List<NoticiaDTO> list;	

}
