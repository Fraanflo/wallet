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
 * Controlador para la funcionalidad de retirar fondos de la wallet.
 */
@Controller
public class RetirarController {

	private final TransaccionService transaccionService;
	private final UsuarioService usuarioService;

	/**
	 * Constructor para la inyección de dependencias.
	 *
	 * @param transaccionService Servicio para realizar transacciones.
	 * @param usuarioService     Servicio para operaciones relacionadas con
	 *                           usuarios.
	 */
	@Autowired
	public RetirarController(TransaccionService transaccionService, UsuarioService usuarioService) {
		this.transaccionService = transaccionService;
		this.usuarioService = usuarioService;
	}

	/**
	 * Maneja las solicitudes GET de retirar
	 *
	 * @param realizada Indica si la transacción de retiro de dinero fue realizada
	 * @return vista retirar dinero.
	 */
	@GetMapping("/retirar")
	public ModelAndView retirarGet(@RequestParam(defaultValue = "false") boolean realizada) {
		ModelAndView mav = new ModelAndView("retirar.jsp");
		mav.addObject("realizada", realizada);
		return mav;
	}

	/**
	 * Maneja las solicitudes POST a /retirar.
	 *
	 * @param monto              monto a retirar
	 * @param authentication     info de autenticación del usuario conectado
	 * @param redirectAttributes atributos para redireccionar la vista
	 * @return redirección a la página de inicio o de retirar en caso de error(con
	 *         alerta).
	 */
	@PostMapping("/retirar")
	public String retirarPost(@RequestParam("monto") int monto, Authentication authentication,
			RedirectAttributes redirectAttributes) {

		Usuario usuario = usuarioService.getByUsername(authentication.getName());
		String correo = usuario.getCorreo();
		try {
			transaccionService.retirar(correo, monto);
			redirectAttributes.addFlashAttribute("alertaTitulo", "Éxito");
			redirectAttributes.addFlashAttribute("alertaMensaje", "El retiro se realizó exitosamente.");
			redirectAttributes.addFlashAttribute("alertaTipo", TipoAlerta.SUCCESS);
		} catch (IllegalArgumentException e) {
			redirectAttributes.addFlashAttribute("alertaTitulo", "Error");
			redirectAttributes.addFlashAttribute("alertaMensaje", e.getMessage());
			redirectAttributes.addFlashAttribute("alertaTipo", TipoAlerta.ERROR);
			return "redirect:/retirar";
		}

		return "redirect:/home";
	}
}