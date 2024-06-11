package cl.bootcamp.AlkeWallet.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cl.bootcamp.AlkeWallet.entity.UsuarioEntity;
import jakarta.transaction.Transactional;

/**
 * Repositorio para de entidad UsuarioEntity que proporciona operaciones CRUD y querys
 */
@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioEntity, Integer> {

	/**
     * Busca un usuario por su nombre de usuario (correo electrónico).
     *
     * @param username nombre de usuario (correo electrónico) del usuario a buscar
     * @return Usuario encontrado, o null si no se encuentra ningún usuario con el nombre de usuario.
     */
	@Query("SELECT u FROM UsuarioEntity u WHERE u.username = :correo")
	UsuarioEntity findByUsername(@Param("correo") String username);
	

	  /**
     * Actualiza el saldo de un usuario sumando el monto ingresado
     *
     * @param userId id del usuario cuyo saldo se va a actualizar
     * @param monto monto a sumar al saldo del usuario
     */
	@Modifying
	@Transactional
	@Query("UPDATE UsuarioEntity u SET u.saldo = u.saldo + :monto WHERE u.id = :userId")
	void updateSaldo(int userId, int monto);
}
