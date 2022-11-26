package com.gft.api.controllers;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.api.dto.usuario.AlterarUsuarioDTO;
import com.gft.api.dto.usuario.ConsultaUsuarioDTO;
import com.gft.api.dto.usuario.RegistroUsuarioDTO;
import com.gft.api.dto.usuario.UsuarioMapper;
import com.gft.api.entities.Perfil;
import com.gft.api.entities.Usuario;
import com.gft.api.exception.SenhaException;
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
	@PreAuthorize("hasAuthority('ADM')")
	public ResponseEntity<Page<ConsultaUsuarioDTO>> buscarTodosOsUsuarios(
			@PageableDefault(size = 10) Pageable pageable) {
		
		Page<ConsultaUsuarioDTO> usuarioDTO = usuarioService.listarUsuarios(pageable)
				.map(UsuarioMapper::fromUsuario);

		return ResponseEntity.ok(usuarioDTO);
	}
	
	@GetMapping("/usuariologado")
	public ResponseEntity<ConsultaUsuarioDTO> buscarUsuario(@AuthenticationPrincipal Usuario usuarioLogado) {
		
		ConsultaUsuarioDTO consultaUsuarioDTO = UsuarioMapper
				.fromUsuario(usuarioLogado);

		return ResponseEntity.ok(consultaUsuarioDTO);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ADM')")
	public ResponseEntity<ConsultaUsuarioDTO> salvarUsuario(@Valid @RequestBody RegistroUsuarioDTO dto) {
		
		if(dto.getSenha().length() < 6 | dto.getSenha().length() > 12 )
			throw new SenhaException("A senha deve ter de 6 a 12 caracteres");

		Perfil perfil = perfilService.buscarPerfilPorId(dto.getPerfilId());

		Usuario usuario = UsuarioMapper.fromUsuarioDTO(dto);
		usuario.setPerfil(perfil);
		usuarioService.salvarUsuario(usuario);

		return ResponseEntity.ok(UsuarioMapper.fromUsuario(usuario));
	}

	@PutMapping
	public ResponseEntity<ConsultaUsuarioDTO> alterarUsuario(@AuthenticationPrincipal Usuario usuarioLogado,
			@Valid @RequestBody AlterarUsuarioDTO dto) {
		
		if(dto.getSenha().length() < 6 | dto.getSenha().length() > 12 )
			throw new SenhaException("A senha deve ter de 6 a 12 caracteres");

		Usuario usuarioNovo = UsuarioMapper.fromAlterarUsuarioDTO(dto);
		
		usuarioService.atualizarUsuario(usuarioNovo, usuarioLogado.getId());

		return ResponseEntity.ok(UsuarioMapper.fromUsuario(usuarioNovo));
	}

	@DeleteMapping
	public ResponseEntity<?> deletarUsuario(@AuthenticationPrincipal Usuario usuarioLogado) {

		usuarioService.deletarUsuario(usuarioLogado.getId());

		return ResponseEntity.ok().build();
	}

}
