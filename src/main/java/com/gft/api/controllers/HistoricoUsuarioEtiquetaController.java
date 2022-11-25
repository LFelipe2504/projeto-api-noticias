package com.gft.api.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.api.dto.historicoEtiqueta.ConsultaHistoricoUsuarioEtiquetaDTO;
import com.gft.api.dto.historicoEtiqueta.HistoricoMapper;
import com.gft.api.entities.Usuario;
import com.gft.api.services.HistoricoUsuarioEtiquetaService;

@RestController
@RequestMapping("/historico")
public class HistoricoUsuarioEtiquetaController {
	
	private final HistoricoUsuarioEtiquetaService historicoService;
	
	
	public HistoricoUsuarioEtiquetaController(HistoricoUsuarioEtiquetaService historicoService) {
		this.historicoService = historicoService;
	}



	@GetMapping
	public ResponseEntity<Page<ConsultaHistoricoUsuarioEtiquetaDTO>> buscarHistoricoAcessoUsuarioLogado(
			@PageableDefault(size = 10) Pageable pageable,
			@AuthenticationPrincipal Usuario usuario) {
		
		if(usuario.getPerfil().getNome().equals("USUARIO")) {
			Page<ConsultaHistoricoUsuarioEtiquetaDTO> historicoDTO = historicoService
					.buscarHistoricoAcessoUsuario(pageable, usuario)
					.map(HistoricoMapper::fromHistoricoUsuarioEtiqueta);
			
			return ResponseEntity.ok(historicoDTO);
			
		} else {			
			Page<ConsultaHistoricoUsuarioEtiquetaDTO> historicoDTO = historicoService
					.buscarHistoricoAcessoDeTodosOsUsuarios(pageable)
					.map(HistoricoMapper::fromHistoricoUsuarioEtiqueta);
			
			return ResponseEntity.ok(historicoDTO);
		}

	}	

}
