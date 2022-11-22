package com.gft.api.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gft.api.entities.Etiqueta;
import com.gft.api.entities.Usuario;
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
		Optional<Etiqueta> optional = etiquetaRepository.findByNome(etiqueta.getNome());
		
		if(optional.isEmpty()) {			
			etiquetaRepository.save(etiqueta);
			
			List<Etiqueta> ListaEtiquetas = usuario.getEtiquetas(); 
			ListaEtiquetas.add(etiqueta);
			usuario.setEtiquetas(ListaEtiquetas);
			
			usuarioService.SalvarUsuarioSemVerificacao(usuario);
			
		}
		
		List<Etiqueta> ListaEtiquetas = usuario.getEtiquetas(); 
		ListaEtiquetas.add(optional.get());
		usuario.setEtiquetas(ListaEtiquetas);
		
		usuarioService.SalvarUsuarioSemVerificacao(usuario);		
		
	}
	
	public void deletarEtiqueta(Long id, Usuario usuario) {		
		
		Usuario usuarioBuscado = usuarioService.buscarUsuarioPorNome(usuario.getNome());
		
		Etiqueta etiqueta = this.buscarEtiquetaPorId(id);	
		
		List<Etiqueta> etiquetasDoUsuarioBuscado = usuarioBuscado.getEtiquetas();
		
		etiquetasDoUsuarioBuscado.remove(etiqueta);
		
		usuarioService.SalvarUsuarioSemVerificacao(usuarioBuscado);
		
		if(etiqueta.getUsuarios().isEmpty())
			etiquetaRepository.delete(etiqueta);		
	}

	
}
