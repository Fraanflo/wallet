package cl.bootcamp.AlkeWallet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConfig {


	
	@Bean
SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
		
		String[] matchers = new String[] {"/registro", "/registro/**", "/login"};
		return http
				.authorizeHttpRequests(request -> 
    				request.requestMatchers(matchers).permitAll())
				
				.authorizeHttpRequests(request -> 
            		request.anyRequest().authenticated())			
				
				
				.sessionManagement(sessionManagement -> {
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .maximumSessions(10)
                .expiredUrl("/login");  
            sessionManagement.invalidSessionUrl("/login");
          })
            .formLogin((form)-> 
            		form
            		.loginPage("/login")
                    .defaultSuccessUrl("/home")
                    .permitAll()
                   ) 
            .logout(logout-> logout.permitAll())
            .csrf(csrf-> csrf.disable())
            .build();
	}
@Bean
 BCryptPasswordEncoder passwordEncoder() {
  return new BCryptPasswordEncoder();
}
@Bean
 WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring().requestMatchers("/WEB-INF/jsp/**");
}
}