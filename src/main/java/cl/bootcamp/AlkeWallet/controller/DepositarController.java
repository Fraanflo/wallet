package cl.bootcamp.AlkeWallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.bootcamp.AlkeWallet.model.Usuario;
import cl.bootcamp.AlkeWallet.service.TransaccionService;
import cl.bootcamp.AlkeWallet.service.UsuarioService;
@Controller
public class DepositarController {
	private final TransaccionService transaccionService;
    private final UsuarioService usuarioService;

    @Autowired
    public DepositarController(TransaccionService transaccionService, UsuarioService usuarioService) {
        this.transaccionService = transaccionService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/depositar")
    public String viewFormDeposito() {
        return "depositar.jsp";
    }

    @PostMapping("/depositar")
    public String depositar(@RequestParam(name = "monto") Integer monto, Authentication authentication) {
        if (monto == null) {
            // Manejo del error cuando el monto es nulo
            return "redirect:/error?mensaje=El monto no puede ser nulo";
        }
        
        Usuario usuario = usuarioService.getByUsername(authentication.getName());
        String correo = usuario.getCorreo();
        transaccionService.depositar(correo, monto); 
        return "redirect:/home";
    }
}