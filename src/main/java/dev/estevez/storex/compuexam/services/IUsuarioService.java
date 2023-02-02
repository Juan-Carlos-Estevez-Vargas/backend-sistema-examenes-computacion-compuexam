package dev.estevez.storex.compuexam.services;

import java.util.Set;

import dev.estevez.storex.compuexam.entities.Usuario;
import dev.estevez.storex.compuexam.entities.UsuarioRol;

public interface IUsuarioService {

	public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles);
	
}
