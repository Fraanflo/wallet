package cl.bootcamp.AlkeWallet.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.bootcamp.AlkeWallet.entity.UsuarioEntity;
import cl.bootcamp.AlkeWallet.model.Usuario;
import cl.bootcamp.AlkeWallet.repository.UsuarioRepository;
import cl.bootcamp.AlkeWallet.service.UsuarioService;
import lombok.extern.apachecommons.CommonsLog;

/**
 * Implementación del servicio de usuario.
 */
@Service
@CommonsLog
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

	private final UsuarioRepository usuarioRepository;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	/**
	 * Crea un nuevo usuario.
	 */
	@Override
	@Transactional
	public int crear(Usuario usuario) {
		try {

			UsuarioEntity usuarioEntity = new UsuarioEntity();
			String hashPass = new BCryptPasswordEncoder().encode(usuario.getClave());

			usuarioEntity.setNombre(usuario.getNombre());
			usuarioEntity.setUsername(usuario.getCorreo());
			usuarioEntity.setFechaDeCreacion(usuario.getFecha_de_creacion());
			usuarioEntity.setPassword(hashPass);

			UsuarioEntity usuarioGuardado = usuarioRepository.save(usuarioEntity);

			return usuarioGuardado.getUserId();

		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw ex;
		}
	}

	/**
	 * Obtiene un usuario por su id
	 */
	@Override
	public Usuario getById(int user_id) {
		UsuarioEntity usuarioEntity = usuarioRepository.findById(user_id).orElse(null);
		if (usuarioEntity == null)
			return null;

		Usuario usuario = new Usuario();
		usuario.setUser_Id(usuarioEntity.getUserId());
		usuario.setNombre(usuarioEntity.getNombre());
		usuario.setCorreo(usuarioEntity.getUsername());

		return usuario;
	}

	/**
	 * Obtiene una lista de todos los usuarios
	 */
	@Override
	public List<Usuario> listado() {
		try {

			List<Usuario> listado = new ArrayList<>();
			Iterable<UsuarioEntity> listadoEntities = usuarioRepository.findAll();

			for (UsuarioEntity usuarioEntity : listadoEntities) {
				Usuario usuario = new Usuario();
				usuario.setUser_Id(usuarioEntity.getUserId());
				usuario.setNombre(usuarioEntity.getNombre());
				usuario.setCorreo(usuarioEntity.getUsername());

				listado.add(usuario);
			}

			return listado;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw ex;
		}
	}

	/**
	 * Carga un usuario por su nombre de usuario (correo) para la autenticación de
	 * Spring Security.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			// Buscar el usuario por su nombre de usuario (correo electrónico)

			UsuarioEntity usuario = usuarioRepository.findByUsername(username);

			if (usuario != null) {
				// Construir los detalles del usuario para la autenticación de Spring Security
				List<GrantedAuthority> permissions = new ArrayList<>();
				return new User(usuario.getUsername(), usuario.getPassword(), permissions) {

				};
			} else {
				// Si no se encuentra el usuario, lanzar una excepción
				log.debug("Usuario no encontrado con el correo electrónico: " + username);
				throw new UsernameNotFoundException("Usuario no encontrado con el correo electrónico: " + username);
			}
			// En caso de error, registrar el error y lanzar una excepción
		} catch (Exception ex) {
			log.error("Error al cargar usuario por nombre de usuario: " + ex.getMessage());
			throw new UsernameNotFoundException("Error al cargar usuario por nombre de usuario", ex);
		}
	}

	/**
	 * Obtiene un usuario por su nombre de usuario (correo electrónico).
	 */
	@Override
	public Usuario getByUsername(String correo) {
		UsuarioEntity usuarioEntity = usuarioRepository.findByUsername(correo);
		if (usuarioEntity == null)
			return null;

		Usuario usuario = new Usuario();
		usuario.setUser_Id(usuarioEntity.getUserId());
		usuario.setNombre(usuarioEntity.getNombre());
		usuario.setCorreo(usuarioEntity.getUsername());
		usuario.setSaldo(usuarioEntity.getSaldo());
		return usuario;
	}

	/**
	 * Obtiene el saldo de un usuario por su nombre de usuario (correo).
	 */
	@Override
	public int obtenerSaldoUsuario(String correo) {
		UsuarioEntity usuario = usuarioRepository.findByUsername(correo);
		if (usuario != null) {
			return usuario.getSaldo();
		} else {
			throw new IllegalArgumentException("El usuario no existe");
		}

	}
}