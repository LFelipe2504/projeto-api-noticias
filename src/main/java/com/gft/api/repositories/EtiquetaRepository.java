package com.gft.api.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.api.entities.Etiqueta;

@Repository
public interface EtiquetaRepository extends JpaRepository<Etiqueta, Long>{
	
	Optional<Etiqueta> findByNome(String nome);
	
	Page<Etiqueta> findAll(Pageable pageable);
	
}
