package com.gft.api.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gft.api.entities.HistoricoUsuarioEtiqueta;
import com.gft.api.entities.Usuario;
import com.gft.api.repositories.HistoricoUsuarioEtiquetaRepository;

@Service
public class HistoricoUsuarioEtiquetaService {
	
	private HistoricoUsuarioEtiquetaRepository historicoRepository;		

	public HistoricoUsuarioEtiquetaService(HistoricoUsuarioEtiquetaRepository historicoRepository) {		
		this.historicoRepository = historicoRepository;
	}


	public Page<HistoricoUsuarioEtiqueta> buscarHistoricoAcessoUsuario(Pageable pageable, Usuario usuario) {
		Page<HistoricoUsuarioEtiqueta> historicoDoUsuario = historicoRepository
				.findAllByIdUsuario(pageable, usuario.getId());
		
		return historicoDoUsuario;
	}

	public Page<HistoricoUsuarioEtiqueta> buscarHistoricoAcessoDeTodosOsUsuarios(Pageable pageable) {
		return historicoRepository.findAll(pageable);
	}


	public HistoricoUsuarioEtiqueta salvarHistoricoParaUsuario(HistoricoUsuarioEtiqueta historicoUsuario) {
		return historicoRepository.save(historicoUsuario);
		
	}

}
