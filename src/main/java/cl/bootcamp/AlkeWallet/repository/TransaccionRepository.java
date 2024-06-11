package cl.bootcamp.AlkeWallet.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.bootcamp.AlkeWallet.entity.TransaccionEntity;
/**
 * Repositorio de la entidad TransaccionEntity que proporciona operaciones CRUD

 */
@Repository
public interface TransaccionRepository extends CrudRepository<TransaccionEntity, Integer> {
/**
 * Busca transacciones segun id usuario remitente o receptor
 * @param senderUserId id usuario remitente
 * @param receiverUserId id usuario receptor
 * @return lista de transacciones asociadas al usuario
 */
	List<TransaccionEntity> findBySenderUserIdOrReceiverUserId(int senderUserId, int receiverUserId);
}
