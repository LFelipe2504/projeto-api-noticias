package com.gft.api.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gft.api.dto.auth.AutenticacaoDTO;
import com.gft.api.dto.auth.TokenDTO;
import com.gft.api.entities.Usuario;

@Service
public class AutenticacaoService {
	
	private final AuthenticationManager authManager;
	
	@Value("${api.jwt.expiration}")
	private String expiration;
	
	@Value("${api.jwt.secret}")
	private String secret;
	
	@Value("${api.jwt.issuer}")
	private String issuer;

	public AutenticacaoService(AuthenticationManager authManager) {
		this.authManager = authManager;
	}
	
	@PostMapping
	public TokenDTO autenticar(AutenticacaoDTO authForm) {
		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(authForm.getNome(),
				authForm.getSenha()));
		
		String token = this.gerarToken(authentication);
		return new TokenDTO(token);
	}
	
	private Algorithm criarAlgoritmo() {
		return Algorithm.HMAC256(secret);
	}

	private String gerarToken(Authentication authentication) {
		Usuario principal = (Usuario) authentication.getPrincipal();
		
		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		
		return JWT.create()
				.withIssuer(issuer)
				.withExpiresAt(dataExpiracao)
				.withSubject(principal.getId().toString())
				.sign(this.criarAlgoritmo());
	}
	
	public boolean verificaToken(String token) {
		
		if(token==null)
			return false;
		
		JWT.require(this.criarAlgoritmo()).withIssuer(issuer).build().verify(token);
		return true;

	}
	
	public  Long retornarIdUsuario(String token) {
		String subject = JWT.require(this.criarAlgoritmo()).withIssuer(issuer).build().verify(token).getSubject();
		
		return Long.parseLong(subject);
	}

}
