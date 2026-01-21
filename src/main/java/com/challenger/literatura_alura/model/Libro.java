package com.challenger.literatura_alura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @ManyToOne(cascade = CascadeType.ALL)
    private Autor autor;
    @Enumerated(EnumType.STRING)
    private  Idioma idioma;
    private Double cantDescarga;

    public Libro(DatosLibro datosLibro, DatosAutor datosAutor) {
        Autor a = new Autor(datosAutor);
        this.titulo = datosLibro.titulo();
        this.idioma = Idioma.fString(datosLibro.idioma().get(0).trim());
        this.cantDescarga = datosLibro.cantDescarga();
        this.autor = a;
    }

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.idioma = Idioma.fString(datosLibro.idioma().get(0).trim());
        this.cantDescarga = datosLibro.cantDescarga();
        //this.autor = autor;
    }

    public Libro(){}

    @Override
    public String toString() {
        return
                "-------------Libro---------------" +'\n'+
                        "Titulo:" + titulo +'\n'+
                        "Autor:" + autor.getNombre() +'\n'+
                        "Idioma:" + idioma +'\n'+
                        "Numero de descargas:" + cantDescarga +'\n'+
                        "--------------------------------"
                ;
    }

    public String toStringNombreLibro(){
        return this.titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Double getCantDescarga() {
        return cantDescarga;
    }

    public void setCantDescarga(Double cantDescarga) {
        this.cantDescarga = cantDescarga;
    }
}
