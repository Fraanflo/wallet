package cl.bootcamp.AlkeWallet.service;

import java.util.List;

import cl.bootcamp.AlkeWallet.model.Transaccion;
/**
 * Interfaz para el servicio de Transacciones
 */
public interface TransaccionService {

	/**
     * Realiza un depósito en la cuenta del usuario.
     *
     * @param correo correo electrónico del usuario
     * @param monto  monto a depositar.
     */
	void depositar(String correo, int monto);


	  /**
     * Realiza un retiro de la cuenta del usuario.
     *
     * @param correo correo electrónico del usuario
     * @param monto monto a retirar.
     */
	void retirar(String correo, int monto);
	
	/**
     * Realiza una transferencia de fondos entre usuarios
     *
     * @param correo correo electrónico del usuario remitente
     * @param receiverUserId id del usuario receptor
     * @param valor monto a transferir
     */
	void transferir(String correo, int receiverUserId, int valor);
/**
 * Obtiene el historial de transacciones del usuario
 * @param correo correo eléctronico del usuario
 * @return lista de transacciones
 */
	List<Transaccion> getHistorial(String correo);

}
