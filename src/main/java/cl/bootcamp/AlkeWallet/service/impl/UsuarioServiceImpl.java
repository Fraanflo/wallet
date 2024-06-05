package cl.bootcamp.AlkeWallet.service.impl;

import java.util.ArrayList; 
import java.util.List;
import java.util.Optional;

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

@Service
@CommonsLog
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

	private final UsuarioRepository usuarioRepository;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

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

	@Override
	public int editar(Usuario usuario) {
		try {

			Optional<UsuarioEntity> usuarioEntityGuardadoOptional = usuarioRepository.findById(usuario.getUser_Id());

			if (usuarioEntityGuardadoOptional.isEmpty())
				return -1;

			UsuarioEntity usuarioEntityGuardado = usuarioEntityGuardadoOptional.get();

			usuarioEntityGuardado.setNombre(usuario.getNombre());
			usuarioEntityGuardado.setUsername(usuario.getCorreo());
			if (usuario.getClave() != null && !usuario.getClave().isEmpty()) {
				String hashPass = new BCryptPasswordEncoder().encode(usuario.getClave());
				usuarioEntityGuardado.setPassword(hashPass);
			}

			UsuarioEntity usuarioGuardado = usuarioRepository.save(usuarioEntityGuardado);

			return usuarioGuardado.getUserId();
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw ex;
		}
	}

	@Override
	public Usuario getByCorreo(String correo) {
		UsuarioEntity usuarioEntity = usuarioRepository.findByUsername(correo);
		if (usuarioEntity == null)
			return null;

		Usuario usuario = new Usuario();
		usuario.setUser_Id(usuarioEntity.getUserId());
		usuario.setNombre(usuarioEntity.getNombre());
		usuario.setCorreo(usuarioEntity.getUsername());

		return usuario;
	}

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

	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 try {
		        log.debug("Intentando cargar usuario con correo: " + username);
		        UsuarioEntity usuario = usuarioRepository.findByUsername(username);
		        
		        if (usuario != null) {
		            List<GrantedAuthority> permissions = new ArrayList<>();
		            return new User(usuario.getUsername(), usuario.getPassword(), permissions) {
		            	/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						public String getNombre() {
		                    return usuario.getNombre();
		            }
		            };
		        } else {
		            log.debug("Usuario no encontrado con el correo electrónico: " + username);
		            throw new UsernameNotFoundException("Usuario no encontrado con el correo electrónico: " + username);
		        }
		    } catch (Exception ex) {
		        log.error("Error al cargar usuario por nombre de usuario: " + ex.getMessage());
		        throw new UsernameNotFoundException("Error al cargar usuario por nombre de usuario", ex);
		    }
		}

	@Override
	public Usuario getByUsername(String correo) {
		UsuarioEntity usuarioEntity=  usuarioRepository.findByUsername(correo);
		if(usuarioEntity== null) return null;
		
		Usuario usuario= new Usuario();
		usuario.setUser_Id(usuarioEntity.getUserId());
		usuario.setNombre(usuarioEntity.getNombre());
		usuario.setCorreo(usuarioEntity.getUsername());
		return usuario;
	}


}