package cl.bootcamp.AlkeWallet.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Clase de configuración para el motor de vistas JSP.
 * Esta clase configura la resolución de vistas JSP para la aplicación.
 */
@EnableWebMvc
@Configuration
public class JspConfig {

	@Bean
	InternalResourceViewResolver jspViewResolver(){
	    final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	    viewResolver.setOrder(10);
	    viewResolver.setViewClass(JstlView.class);
	    viewResolver.setViewNames("*.jsp");
	    viewResolver.setPrefix("/WEB-INF/jsp/"); //ubicación de las vistas
	    viewResolver.setSuffix("");
	    
	    return viewResolver;
	}
}