package com.challenger.literatura_alura.repository;

import com.challenger.literatura_alura.model.Autor;
import com.challenger.literatura_alura.model.Idioma;
import com.challenger.literatura_alura.model.Libro;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ILibroRepository extends JpaRepository<Libro, Long> {

    @Query("SELECT a FROM Autor a")
    List<Autor> listaAutoresDb();

    @Query("SELECT a FROM Autor a WHERE a.anoNacimiento <= :ano AND  :ano <= a.anoFallecimiento")
    List<Autor> listaAutoresEnDeterminadoano(int ano);

    List<Libro> findByIdioma(Idioma idioma);

    @Query("SELECT l.titulo FROM Libro l")
    List<String> nombreLibrosEnBD();

}
