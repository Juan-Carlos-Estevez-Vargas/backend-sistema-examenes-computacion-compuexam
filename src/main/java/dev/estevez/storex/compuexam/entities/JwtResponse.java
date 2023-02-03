package dev.estevez.storex.compuexam.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

	@Getter
	@Setter
	private String token;
	
}
