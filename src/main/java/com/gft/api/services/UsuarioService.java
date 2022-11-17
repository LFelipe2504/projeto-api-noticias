package com.gft.api.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gft.api.entities.Usuario;
import com.gft.api.exception.UsuarioExisteException;
import com.gft.api.repositories.UsuarioRepository;

@Service
public class UsuarioService{
	
	private final UsuarioRepository usuarioRepository;
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	
	public Usuario buscarUsuarioPorEmail(String email) {
		
		return usuarioRepository.findByEmail(email).get();
	}

	public Usuario buscarUsuarioPorId(Long idUsuario) {
		return usuarioRepository.findById(idUsuario)
				.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));		
	}

	public Page<Usuario> listarUsuarios(Pageable pageable) {		
		return usuarioRepository.findAll(pageable); 
	}
	
	public Usuario salvarUsuario(Usuario usuario) {
		
		Optional<Usuario> optional =usuarioRepository.findByEmail(usuario.getEmail());
		
		if(optional.isEmpty())
			return usuarioRepository.save(usuario);
					
		throw new UsuarioExisteException("Já existe usuário com esse e-mail");
	}
	
	public Usuario atualizarUsuario(Usuario usuario, Long id) {
		Usuario usuarioOriginal = this.buscarUsuarioPorId(id);
		
		usuario.setId(usuarioOriginal.getId());
		
		return this.salvarUsuario(usuario);		
	}


	public void deletarUsuario(Long id) {
		Usuario usuario = this.buscarUsuarioPorId(id);	
		usuarioRepository.delete(usuario);
	}
	
	
}
