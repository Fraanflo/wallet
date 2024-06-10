package cl.bootcamp.AlkeWallet.service;

import java.util.List;

import cl.bootcamp.AlkeWallet.model.Usuario;


public interface UsuarioService {
	int crear(Usuario usuario);
	int editar(Usuario usuario);
	Usuario getByCorreo(String username);
	Usuario getById(int user_id);
	public Usuario getByUsername(String correo) ;
	List<Usuario> listado();
	public int obtenerSaldoUsuario(String correo);
}
