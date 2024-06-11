package cl.bootcamp.AlkeWallet.service;

import java.util.List;

import cl.bootcamp.AlkeWallet.model.Usuario;

/**
 * interfaz para el Servicio de usuario.
 */
public interface UsuarioService {

	/**
	 * Crea un nuevo usuario.
	 *
	 * @param usuario datos del usuario a crear
	 * @return El id del usuario creado
	 */
	int crear(Usuario usuario);
	
	/**
     * Obtiene un usuario por su id
     *
     * @param user_id id del usuario.
     * @return El usuario encontrado, o null si no se encuentra ningún usuario con el id señalado
     */
	Usuario getById(int user_id);


    /**
     * Obtiene un usuario por su nombre de usuario (correo electrónico).
     *
     * @param correo Nombre de usuario (correo) del usuario buscado.
     * @return El usuario encontrado, o null si no se encuentra ningún usuario con el nombre de usuario buscado.
     */
	public Usuario getByUsername(String correo);
	
	 /**
     * Obtiene una lista de todos los usuarios
     *
     * @return Lista de todos los usuarios.
     */
	List<Usuario> listado();

    /**
     * Obtiene el saldo de un usuario por su nombre de usuario (correo).
     *
     * @param correo del usuario cuyo saldo se desea obtener.
     * @return El saldo del usuario, o 0 si no se encuentra ningún usuario con el nombre de usuario buscado.
     */
	public int obtenerSaldoUsuario(String correo);
}
