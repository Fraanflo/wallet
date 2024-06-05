package cl.bootcamp.AlkeWallet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
@EnableWebMvc
@Configuration
public class JspConfig {

	@Bean
	InternalResourceViewResolver jspViewResolver(){
	    final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	    viewResolver.setOrder(10);
	    viewResolver.setViewClass(JstlView.class);
	    viewResolver.setViewNames("*.jsp");
	    viewResolver.setPrefix("/WEB-INF/jsp/");
	    viewResolver.setSuffix("");
	    
	    return viewResolver;
	}
}