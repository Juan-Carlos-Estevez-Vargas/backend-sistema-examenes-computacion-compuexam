package dev.estevez.storex.compuexam.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import dev.estevez.storex.compuexam.services.impl.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtUtils jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String requestTokenHeader = request.getHeader("Authorization");
		System.err.println(requestTokenHeader);
		String username = null;
		String jwtToken = null;

		/**
		 * Si existe el cabecero de authorization y el token JWT inicia cpn Bearer
		 * entonces extraemos el usuario asociado a dicho token JWT.
		 * 
		 * En caso contrario el token es enválido o esta hecho de forma incorrecta.
		 */
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);

			try {
				username = this.jwtUtil.extractUsername(jwtToken);
			} catch (ExpiredJwtException exception) {
				System.out.println("El token ha expirado");
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("Token invalido, no empieza con bearer string");
		}

		/**
		 * Si existe el username asociado existe y no esta autenticado.
		 */
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

			/**
			 * Si el token JWT es válido autenticamos el usuario.
			 */
			if (this.jwtUtil.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		} else {
			System.out.println("El token no es valido");
		}

		filterChain.doFilter(request, response);
	}

}
