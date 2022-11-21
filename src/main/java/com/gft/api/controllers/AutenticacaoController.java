package com.gft.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.api.dto.auth.AutenticacaoDTO;
import com.gft.api.dto.auth.TokenDTO;
import com.gft.api.services.AutenticacaoService;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
	
	AutenticacaoService autenticacaoService;	
	
	public AutenticacaoController(AutenticacaoService autenticacaoService) {
		this.autenticacaoService = autenticacaoService;
	}



	@PostMapping
	public ResponseEntity<TokenDTO> autenticar(@RequestBody AutenticacaoDTO auth){
		
		return ResponseEntity.ok(autenticacaoService.autenticar(auth));
	}
	
	
}
