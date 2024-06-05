package cl.bootcamp.AlkeWallet.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cl.bootcamp.AlkeWallet.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioEntity,Integer>{

	  @Query("SELECT u FROM UsuarioEntity u WHERE u.username = :correo")
	    UsuarioEntity findByUsername(@Param("correo") String username);
	  
	  @Query("SELECT u.nombre FROM UsuarioEntity u WHERE u.username = :correo")
	    String findNombreByUsername(@Param("correo") String username);

	}