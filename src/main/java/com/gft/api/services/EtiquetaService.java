package com.gft.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gft.api.entities.Etiqueta;
import com.gft.api.entities.Usuario;
import com.gft.api.exception.EntityExistException;
import com.gft.api.exception.EntityNotFoundException;
import com.gft.api.repositories.EtiquetaRepository;

@Service
public class EtiquetaService{
	
	private final EtiquetaRepository etiquetaRepository;
	
	private final UsuarioService usuarioService;
	
	
	public EtiquetaService(EtiquetaRepository etiquetaRepository, UsuarioService usuarioService) {
		this.etiquetaRepository = etiquetaRepository;
		this.usuarioService = usuarioService;
	}
	
	
	public Etiqueta buscarEtiquetaPorNome(String nome) {
		
		return etiquetaRepository.findByNome(nome)
				.orElseThrow(() -> new EntityNotFoundException("Etiqueta não encontrada"));
	}

	public Etiqueta buscarEtiquetaPorId(Long idEtiqueta) {
		return etiquetaRepository.findById(idEtiqueta)
				.orElseThrow(() -> new EntityNotFoundException("Etiqueta não encontrada"));
	}

	public Page<Etiqueta> listarEtiquetas(Pageable pageable) {		
		return etiquetaRepository.findAll(pageable); 
	}
	
	public void salvarEtiqueta(Etiqueta etiqueta, Usuario usuario) {		
		Optional<Etiqueta> optionalEtiqueta = etiquetaRepository.findByNome(etiqueta.getNome());
		
		List<Etiqueta> listaEtiquetasDoUsuario = usuarioService
				.buscarUsuarioPorId(usuario.getId())
				.getEtiquetas();
		
		if(optionalEtiqueta.isEmpty()) {	
			
			etiquetaRepository.save(etiqueta);
			
			listaEtiquetasDoUsuario.add(etiqueta);
			usuario.setEtiquetas(listaEtiquetasDoUsuario);
			
			usuarioService.SalvarUsuarioSemVerificacao(usuario);
			
		}else if (!listaEtiquetasDoUsuario.contains(optionalEtiqueta.get())) {	
			listaEtiquetasDoUsuario.add(optionalEtiqueta.get());
			
			usuario.setEtiquetas(listaEtiquetasDoUsuario);
			
			usuarioService.SalvarUsuarioSemVerificacao(usuario);		
		}else 
			throw new EntityExistException("Etiqueta já cadastrada.");					
	}
	
	public void deletarEtiqueta(Long id, Usuario usuario) {		
		
		Usuario usuarioBuscado = usuarioService.buscarUsuarioPorEmail(usuario.getEmail());
		
		Etiqueta etiqueta = this.buscarEtiquetaPorId(id);	
		
		List<Etiqueta> etiquetasDoUsuarioBuscado = usuarioBuscado.getEtiquetas();
		
		etiquetasDoUsuarioBuscado.remove(etiqueta);
		
		usuarioService.SalvarUsuarioSemVerificacao(usuarioBuscado);
		
		if(etiqueta.getUsuarios().isEmpty())
			etiquetaRepository.delete(etiqueta);		
	}


	public Etiqueta atualizarEtiqueta(Etiqueta etiqueta) {		
		return etiquetaRepository.save(etiqueta);
	}
	
}
