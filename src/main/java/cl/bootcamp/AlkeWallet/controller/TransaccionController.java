package cl.bootcamp.AlkeWallet.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import cl.bootcamp.AlkeWallet.model.Transaccion;
import cl.bootcamp.AlkeWallet.service.TransaccionService;
import cl.bootcamp.AlkeWallet.service.UsuarioService;

@Controller
public class TransaccionController {
	
private final TransaccionService transaccionService;
private final UsuarioService usuarioService;

	

	public TransaccionController(TransaccionService transaccionService, UsuarioService usuarioService) {
	super();
	this.transaccionService = transaccionService;
	this.usuarioService = usuarioService;
}

	 
	 @GetMapping("/historial")
	 public String viewHistorial(Authentication authentication, Model model) {
		    String correo = authentication.getName(); 
		    List<Transaccion> historial = transaccionService.getHistorial(correo); 
		    model.addAttribute("historial", historial); 
		    return "historial.jsp"; 
		}
}