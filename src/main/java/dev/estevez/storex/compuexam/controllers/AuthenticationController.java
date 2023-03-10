package dev.estevez.storex.compuexam.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.estevez.storex.compuexam.config.JwtUtils;
import dev.estevez.storex.compuexam.entities.JwtRequest;
import dev.estevez.storex.compuexam.entities.JwtResponse;
import dev.estevez.storex.compuexam.entities.Usuario;
import dev.estevez.storex.compuexam.services.impl.UserDetailsServiceImpl;

/**
 * Controlador de autenticación de JWT.
 * 
 * @author Juan Carlos Estevez Vargas.
 *
 */
@RestController
@CrossOrigin("*")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtUtils jwtUtils;

	/**
	 * End point para generar un token JWT.
	 * 
	 * @param jwtRequest petición o usuario que inicia sesión.
	 * @return token JWT de respuesta.
	 * @throws Exception
	 */
	@PostMapping("/generate-token")
	public ResponseEntity<?> generarToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		try {
			autenticar(jwtRequest.getUsername(), jwtRequest.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Usuario NO encontrado");
		}

		UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtils.generateToken(userDetails);
		System.out.println(token);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	/**
	 * Autentica un usuario basado en su token JWT.
	 * 
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	private void autenticar(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException disabledException) {
			throw new Exception("Usuario deshabilitado " + disabledException.getMessage());
		} catch (BadCredentialsException badCredentialsException) {
			throw new Exception("Credenciales inválidad " + badCredentialsException.getMessage());
		}
	}
	
	@GetMapping("/actual-usuario")
	public Usuario obtenerUsuarioActual(Principal principal) {
		return (Usuario) this.userDetailsService.loadUserByUsername(principal.getName());
	}

}
