package com.gft.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gft.api.entities.HistoricoUsuarioEtiqueta;
import com.gft.api.entities.Usuario;
import com.gft.api.exception.EntityExistException;
import com.gft.api.exception.EntityNotFoundException;
import com.gft.api.repositories.HistoricoUsuarioEtiquetaRepository;
import com.gft.api.repositories.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{
	
	private final UsuarioRepository usuarioRepository;
	private HistoricoUsuarioEtiquetaRepository historicoRepository; 
	
	public UsuarioService(UsuarioRepository usuarioRepository,
			HistoricoUsuarioEtiquetaRepository historicoRepository) {
		this.usuarioRepository = usuarioRepository;
		this.historicoRepository = historicoRepository;
	}
	
	
	public Usuario buscarUsuarioPorNome(String nome) {
		
		return usuarioRepository.findByNome(nome).get();
	}

	public Usuario buscarUsuarioPorId(Long idUsuario) {
		
		return usuarioRepository.findById(idUsuario)
				.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));		
	}

	public Page<Usuario> listarUsuarios(Pageable pageable) {		
		return usuarioRepository.findAll(pageable); 
	}
	
	public Usuario salvarUsuario(Usuario usuario) {
		
		Optional<Usuario> optional = usuarioRepository.findByNome(usuario.getNome());
		
		if(optional.isEmpty()) 
			return usuarioRepository.save(usuario);		
					
		throw new EntityExistException("Já existe usuário com esse nome");
	}
	
	public Usuario atualizarUsuario(Usuario usuario, Long id) {
		Usuario usuarioOriginal = this.buscarUsuarioPorId(id);
		
		usuario.setId(usuarioOriginal.getId());
		
		return this.salvarUsuario(usuario);		
	}

	public void deletarUsuario(Long id) {
		Usuario usuario = this.buscarUsuarioPorId(id);
		
		List<HistoricoUsuarioEtiqueta> historicoDoUsuario = historicoRepository.findAllByIdUsuario(usuario.getId());
		
		historicoDoUsuario.stream().forEach(h -> historicoRepository.delete(h));
		
		usuarioRepository.delete(usuario);
	}
	
	@Override
	public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
		return buscarUsuarioPorNome(nome);
	}


	public Usuario SalvarUsuarioSemVerificacao(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
}
