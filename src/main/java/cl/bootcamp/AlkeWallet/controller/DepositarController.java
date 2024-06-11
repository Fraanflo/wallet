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

/**
 * Controlador para la funcionalidad de depósito en la wallet.
 */
@Controller
public class DepositarController {
	private final TransaccionService transaccionService;
	private final UsuarioService usuarioService;

	/**
	 * Controlador para la inyección de dependencias
	 * 
	 * @param transaccionService servicio de transacciones
	 * @param usuarioService     servicio para operaciones con usuario
	 */
	@Autowired
	public DepositarController(TransaccionService transaccionService, UsuarioService usuarioService) {
		this.transaccionService = transaccionService;
		this.usuarioService = usuarioService;
	}

	/**
	 * Maneja las solicitudes GET de depositar
	 * 
	 * @param realizada indica si la operación fue realizada
	 * @return vista del depósito
	 */
	@GetMapping("/depositar")
	public ModelAndView depositoGet(@RequestParam(defaultValue = "false") boolean realizada) {
		ModelAndView mav = new ModelAndView("depositar.jsp");
		mav.addObject("realizada", realizada);
		return mav;
	}

	/**
	 * Maneja las solicitudes POST de depósitar
	 *
	 * @param monto monto a depositar
	 * @param authentication info de autenticación del usuario conectado
	 * @param redirectAttributes atributos para redireccionar la vista
	 * @return Redirección a la página de inicio o de depósito en caso de error(con su alerta).
	 */
	@PostMapping("/depositar")
	public String depositoPost(@RequestParam("monto") int monto, Authentication authentication,
			RedirectAttributes redirectAttributes) {

		Usuario usuario = usuarioService.getByUsername(authentication.getName());
		String correo = usuario.getCorreo();
		try {
			transaccionService.depositar(correo, monto);
			redirectAttributes.addFlashAttribute("alertaTitulo", "Éxito");
			redirectAttributes.addFlashAttribute("alertaMensaje", "El depósito se realizó exitosamente.");
			redirectAttributes.addFlashAttribute("alertaTipo", TipoAlerta.SUCCESS);
		} catch (IllegalArgumentException e) {
			redirectAttributes.addFlashAttribute("alertaTitulo", "Error");
			redirectAttributes.addFlashAttribute("alertaMensaje", e.getMessage());
			redirectAttributes.addFlashAttribute("alertaTipo", TipoAlerta.ERROR);
			return "redirect:/depositar";
		}

		return "redirect:/home";
	}
}