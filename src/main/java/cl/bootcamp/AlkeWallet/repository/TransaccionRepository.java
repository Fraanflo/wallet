package cl.bootcamp.AlkeWallet.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.bootcamp.AlkeWallet.entity.TransaccionEntity;

@Repository
public interface TransaccionRepository extends CrudRepository<TransaccionEntity, Integer> {

	List<TransaccionEntity> findBySenderUserIdOrReceiverUserId(int senderUserId, int receiverUserId);
}
