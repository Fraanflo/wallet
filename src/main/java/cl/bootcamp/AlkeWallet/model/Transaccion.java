package cl.bootcamp.AlkeWallet.model;

import java.sql.Timestamp;

import lombok.Data;
@Data
public class Transaccion {
private int transaction_Id;
private int sender_User_Id;
private int receiver_User_Id;
private int valor;
private Timestamp transaction_Date;
private int currency_Id;
}
