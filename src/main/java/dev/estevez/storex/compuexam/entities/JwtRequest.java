package dev.estevez.storex.compuexam.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Entidad de petición para generar un token JWT.
 * 
 * @author Juan Carlos Estevez Vargas.
 *
 */
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {

	@Getter
	private String username;

	@Getter
	private String password;

}
