package com.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.security.handler.CustomAccessDeniedHandler;
import com.security.handler.CustomAuthenticationSuccessHandler;
import com.security.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private CustomAuthenticationSuccessHandler successHandler;
	
	@Autowired
	private CustomAccessDeniedHandler accessDeniedHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/login").permitAll()
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/user").hasAnyRole("ADMIN","USER")
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")  // Specifies the login page URL (goes to controller to look for this URL)
			//.loginProcessingUrl("/login") // Specifies the URL,which is used in action on the <from> tag
			.usernameParameter("userName") // Username parameter, used in <input> tag
			.passwordParameter("password") // Password parameter, used in <input> tag
			.successHandler(successHandler)
			.and()
			.exceptionHandling().accessDeniedHandler(accessDeniedHandler)
			.and()
			.logout()
			.logoutUrl("/logout");
		
					// Following is to go to direct controller URL for access denied.
						// exceptionHandling().accessDeniedPage("/accessDenied");
		
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	
}
