package cl.bootcamp.AlkeWallet.service.impl;

import java.util.ArrayList; 
import java.util.List;

import org.springframework.stereotype.Service;

import cl.bootcamp.AlkeWallet.entity.TransaccionEntity;
import cl.bootcamp.AlkeWallet.model.Transaccion;
import cl.bootcamp.AlkeWallet.repository.TransaccionRepository;
import cl.bootcamp.AlkeWallet.service.TransaccionService;

@Service
public class TransaccionServiceImpl implements TransaccionService{

	private final TransaccionRepository transaccionRepository;

	public TransaccionServiceImpl(TransaccionRepository transaccionRepository) {
		this.transaccionRepository = transaccionRepository;
	}


	  @Override
	    public List<Transaccion> listado() {
	        try {
	            List<Transaccion> listado = new ArrayList<>();
	            Iterable<TransaccionEntity> listadoTransaccion = transaccionRepository.findAll();

	            for (TransaccionEntity transaccionEntity : listadoTransaccion) {
	                Transaccion transaccion = new Transaccion();
	                transaccion.setTransaccionId(transaccionEntity.getTransaccionId());
	                transaccion.setReceiverUserId(transaccionEntity.getReceiverUserId());
	                transaccion.setSenderUserId(transaccionEntity.getSenderUserId());
	                transaccion.setTransactionDate(transaccionEntity.getTransactionDate());

	                listado.add(transaccion);
	            }

	            return listado;

	        } catch (Exception ex) {
	    
	            System.err.println(ex.getMessage()); 
	            throw ex;
	        }
	    }
	}