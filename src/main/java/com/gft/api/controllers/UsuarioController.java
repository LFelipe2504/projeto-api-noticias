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

import com.gft.api.dto.usuario.ConsultaUsuarioDTO;
import com.gft.api.dto.usuario.RegistroUsuarioDTO;
import com.gft.api.dto.usuario.UsuarioMapper;
import com.gft.api.entities.Perfil;
import com.gft.api.entities.Usuario;
import com.gft.api.services.PerfilService;
import com.gft.api.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;

	private final PerfilService perfilService;

	public UsuarioController(UsuarioService usuarioService, PerfilService perfilService) {
		this.usuarioService = usuarioService;
		this.perfilService = perfilService;
	}

	@GetMapping
	public ResponseEntity<Page<ConsultaUsuarioDTO>> buscarTodosOsUsuarios(
			@PageableDefault(size = 10) Pageable pageable) {

		Page<ConsultaUsuarioDTO> usuarioDTO = usuarioService.listarUsuarios(pageable)
				.map(UsuarioMapper::fromUsuario);

		return ResponseEntity.ok(usuarioDTO);
	}

	@GetMapping("{id}")
	public ResponseEntity<ConsultaUsuarioDTO> buscarUsuario(@PathVariable Long id) {

		ConsultaUsuarioDTO consultaUsuarioDTO = UsuarioMapper.fromUsuario(usuarioService.buscarUsuarioPorId(id));

		return ResponseEntity.ok(consultaUsuarioDTO);

	}

	@PostMapping
	public ResponseEntity<ConsultaUsuarioDTO> salvarUsuario(@RequestBody RegistroUsuarioDTO dto) {

		Perfil perfil = perfilService.buscarPerfilPorId(dto.getPerfilId());

		Usuario usuario = UsuarioMapper.fromUsuarioDTO(dto);
		usuario.setPerfil(perfil);
		usuarioService.salvarUsuario(usuario);

		return ResponseEntity.ok(UsuarioMapper.fromUsuario(usuario));
	}

	@PutMapping("{id}")
	public ResponseEntity<ConsultaUsuarioDTO> alterarUsuario(@PathVariable Long id,
			@RequestBody RegistroUsuarioDTO dto) {

		Perfil perfil = perfilService.buscarPerfilPorId(dto.getPerfilId());

		Usuario usuario = UsuarioMapper.fromUsuarioDTO(dto);
		usuario.setPerfil(perfil);

		usuarioService.atualizarUsuario(usuario, id);

		return ResponseEntity.ok(UsuarioMapper.fromUsuario(usuario));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deletarUsuario(@PathVariable Long id) {

		usuarioService.deletarUsuario(id);

		return ResponseEntity.ok().build();
	}

}
