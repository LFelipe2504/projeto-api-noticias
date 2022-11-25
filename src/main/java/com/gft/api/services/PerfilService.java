package com.gft.api.services;

import org.springframework.stereotype.Service;

import com.gft.api.entities.Perfil;
import com.gft.api.exception.EntityNotFoundException;
import com.gft.api.repositories.PerfilRepository;

@Service
public class PerfilService{
	
	private final PerfilRepository perfilRepository;	
	
	
	public PerfilService(PerfilRepository perfilRepository) {
		this.perfilRepository = perfilRepository;
	}


	public Perfil buscarPerfilPorId(Long idPerfil) {
		return perfilRepository.findById(idPerfil)
				.orElseThrow(() -> new EntityNotFoundException("Perfil não encontrado. Digite 1 "
						+ "para atribuir o perfil 'ADM' ou 2 para atribuir o perfil 'USUARIO'"));		
	}
	
}
