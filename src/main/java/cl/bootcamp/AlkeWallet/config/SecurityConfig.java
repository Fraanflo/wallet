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

/**
 * Clase que configura la seguridad de la wallet
 */
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConfig {


	/**
	 * Configuración del filtro de seguridad para Http
	 * @param http para configurar la seguridad http
	 * @return ScurityFilterChain 
	 * @throws Exception si ocurre algún error al configurar la seguridad
	 */
	@Bean
SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
        // Se especifican los URL que deben coincidir para permitir el acceso sin autenticación

		String[] matchers = new String[] {"/registro", "/registro/**", "/login"};
		return http
				.authorizeHttpRequests(request -> 
    				request.requestMatchers(matchers).permitAll())
				
				.authorizeHttpRequests(request -> 
            		request.anyRequest().authenticated())			
				
                // Se configura la gestión de sesión

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
	/**
	 * Bean para la codificación de contraseñas utilizando Bcrypt
	 * @return BcryptPasswordEncoder para la codificación de contraseñas
	 */
@Bean
 BCryptPasswordEncoder passwordEncoder() {
  return new BCryptPasswordEncoder();
}

/**
 * Configura la personalización de la seguridad web
 * @return WebSecurityCustomizer para personalizar la seguridad
 */
@Bean
 WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring().requestMatchers("/WEB-INF/jsp/**");
}
}