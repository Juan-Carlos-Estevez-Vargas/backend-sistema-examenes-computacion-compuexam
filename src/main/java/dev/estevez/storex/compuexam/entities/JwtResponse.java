package dev.estevez.storex.compuexam.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad de respuesta, se encarga solo de responder un token JWT.
 * 
 * @author Juan Carlos Estevez Vargas.
 *
 */
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

	@Getter
	@Setter
	private String token;

}
