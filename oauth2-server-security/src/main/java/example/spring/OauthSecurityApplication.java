package example.spring;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.spring.model.User;

@EnableWebSecurity
@RestController
@SpringBootApplication
@EnableAuthorizationServer
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class OauthSecurityApplication extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
		http
			.antMatcher("/**")
			.authorizeRequests()
				.antMatchers("/", "/webjars/**")
				.permitAll()
			.anyRequest()
				.authenticated()
				.and()
			.formLogin()
                .permitAll()
                .and()
			.logout()
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)
				.permitAll()
				.and()
			.csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		// @formatter:on
    }

    @RequestMapping("/userDetails")
    public User userDetails(Principal principal) {
        return new User((AbstractAuthenticationToken) principal);
    }

    @RequestMapping("/user")
    public Map<String, String> user(Principal principal) {
        User user = new User((AbstractAuthenticationToken) principal);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", user.getName());
        return map;
    }

    @RequestMapping("/me")
    public Map<String, String> me(Principal principal) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", principal.getName());
        return map;
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(org.springframework.security.core.userdetails.User.withUsername("user").password("password")
                .roles("USER").build());
        manager.createUser(org.springframework.security.core.userdetails.User.withUsername("admin").password("password")
                .roles("USER", "ADMIN").build());
        return manager;
    }

    public static void main(String[] args) {
        SpringApplication.run(OauthSecurityApplication.class, args);
    }

}
