package example.spring;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			.authorizeRequests()
				.antMatchers("/css/**", "/index").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN") // ROLE_ADMIN
				.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')") // ROLE_ADMIN e ROLE_DBA
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.failureUrl("/login-error")
				.permitAll()
				.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/index")
				.invalidateHttpSession(true)
				.and()
			.httpBasic();
		// @formatter:on
	}
	
}
