package cl.bootcamp.AlkeWallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import cl.bootcamp.AlkeWallet.model.Usuario;
import cl.bootcamp.AlkeWallet.service.UsuarioService;


@Controller
public class UsuarioController {
	 @Autowired
	    private UsuarioService usuarioService;
	 
	@GetMapping("/login")
    public String login(Model model, @RequestParam(name = "error", required = false) String error) {
       

        // Si hay un error, registra el mensaje de error
        if (error != null) {
            System.out.println("Error en el inicio de sesión: " + error);
            
           
        }
        return "login.jsp";
    }
	@GetMapping("/registro")
	public ModelAndView formGet(@RequestParam(defaultValue="false") boolean creado) {		
	    ModelAndView mav = new ModelAndView("registro.jsp");
	    mav.addObject("creado", creado);
	    mav.addObject("usuario", new Usuario()); 
	    return mav;
	}
	 @PostMapping("/registro")
	    public String formPost(@ModelAttribute Usuario usuario, Model model) {
	        int userCreado = usuarioService.crear(usuario);
	        model.addAttribute("creado", userCreado > 0);
	        return "redirect:/registro?creado=" + (userCreado > 0);
	    }
	    
}
