package com.challenger.literatura_alura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private int anoNacimiento;
    private int anoFallecimiento;
    @OneToMany(mappedBy = "autor", fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor(List<DatosAutor> a){
        this.nombre = a.get(0).nombre();
        this.anoNacimiento = a.get(0).anoNacimiento();
        this.anoFallecimiento= a.get(0).anoFallecimiento();

    }

    public Autor(DatosAutor datosAutor){
        this.nombre=datosAutor.nombre();
        this.anoNacimiento= datosAutor.anoNacimiento();
        this.anoFallecimiento= datosAutor.anoFallecimiento();
    }

    @Override
    public String toString() {
        //List<Libro> libros= getLibros();
        return "-------------Autor---------------" +'\n'+
                "Nombre: " + nombre + '\n' +
                "Fecha de nacimiento: " + anoNacimiento  + '\n' +
                "Fecha de fallecimiento: " + anoFallecimiento  + '\n' +
                "libros: " + libros.get(0).getTitulo()  + '\n' +
                "--------------------------------"
                ;
    }


    public Autor(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        libros.forEach(e->e.setAutor(this));
        this.libros = libros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnoNacimiento() {
        return anoNacimiento;
    }

    public void setAnoNacimiento(int anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
    }

    public int getAnoFallecimiento() {
        return anoFallecimiento;
    }

    public void setAnoFallecimiento(int anoFallecimiento) {
        this.anoFallecimiento = anoFallecimiento;
    }
}
