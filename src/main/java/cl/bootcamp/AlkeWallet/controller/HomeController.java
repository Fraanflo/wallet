package cl.bootcamp.AlkeWallet.controller;

import java.util.Map;

import org.springframework.security.core.Authentication; 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import cl.bootcamp.AlkeWallet.model.Usuario;
import cl.bootcamp.AlkeWallet.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
/**
 * Controlador de la página del menú luego de autenticar
 */
@Controller
public class HomeController {

	private final UsuarioService usuarioService;
	/**
	 * Constructor para inyeccion de dependencias
	 * @param usuarioService servicio para operaciones usuario
	 */
    public HomeController(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}
/**
 * Maneja las solicitudes GET del home
 * @param authentication info de autenticación del usuario 
 * @param request para acceder a atributos flash
 * @return vista de la página del menu
 */
    @GetMapping("/home")
    public ModelAndView home(Authentication authentication, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("home.jsp");

        // Recuperar los atributos flash si están presentes
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (flashMap != null) {
            mav.addObject("alertaTitulo", flashMap.get("alertaTitulo"));
            mav.addObject("alertaMensaje", flashMap.get("alertaMensaje"));
            mav.addObject("alertaTipo", flashMap.get("alertaTipo"));
        }

        Usuario usuario = usuarioService.getByUsername(authentication.getName());
        mav.addObject("usuario", usuario);

        Integer saldo = usuarioService.obtenerSaldoUsuario(authentication.getName());
        mav.addObject("saldo", saldo);

        return mav;
    }
}
    
   
