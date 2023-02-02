package dev.estevez.storex.compuexam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.estevez.storex.compuexam.entities.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

	public Usuario findByUsername(String username);
	
}
