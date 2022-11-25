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
import com.gft.api.entities.HistoricoUsuarioEtiqueta;
import com.gft.api.entities.Noticias;
import com.gft.api.entities.Usuario;
import com.gft.api.exception.EntityNotFoundException;

import reactor.core.publisher.Mono;

@Service
public class NoticiaService {
	
	private WebClient webClientNoticia;		
	private final UsuarioService usuarioService;	
	private final HistoricoUsuarioEtiquetaService historicoService;
	private final EtiquetaService etiquetaService;
	

	public NoticiaService(WebClient webClientNoticia, UsuarioService usuarioService,
			HistoricoUsuarioEtiquetaService historicoService, EtiquetaService etiquetaService) {
		this.webClientNoticia = webClientNoticia;
		this.usuarioService = usuarioService;
		this.historicoService = historicoService;
		this.etiquetaService = etiquetaService;
	}
	
	

	public List<NoticiaDTO> obterNoticias(Usuario usuario) {
		Usuario usuarioBuscado = usuarioService.buscarUsuarioPorEmail(usuario.getEmail());
		
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
			
			HistoricoUsuarioEtiqueta historicoUsuario = new HistoricoUsuarioEtiqueta(null, etiqueta.getNome(),
					date, usuarioBuscado.getId());
			historicoService.salvarHistoricoParaUsuario(historicoUsuario);
			
			etiqueta.setNumeroAcessos(etiqueta.getNumeroAcessos()+1);
			etiquetaService.atualizarEtiqueta(etiqueta);
			
		}		
		 
		return noticias;
	}

}
