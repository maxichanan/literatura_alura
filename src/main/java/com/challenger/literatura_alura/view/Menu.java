package com.challenger.literatura_alura.view;

public class Menu {

    public static String exibirMenu(){
        return """
                **********************************************************
                1- Buscar libro por titulo
                2- Listar libro registrado
                3- Listar autores registrados
                4- Listar autores vivo en determinado año
                5- Lista de libro por idioma
                0- Salir
                
                Eliga una opción válida:
                **********************************************************
                """;
    }

    public static String solicitarNombreLibro(){
        return "Ingresa el nombre del libro que desea buscar:";
    }

    public static String solicitarAno(){
        return "Ingrese el año  vivo del autor que desea buscar";
    }

    public static String solicitarIdioma(){
        return """
                **********************************************************
                Idiomas:
                
                1- Español
                2- Inglés
                3- Francés
                4- Portugués
                5- Finlandés
                6- Ruso
                7- Noruego
                8- Italiano
                
                Selecciona una opción válida:
                **********************************************************
                """;
    }


}
