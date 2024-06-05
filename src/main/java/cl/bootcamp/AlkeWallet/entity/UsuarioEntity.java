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
@Table(name="usuario")
@Data
public class UsuarioEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name = "correo")
	private String username;
	
	@Column(name = "clave")
	private String password;
	
	@Column(name="saldo")
	private int saldo;
	
	@Column(name="fecha_de_creacion")
	private Timestamp fechaDeCreacion;
	}
