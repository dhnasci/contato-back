package br.com.creathus.contato.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.creathus.contato.dto.CredenciaisDTO;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private JWTUtil jwtutil;
	
	private AuthenticationManager authenticationManager;
	
	private Cookie cookie;
	

	public JWTAuthenticationFilter(JWTUtil jwtutil, AuthenticationManager authenticationManager) {
		
		setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
		
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
		Cookie cookie = new Cookie("token", token);
		resp.addCookie(cookie);
		
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
	
	private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

		@Override
		public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException exception) throws IOException, ServletException {
			// TODO Auto-generated method stub
			response.setStatus(401);
            response.setContentType("application/json"); 
            response.getWriter().append(json());
		}
		
		private String json() {
            long date = new Date().getTime();
            return "{\"timestamp\": " + date + ", "
                + "\"status\": 401, "
                + "\"error\": \"Não autorizado\", "
                + "\"message\": \"Email ou senha inválidos\", "
                + "\"path\": \"/login\"}";
        }
		
	}

}
