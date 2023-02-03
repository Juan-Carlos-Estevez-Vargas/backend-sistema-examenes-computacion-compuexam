package dev.estevez.storex.compuexam.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.estevez.storex.compuexam.entities.Usuario;
import dev.estevez.storex.compuexam.repositories.IUsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private IUsuarioRepository usuarioRepository; 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = this.usuarioRepository.findByUsername(username);
		
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario NO encontrado");
		}
		
		return usuario;
	}

}
