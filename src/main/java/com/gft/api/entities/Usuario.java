package com.gft.api.entities;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_usuario")
public class Usuario implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String senha;
	
	@ManyToOne
	private Perfil perfil;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			  name = "tb_usuario_etiqueta", 
			  joinColumns = @JoinColumn(name = "usuario_id"), 
			  inverseJoinColumns = @JoinColumn(name = "etiqueta_id"))
	private List<Etiqueta> etiquetas;
	
	@OneToOne(cascade =CascadeType.ALL)
	private HistoricoUsuarioEtiqueta historico;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return  List.of(this.perfil);
	}
	@Override
	public String getPassword() {
		return getSenha();
	}
	@Override
	public String getUsername() {
		return getNome();
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}

}
