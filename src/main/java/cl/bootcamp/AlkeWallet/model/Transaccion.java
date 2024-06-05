package cl.bootcamp.AlkeWallet.model;

import java.sql.Timestamp;

import lombok.Data;
@Data
public class Transaccion {
private int transaccionId;
private int senderUserId;
private int receiverUserId;
private int valor;
private Timestamp transactionDate;
private int currencyId;
}
