package dev.estevez.storex.compuexam.services.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.estevez.storex.compuexam.entities.Rol;
import dev.estevez.storex.compuexam.entities.Usuario;
import dev.estevez.storex.compuexam.entities.UsuarioRol;
import dev.estevez.storex.compuexam.repositories.IRolRepository;
import dev.estevez.storex.compuexam.repositories.IUsuarioRepository;
import dev.estevez.storex.compuexam.services.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService{
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Autowired
	private IRolRepository rolRepository;

	@Override
	public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) {
		Usuario usuarioLocal = usuarioRepository.findByUsername(usuario.getUsername());
		
		if (usuarioLocal != null) {
			System.out.println("El usuario ya existe");
			//throw new Exception("El usuario ya está presente");
		}
		
		for(UsuarioRol usuarioRol: usuarioRoles) {
			rolRepository.save(usuarioRol.getRol());
		}
		
		usuario.getUsuarioRoles().addAll(usuarioRoles);	
		usuarioLocal = usuarioRepository.save(usuario);
		
		return usuarioLocal;
	}

}
