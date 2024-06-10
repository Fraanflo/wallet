package cl.bootcamp.AlkeWallet.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.bootcamp.AlkeWallet.config.TipoAlerta;
import cl.bootcamp.AlkeWallet.model.Usuario;
import cl.bootcamp.AlkeWallet.service.TransaccionService;
import cl.bootcamp.AlkeWallet.service.UsuarioService;

@Controller
public class TransferirController {
	 private final TransaccionService transaccionService;
	    private final UsuarioService usuarioService;

	    @Autowired
	    public TransferirController(TransaccionService transaccionService, UsuarioService usuarioService) {
	        this.transaccionService = transaccionService;
	        this.usuarioService = usuarioService;
	    }

	    @GetMapping("/transferir")
	    public ModelAndView transferenciaGet(@RequestParam(defaultValue="false") boolean realizada) {		
	        ModelAndView mav = new ModelAndView("transferir.jsp");
	        mav.addObject("realizada", realizada);
	        System.out.println("Transferencia completada: " + realizada);

	        return mav;
	    }


	    @PostMapping("/transferir")
	    public String transferenciaPost(@RequestParam("receiverUserId") int receiverUserId, 
	                                    @RequestParam("valor") int valor, 
	                                    Authentication authentication, 
	                                    RedirectAttributes redirectAttributes) {
	        Usuario usuario = usuarioService.getByUsername(authentication.getName());
	        
	        if (valor <= 0) {
	            // Si el monto es menor o igual a 0, mostrar una alerta de advertencia
	        	redirectAttributes.addFlashAttribute("alertaTitulo", "Advertencia");
	        	redirectAttributes.addFlashAttribute("alertaMensaje", "El monto a transferir debe ser mayor a 0. Por favor, ingresa un monto válido.");
	        	redirectAttributes.addFlashAttribute("alertaTipo", TipoAlerta.WARNING);

	            return "redirect:/transferir";
	        }

	        // Verificar si el usuario tiene suficientes fondos para realizar la transferencia
	        if (usuario.getSaldo() < valor) {
	            redirectAttributes.addFlashAttribute("alertaTitulo", "Error");
	            redirectAttributes.addFlashAttribute("alertaMensaje", "El usuario remitente no tiene suficientes fondos para completar la transferencia.");
	            redirectAttributes.addFlashAttribute("alertaTipo", TipoAlerta.ERROR);
	            return "redirect:/transferir";
	        }

	        // Realizar la transferencia
	        transaccionService.transferir(usuario.getCorreo(), receiverUserId, valor);
	        redirectAttributes.addAttribute("realizada", true);
	        return "redirect:/home";
	    }
}