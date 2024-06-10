package cl.bootcamp.AlkeWallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import cl.bootcamp.AlkeWallet.model.Usuario;
import cl.bootcamp.AlkeWallet.service.TransaccionService;
import cl.bootcamp.AlkeWallet.service.UsuarioService;

@Controller
public class RetirarController {

	private final TransaccionService transaccionService;
    private final UsuarioService usuarioService;

    @Autowired
    public RetirarController(TransaccionService transaccionService, UsuarioService usuarioService) {
        this.transaccionService = transaccionService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/retirar")
    public String viewFormRetiro() {
        return "retirar.jsp";
    }

    @PostMapping("/retirar")
    public String depositar(int monto, Authentication authentication) {
    	 Usuario usuario = usuarioService.getByUsername(authentication.getName());
    	    String correo = usuario.getCorreo();
    	    transaccionService.retirar(correo, monto); 
    	    return "redirect:/home";
    }
}