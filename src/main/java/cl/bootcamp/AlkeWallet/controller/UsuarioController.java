package cl.bootcamp.AlkeWallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import cl.bootcamp.AlkeWallet.model.Usuario;
import cl.bootcamp.AlkeWallet.service.UsuarioService;

/**
 * Controlador para la gestión de usuarios, incluyendo inicio de sesión y
 * registro.
 */
@Controller
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;

	/**
	 * Maneja las solicitudes GET login para la página de inicio de sesion
	 *
	 * @param model modelo para la vista
	 * @param error da mensaje de error si lo hay al intentar iniciar sesi+ón
	 * @return nombre de la vista para la página login
	 */
	
	@GetMapping("/login")
	public String login(Model model, @RequestParam(name = "error", required = false) String error) {

		// Si hay un error, registra el mensaje de error
		if (error != null) {
			System.out.println("Error en el inicio de sesión: " + error);

		}
		return "login.jsp";
	}
	  /**
     * Maneja las solicitudes GET registro para mostrar el formulario de registro de usuario
     *
     * @param creado indica si el usuario ha sido creado
     * @return ModelAndView formulario de registro
     */
	@GetMapping("/registro")
	public ModelAndView formGet(@RequestParam(defaultValue = "false") boolean creado) {
		ModelAndView mav = new ModelAndView("registro.jsp");
		mav.addObject("creado", creado);
		mav.addObject("usuario", new Usuario());
		return mav;
	}

	 /**
     * Maneja las solicitudes POST de registro para procesar el formulario de registro de usuarios
     *
     * @param usuario Datos del usuario a registrar
     * @param model 
     * @return Redirección a la página de registro con un indicador de éxito
     */
	@PostMapping("/registro")
	public String formPost(@ModelAttribute Usuario usuario, Model model) {
		int userCreado = usuarioService.crear(usuario);
		model.addAttribute("creado", userCreado > 0);
		return "redirect:/registro?creado=" + (userCreado > 0);
	}

}
