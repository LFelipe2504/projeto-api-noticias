package com.gft.api.controllers;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.api.dto.etiqueta.ConsultaEtiquetaComContagemDTO;
import com.gft.api.dto.etiqueta.ConsultaEtiquetaDTO;
import com.gft.api.dto.etiqueta.EtiquetaMapper;
import com.gft.api.dto.etiqueta.RegistroEtiquetaDTO;
import com.gft.api.dto.usuario.ConsultaUsuarioDTO;
import com.gft.api.dto.usuario.UsuarioMapper;
import com.gft.api.entities.Etiqueta;
import com.gft.api.entities.Usuario;
import com.gft.api.services.EtiquetaService;

@RestController
@RequestMapping("/etiquetas")
public class EtiquetaController {

	private final EtiquetaService etiquetaService;

	public EtiquetaController(EtiquetaService etiquetaService) {
		this.etiquetaService = etiquetaService;
	}	
	
	@GetMapping("/maisacessadas")
	@PreAuthorize("hasAuthority('ADM')")
	public ResponseEntity<Page<ConsultaEtiquetaComContagemDTO>> buscarEtiquetasMaisAcessadas(
			@PageableDefault(size = 10, sort = "numeroAcessos", direction = Direction.DESC) Pageable pageable) {

		Page<ConsultaEtiquetaComContagemDTO> etiquetasDTO = etiquetaService.listarEtiquetas(pageable)
				.map(EtiquetaMapper::fromEtiquetaToContagemDTO);

		return ResponseEntity.ok(etiquetasDTO);
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ADM')")
	public ResponseEntity<Page<ConsultaEtiquetaDTO>> buscarTodosAsEtiquetas(
			@PageableDefault(size = 10) Pageable pageable) {

		Page<ConsultaEtiquetaDTO> etiquetasDTO = etiquetaService.listarEtiquetas(pageable)
				.map(EtiquetaMapper::fromEtiqueta);

		return ResponseEntity.ok(etiquetasDTO);
	}

	@GetMapping("{id}")
	@PreAuthorize("hasAuthority('ADM')")
	public ResponseEntity<ConsultaEtiquetaDTO> buscarEtiqueta(@PathVariable Long id) {

		ConsultaEtiquetaDTO consultaEtiquetaDTO = EtiquetaMapper.fromEtiqueta(etiquetaService.buscarEtiquetaPorId(id));

		return ResponseEntity.ok(consultaEtiquetaDTO);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('USUARIO')")
	public ResponseEntity<ConsultaUsuarioDTO> salvarEtiquetaParaUsuarioLogado(@Valid @RequestBody RegistroEtiquetaDTO dto,
			@AuthenticationPrincipal Usuario usuario) {

		Etiqueta etiqueta = EtiquetaMapper.fromEtiquetaDTO(dto);

		etiquetaService.salvarEtiqueta(etiqueta, usuario);

		return ResponseEntity.ok(UsuarioMapper.fromUsuario(usuario));
	}

	@DeleteMapping("{id}")
	@PreAuthorize("hasAuthority('USUARIO')")
	public ResponseEntity<?> deletarEtiquetaParaUsuarioLogado(@PathVariable Long id,
			@AuthenticationPrincipal Usuario usuario) {

		etiquetaService.deletarEtiqueta(id,usuario);

		return ResponseEntity.ok().build();
	}

}
