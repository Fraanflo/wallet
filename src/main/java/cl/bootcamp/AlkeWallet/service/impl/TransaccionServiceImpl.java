package cl.bootcamp.AlkeWallet.service.impl;

import java.sql.Timestamp; 
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.bootcamp.AlkeWallet.entity.TransaccionEntity;
import cl.bootcamp.AlkeWallet.entity.UsuarioEntity;
import cl.bootcamp.AlkeWallet.model.Transaccion;
import cl.bootcamp.AlkeWallet.repository.TransaccionRepository;
import cl.bootcamp.AlkeWallet.repository.UsuarioRepository;
import cl.bootcamp.AlkeWallet.service.TransaccionService;

@Service
public class TransaccionServiceImpl implements TransaccionService{

	

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

	public TransaccionServiceImpl(TransaccionRepository transaccionRepository) {
		this.transaccionRepository = transaccionRepository;
	}


	  @Override
	    public List<Transaccion> getHistorial(String correo) {
		  UsuarioEntity usuario = usuarioRepository.findByUsername(correo);

		    if (usuario == null) {
		        throw new IllegalArgumentException("No se encontró ningún usuario con el correo electrónico proporcionado.");
		    }

		    // Obtener el userId del usuario
		    int userId = usuario.getUserId();

		    List<TransaccionEntity> transaccionEntities = transaccionRepository.findBySenderUserIdOrReceiverUserId(userId, userId);

		    // Convertir las entidades de transacción a modelos y devolver en lista
		    return transaccionEntities.stream().map(this::convertToModel).collect(Collectors.toList());
		}
	    private Transaccion convertToModel(TransaccionEntity entity) {
	        Transaccion transaccion = new Transaccion();
	        transaccion.setTransaction_Id(entity.getTransaccionId());
	        transaccion.setSender_User_Id(entity.getSenderUserId());
	        transaccion.setReceiver_User_Id(entity.getReceiverUserId());
	        transaccion.setValor(entity.getValor());
	        transaccion.setCurrency_Id(entity.getCurrencyId());
	        transaccion.setTransaction_Date(entity.getTransactionDate());
	        return transaccion;
	    }


	    public void depositar(String correo, int monto) {
	    	UsuarioEntity usuario = usuarioRepository.findByUsername(correo);
	        if (usuario != null && monto > 0) {
	            usuario.setSaldo(usuario.getSaldo() + monto);
	            usuarioRepository.save(usuario);
	            usuarioRepository.updateSaldo(usuario.getUserId(), usuario.getSaldo());
	        } else {
	            throw new IllegalArgumentException("El usuario no existe o el monto a depositar debe ser mayor a cero.");
	        }
	    }

	@Override
	public void retirar(String correo, int monto) {
		 UsuarioEntity usuario = usuarioRepository.findByUsername(correo);
		 
	        if (usuario != null && monto > 0 && usuario.getSaldo() > monto) {
	        	usuario.setSaldo(usuario.getSaldo() - monto);
	            usuarioRepository.save(usuario);
	            usuarioRepository.updateSaldo(usuario.getUserId(), usuario.getSaldo());
	        } else {
	            throw new IllegalArgumentException("El usuario no existe o el monto a depositar debe ser mayor a cero.");
	        }
	    }
	@Override
	public void transferir(String correo, int receiverUserId, int valor) {
		 // Obtener el usuario remitente
	    UsuarioEntity remitente = usuarioRepository.findByUsername(correo);

	    // Verificar si el usuario remitente existe
	    if (remitente == null) {
	        throw new IllegalArgumentException("El usuario remitente no existe.");
	    }

	    // Verificar si el valor a depositar es mayor que cero
	    if (valor <= 0) {
	        throw new IllegalArgumentException("El valor a depositar debe ser mayor que cero.");
	       
	    }

	    // Verificar si el usuario remitente tiene suficientes fondos para completar la transferencia
	    if (remitente.getSaldo() < valor) {
	        throw new IllegalArgumentException("El usuario remitente no tiene suficientes fondos para completar la transferencia.");
	    }

	    // Crear la transacción
	    TransaccionEntity transaccion = new TransaccionEntity();
	    transaccion.setSenderUserId(remitente.getUserId());
	    transaccion.setReceiverUserId(receiverUserId);
	    transaccion.setValor(valor);
	    transaccion.setTransactionDate(new Timestamp(System.currentTimeMillis()));
	    transaccion.setCurrencyId(1);
	    transaccionRepository.save(transaccion);

	    // Actualizar los saldos de los usuarios involucrados en la transferencia
	    usuarioRepository.updateSaldo(remitente.getUserId(), -valor);
	    usuarioRepository.updateSaldo(receiverUserId, valor);
	}
	}