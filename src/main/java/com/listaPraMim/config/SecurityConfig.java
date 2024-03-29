package com.listaPraMim.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.listaPraMim.enums.Perfil;
import com.listaPraMim.security.JWTAuthenticationFilter;
import com.listaPraMim.security.JWTAuthorizationFilter;
import com.listaPraMim.security.JWTUtil;
import com.listaPraMim.utils.RestConstants;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
		
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
    private Environment environment;
	
	private Perfil perfil;
	
	private static final String h2 = "/h2-console/**";

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		if (Arrays.asList(environment.getActiveProfiles()).contains("test")) {
            http.headers().frameOptions().disable();
        }
		http.cors().disable();
		http.authorizeRequests()
			.antMatchers(HttpMethod.POST, RestConstants.USUARIO_URI).permitAll()
			.antMatchers(h2).permitAll()
			.anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCriptPasswordEncoder());
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSurce() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration cors = new CorsConfiguration().applyPermitDefaultValues();
		source.registerCorsConfiguration("/**", cors);
		return source;
	}
	
	@Bean
	public BCryptPasswordEncoder bCriptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
