package com.custom.met.cmmn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import com.custom.met.cmmn.constant.URLConstant;

@Configuration
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true) // enable @PreAuthorize
public class CustomSecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	private AccessDeniedHandler accessDeniedHandler;
	
	private static final String[] whiteList = {
		URLConstant.STATIC_RESOURCE_PATH,	
		URLConstant.JSP_PATH,	
		URLConstant.LOGIN_PAGE_URL,	
		URLConstant.LOGIN_PROC_URL,	
		URLConstant.REGISTER_PAGE_URL,	
		URLConstant.REGISTER_PROC_URL,	
		URLConstant.LOGIN_PROC_URL,	
		URLConstant.ERROR_PAGE_URL,	
	};
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public HttpFirewall httpFirewall() {
		StrictHttpFirewall strictHttpFirewall = new StrictHttpFirewall();
		strictHttpFirewall.setAllowUrlEncodedDoubleSlash(true);
		return strictHttpFirewall;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth
				.requestMatchers(whiteList).permitAll()
				.anyRequest().authenticated()
			)
			.formLogin(form -> form
				.loginPage(URLConstant.LOGIN_PAGE_URL)
				.loginProcessingUrl(URLConstant.LOGIN_PROC_URL)
				.usernameParameter("username")
				.passwordParameter("password")
				.successHandler(authenticationSuccessHandler)
				.failureHandler(authenticationFailureHandler)
				.permitAll()
			)
			.logout(logout -> logout
				.logoutUrl(URLConstant.LOGOUT_PROC_URL)
				.logoutSuccessUrl(URLConstant.LOGOUT_SUCC_URL)
				.invalidateHttpSession(true)
				.permitAll()
			)
			.userDetailsService(userDetailsService)
			.exceptionHandling(exception -> exception
				.accessDeniedHandler(accessDeniedHandler)
			);
		
		return http.build();
	}
}
