package com.gft.api.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.gft.api.dto.noticia.NoticiaDTO;
import com.gft.api.entities.Etiqueta;
import com.gft.api.entities.Noticias;
import com.gft.api.entities.Usuario;
import com.gft.api.exception.EntityNotFoundException;

import reactor.core.publisher.Mono;

@Service
public class HistoricoUsuarioEtiquetaService {
	
	private WebClient webClientNoticia;	
	
	private final UsuarioService usuarioService;
	

	public HistoricoUsuarioEtiquetaService(WebClient webClientNoticia, UsuarioService usuarioService) {
		this.webClientNoticia = webClientNoticia;
		this.usuarioService = usuarioService;
	}
	
	

	public List<NoticiaDTO> obterNoticias(Usuario usuario) {
		
		Usuario usuarioBuscado = usuarioService.buscarUsuarioPorNome(usuario.getNome());
		
		List<Etiqueta> listaEtiquetasDoUsuario = usuarioBuscado.getEtiquetas();
		if(listaEtiquetasDoUsuario.isEmpty())
			throw new EntityNotFoundException("Não há etiqueta cadastrada");
		
		String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		List<NoticiaDTO> noticias = new ArrayList<>();
		
		for (Etiqueta etiqueta : listaEtiquetasDoUsuario) {
			
			String nomeEtiqueta = etiqueta.getNome();
			
			Mono<Noticias> monoNoticia = this.webClientNoticia
				.method(HttpMethod.GET)
					.uri("/?q="+nomeEtiqueta+"&date="+date)
					.retrieve()
					.bodyToMono(Noticias.class);
			
			monoNoticia.block().getList().stream().forEach(n -> {
				if(n.getDate().equals(date)) {
					noticias.add(n);
				}
			});
			
		}
		 
		return noticias;
	}

}
