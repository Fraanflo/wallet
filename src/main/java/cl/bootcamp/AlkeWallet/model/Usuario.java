package cl.bootcamp.AlkeWallet.model;

import java.sql.Timestamp;

import lombok.Data;

/**
 * Clase model que representa a un usuario.
 */
@Data
public class Usuario {
private int user_Id;
private String nombre;
private String correo;
private String clave;
private int saldo;
private Timestamp fecha_de_creacion;
}
