package cl.bootcamp.AlkeWallet.controller;

import org.springframework.security.core.Authentication; 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cl.bootcamp.AlkeWallet.model.Usuario;
import cl.bootcamp.AlkeWallet.service.UsuarioService;

@Controller
public class HomeController {

	private final UsuarioService usuarioService;
	
    public HomeController(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}

    @GetMapping("/home")
    public ModelAndView home(Authentication authentication, @RequestParam(defaultValue="false") boolean realizada) {
        ModelAndView mav = new ModelAndView("home.jsp");
        
        Usuario nombre = usuarioService.getByUsername(authentication.getName());
        mav.addObject("usuario", nombre);
        
        Integer saldo = usuarioService.obtenerSaldoUsuario(authentication.getName());
        mav.addObject("saldo", saldo);
        
        mav.addObject("realizada", realizada);
        //System.out.println("realizada in HomeController: " + realizada);

        return mav;
    }
    
   
}