package com.gft.api.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gft.api.entities.Etiqueta;
import com.gft.api.repositories.EtiquetaRepository;

@Service
public class EtiquetaService{
	
	private final EtiquetaRepository etiquetaRepository;
	
	public EtiquetaService(EtiquetaRepository etiquetaRepository) {
		this.etiquetaRepository = etiquetaRepository;
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
	
	public Etiqueta salvarEtiqueta(Etiqueta etiqueta) {
		
		Optional<Etiqueta> optional =etiquetaRepository.findByNome(etiqueta.getNome());
		
		if(optional.isEmpty())
			//fazer lógica para atribuir o usuário antes de salvar
			return etiquetaRepository.save(etiqueta);
			
		//fazer lógica para atribuir o usuário a etiqueta e depois de salvar
		Etiqueta etiquetaExistente = optional.get();
		
		return null;
	}
	
	public Etiqueta atualizarEtiqueta(Etiqueta etiqueta, Long id) {
		Etiqueta etiquetaOriginal = this.buscarEtiquetaPorId(id);
		
		etiquetaOriginal.setNome(etiqueta.getNome());
		
		return this.salvarEtiqueta(etiquetaOriginal);		
	}


	public void deletarEtiqueta(Long id) {
		Etiqueta etiqueta = this.buscarEtiquetaPorId(id);	
		etiquetaRepository.delete(etiqueta);
	}
	
	
}
