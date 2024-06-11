package cl.bootcamp.AlkeWallet.controller;

import java.util.List; 

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import cl.bootcamp.AlkeWallet.model.Transaccion;
import cl.bootcamp.AlkeWallet.service.TransaccionService;
import cl.bootcamp.AlkeWallet.service.UsuarioService;


/**
 * Controlador para la funcionalidad de visualización del historial de transacciones del usuario
 */
@Controller
public class TransaccionController {
	
private final TransaccionService transaccionService;
private final UsuarioService usuarioService;

	
/**
 * Constructor para la inyección de dependencias
 *
 * @param transaccionService Servicio para operaciones relacionadas con transacciones
 * @param usuarioService Servicio para operaciones relacionadas con usuario
 */
	public TransaccionController(TransaccionService transaccionService, UsuarioService usuarioService) {
	super();
	this.transaccionService = transaccionService;
	this.usuarioService = usuarioService;
}

	 /**
     * Maneja las solicitudes GET del historial de transacciones
     *
     * @param authentication Información de autenticación del usuario conectado.
     * @param model 
     * @return Nombre de la vista para el historial de transacciones
     */
	 @GetMapping("/historial")
	 public String viewHistorial(Authentication authentication, Model model) {
		    String correo = authentication.getName(); 
		    List<Transaccion> historial = transaccionService.getHistorial(correo); 
		    model.addAttribute("historial", historial); 
		    return "historial.jsp"; 
		}
}