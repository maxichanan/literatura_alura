package com.challenger.literatura_alura;

import com.challenger.literatura_alura.repository.ILibroRepository;
import com.challenger.literatura_alura.view.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaAluraApplication implements CommandLineRunner {

    @Autowired
    private ILibroRepository repositoryLibro;

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaAluraApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
         Principal principal = new Principal(repositoryLibro);
        principal.iniciar();
    }
}
