package com.gft.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.api.dto.noticia.NoticiaDTO;
import com.gft.api.entities.Usuario;
import com.gft.api.services.NoticiaService;

@RestController
@RequestMapping("/noticias")
public class NoticiaController {
	
	private final NoticiaService noticiaService;

	public NoticiaController(NoticiaService noticiaService) {
		this.noticiaService = noticiaService;
	}
	
	@GetMapping
	public ResponseEntity<List<NoticiaDTO>> obterTodasAsNoticiasDasEtiquetasDoUsuario
	(@AuthenticationPrincipal Usuario usuario) {
		
		List<NoticiaDTO> listaNoticias = noticiaService.obterNoticias(usuario);

		return ResponseEntity.ok(listaNoticias);
	}

}
