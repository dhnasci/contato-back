package br.com.creathus.contato.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.creathus.contato.security.JWTAuthenticationFilter;
import br.com.creathus.contato.security.JWTAuthorizationFilter;
import br.com.creathus.contato.security.JWTUtil;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final static String[] PUBLIC_MATCHERS = {
		 "/lista/**",
		 "/contatos/**",
		 "/usuarios/**"
	};
	
	private static final String[] PUBLIC_MATCHERS_GET = {
			"/produtos/**",
			"/categorias/**",
			"/estados/**",
			"/contatos/**"
	};

	private static final String[] PUBLIC_MATCHERS_POST = {
			"/usuarios/**",
			"/contatos/**",
			"/auth/forgot/**"
	};
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JWTUtil jwtutil;
	/*
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		super.configure(http);
		
		http.cors().and().csrf().disable();
		
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
			.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
			.antMatchers(PUBLIC_MATCHERS).permitAll()
			.anyRequest().authenticated();
		
		http.addFilter(new JWTAuthenticationFilter(jwtutil, authenticationManager()));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtutil, userDetailsService));
		// não vai criar sessão de usuário
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		super.configure(http);
		
		http.cors().and().csrf().disable();
		
		http.authorizeRequests()
			.antMatchers(PUBLIC_MATCHERS).permitAll()
			.anyRequest().authenticated();
		
		http.addFilter(new JWTAuthenticationFilter(jwtutil, authenticationManager()));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtutil, userDetailsService));
		// não vai criar sessão de usuário
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bcryptPasswordEncoder());
	}
	/*
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	*/
	@Bean
	  CorsConfigurationSource corsConfigurationSource() {
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
	    return source;
	  }
	
	@Bean
	public BCryptPasswordEncoder bcryptPasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
}
