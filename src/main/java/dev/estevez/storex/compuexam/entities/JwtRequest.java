package dev.estevez.storex.compuexam.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {
	
	@Getter
	private String username;
	
	@Getter
	private String password;

}
