package com.gft.api.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.api.dto.etiqueta.ConsultaEtiquetaDTO;
import com.gft.api.dto.etiqueta.EtiquetaMapper;
import com.gft.api.dto.etiqueta.RegistroEtiquetaDTO;
import com.gft.api.entities.Etiqueta;
import com.gft.api.services.EtiquetaService;

@RestController
@RequestMapping("/etiquetas")
public class EtiquetaController {

	private final EtiquetaService etiquetaService;

	public EtiquetaController(EtiquetaService etiquetaService) {
		this.etiquetaService = etiquetaService;
	}

	@GetMapping
	public ResponseEntity<Page<ConsultaEtiquetaDTO>> buscarTodosAsEtiquetas(
			@PageableDefault(size = 10) Pageable pageable) {

		Page<ConsultaEtiquetaDTO> etiquetasDTO = etiquetaService.listarEtiquetas(pageable)
				.map(EtiquetaMapper::fromEtiqueta);

		return ResponseEntity.ok(etiquetasDTO);
	}

	@GetMapping("{id}")
	public ResponseEntity<ConsultaEtiquetaDTO> buscarEtiqueta(@PathVariable Long id) {

		ConsultaEtiquetaDTO consultaEtiquetaDTO = EtiquetaMapper.fromEtiqueta(etiquetaService.buscarEtiquetaPorId(id));

		return ResponseEntity.ok(consultaEtiquetaDTO);

	}

	@PostMapping
	public ResponseEntity<ConsultaEtiquetaDTO> salvarEtiqueta(@RequestBody RegistroEtiquetaDTO dto) {

		Etiqueta etiqueta = EtiquetaMapper.fromEtiquetaDTO(dto);

		etiquetaService.salvarEtiqueta(etiqueta);

		return ResponseEntity.ok(EtiquetaMapper.fromEtiqueta(etiqueta));
	}

	@PutMapping("{id}")
	public ResponseEntity<ConsultaEtiquetaDTO> alterarEtiqueta(@PathVariable Long id,
			@RequestBody RegistroEtiquetaDTO dto) {

		Etiqueta etiqueta = EtiquetaMapper.fromEtiquetaDTO(dto);

		etiquetaService.atualizarEtiqueta(etiqueta, id);

		return ResponseEntity.ok(EtiquetaMapper.fromEtiqueta(etiqueta));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deletarEtiqueta(@PathVariable Long id) {

		etiquetaService.deletarEtiqueta(id);

		return ResponseEntity.ok().build();
	}

}
