package cl.bootcamp.AlkeWallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;

@SpringBootApplication
public class AlkeWalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlkeWalletApplication.class, args);
		/*
		 String password = "1234";

	        // Hash almacenado en la base de datos (obtenido al crear el usuario)
	        String hashedPassword = "$2a$10$jsNcYjn5nvSAlasb/fwtauq75EGTujTuvjXvXmTvIs8J1NlN7e1ce";
	        System.out.println("Contraseña en texto plano: " + password);
	        System.out.println("Hash almacenado en la base de datos: " + hashedPassword);


	        // Verificar si la contraseña ingresada coincide con el hash almacenado

	        boolean passwordMatches = BCrypt.checkpw(password, hashedPassword);

	        if (passwordMatches) {
	            System.out.println("La contraseña coincide.");
	        } else {
	            System.out.println("La contraseña no coincide.");
	        }
	    }
	    */
	}
}
