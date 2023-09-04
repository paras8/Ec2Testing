package com.apigateway.config;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class RoleBasedAuthorizer implements Converter<Jwt, Collection<GrantedAuthority>> {

	@Override
	public Collection<GrantedAuthority> convert(Jwt jwt) {
		Collection<GrantedAuthority> authorities = new HashSet<>();
		
		List<String> roles = jwt.getClaimAsStringList("cognito:groups");
		roles.forEach(role->authorities.add(new SimpleGrantedAuthority("ROLE_"+role)));
		return authorities;
	}

}
