package br.com.creathus.contato.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.creathus.contato.dto.CredenciaisDTO;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private JWTUtil jwtutil;
	
	private AuthenticationManager authenticationManager;
	

	public JWTAuthenticationFilter(JWTUtil jwtutil, AuthenticationManager authenticationManager) {
		this.jwtutil = jwtutil;
		this.authenticationManager = authenticationManager;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req,
			HttpServletResponse resp,
			FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String username = ((UserSS) auth.getPrincipal()).getUsername();
		String token = jwtutil.generateToken(username);
		resp.addHeader("Authorization", "Bearer " + token);
		
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		try {
			
			CredenciaisDTO creds = new ObjectMapper()
	                .readValue(request.getInputStream(), CredenciaisDTO.class);
	
	        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>());
	        
	        Authentication auth = authenticationManager.authenticate(authToken);
	        return auth;
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
