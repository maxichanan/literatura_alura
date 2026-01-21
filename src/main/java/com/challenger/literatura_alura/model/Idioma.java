package com.challenger.literatura_alura.model;

public enum Idioma {
    Ingles("en", "Inglés"),
    Frances("fr", "Francés"),
    Firlandes("fi", "Finlandés"),
    Italiano("it","Italiano"),
    Noruego("no","Noruego"),
    Ruso("ru","Ruso"),
    Español("es","Español"),
    Portugues("po", "Portugués");

    private String idiomaApi;
    private String idiomaEspanol;

    Idioma (String idiomaApi, String datoEnum){
        this.idiomaApi = idiomaApi;
        this.idiomaEspanol = datoEnum;
    }

    public static Idioma fString(String texto) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.idiomaApi.equalsIgnoreCase(texto)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Ninguna Idioma encontrado: " + texto);
    }

    public static Idioma verificaIdioma(String texto) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.idiomaEspanol.equalsIgnoreCase(texto)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Ninguna Idioma encontrado: " + texto);
    }
}
