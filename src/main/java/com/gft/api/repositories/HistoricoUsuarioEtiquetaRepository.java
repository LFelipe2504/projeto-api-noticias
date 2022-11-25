package com.gft.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.api.entities.HistoricoUsuarioEtiqueta;

@Repository
public interface HistoricoUsuarioEtiquetaRepository extends JpaRepository<HistoricoUsuarioEtiqueta, Long>{
	
	Optional<HistoricoUsuarioEtiqueta> findByNomeEtiqueta(String nome);
	
	Page<HistoricoUsuarioEtiqueta> findAll(Pageable pageable);
	
	Page<HistoricoUsuarioEtiqueta> findAllByIdUsuario(Pageable pageable, Long idUsuario);
	
	List<HistoricoUsuarioEtiqueta> findAllByIdUsuario(Long idUsuario);
}
