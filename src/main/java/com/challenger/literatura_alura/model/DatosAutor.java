package com.challenger.literatura_alura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosAutor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") int anoNacimiento,
        @JsonAlias("death_year") int anoFallecimiento
) {
}