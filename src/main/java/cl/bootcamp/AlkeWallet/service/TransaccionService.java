package cl.bootcamp.AlkeWallet.service;

import java.util.List;

import cl.bootcamp.AlkeWallet.model.Transaccion;

public interface TransaccionService {


	void depositar(String correo, int monto);

	void retirar(String correo, int monto);
	
	void transferir(String correo, int receiverUserId, int valor);

	List<Transaccion> getHistorial(String correo);

}
