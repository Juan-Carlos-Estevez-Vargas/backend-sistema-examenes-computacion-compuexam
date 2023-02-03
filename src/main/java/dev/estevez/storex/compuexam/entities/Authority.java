package dev.estevez.storex.compuexam.entities;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Authority implements GrantedAuthority{
	
	private static final long serialVersionUID = 1L;
	private String authority;

	@Override
	public String getAuthority() {
		return this.authority;
	}

}
