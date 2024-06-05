package cl.bootcamp.AlkeWallet.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="transaccion")
@Data
public class TransaccionEntity {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="transaction_id")
	private int transaccionId;
	
	@Column(name="sender_user_id")
	private int senderUserId;
	
	@Column(name="receiver_user_id")
	private int receiverUserId;
	
	@Column(name="valor")
	private int valor;
	
	@Column(name="transaction_date")
	private Timestamp transactionDate;
	
	@Column(name="currency_id")
	private int currencyId;
	}
