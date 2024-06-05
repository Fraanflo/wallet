package cl.bootcamp.AlkeWallet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.bootcamp.AlkeWallet.entity.TransaccionEntity;

@Repository
public interface TransaccionRepository extends CrudRepository<TransaccionEntity,Integer>{

	TransaccionEntity findById(int transaccionId);
}
