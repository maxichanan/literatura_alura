package com.challenger.literatura_alura.view;

import com.challenger.literatura_alura.model.*;
import com.challenger.literatura_alura.repository.ILibroRepository;
import com.challenger.literatura_alura.service.ConsumoAPI;
import com.challenger.literatura_alura.service.ConvierteDatos;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private static final String URL = "https://gutendex.com/books/";
    private ConsumoAPI datosApi = new ConsumoAPI();
    private ConvierteDatos convertiDato = new ConvierteDatos();
    private Scanner scanner = new Scanner(System.in);
    private String json;
    private ILibroRepository repositorio;
    private List<Libro> libros;

    public Principal(ILibroRepository repositoryL) {
        this.repositorio = repositoryL;
    }

    public void iniciar(){
        var option = -1;
        while (option!=0){
            System.out.println(Menu.exibirMenu());
             String input = scanner.nextLine();
             option = validarDatoIngresado(input);

            switch (option){
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    libroRegistrados();
                    break;
                case 3:
                    autoresRegistrados();
                    break;
                case 4:
                    autoresVivosEnDeterminadoAno();
                    break;
                case 5:
                    libroPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación");
                    break;
                default:
                    System.out.println("Opción invalida");
                    break;
            }
        }
    }

    public int validarDatoIngresado(String dato){
            if(dato ==  null || dato.isEmpty())
            {
                System.out.println("Ingrese valor numérico");
            }else {
                    try {
                        return Integer.parseInt(dato);
                    } catch (NumberFormatException e) {
                        System.out.println(("Ingresaste letras, se espera valor numérico"));
                    }
                }
        return 9;
    }

    private void buscarLibroPorTitulo() {
        System.out.println(Menu.solicitarNombreLibro());
        var nombreSolicitado= scanner.nextLine();
        if(nombreSolicitado == null || nombreSolicitado.trim().isEmpty()){
            System.out.println("Ingrese el nombre de la película");
            return;
        }
        List<String> nombreDeLosLibros = repositorio.nombreLibrosEnBD();
        if(nombreDeLosLibros.size()>0){
        for (int i = 0; i<nombreDeLosLibros.size();i++){
            String nombreLibro= nombreDeLosLibros.get(i).toLowerCase();
                if(nombreLibro.toLowerCase().contains(nombreSolicitado.toLowerCase())){
                    System.out.println("Ya existe el libro " +nombreDeLosLibros.get(i));
                    return;
                }
            }
        }
        System.out.println(obtenerGuardarDatoBD(nombreSolicitado));



    }

    public String obtenerGuardarDatoBD(String nombreLibro){
        json = datosApi.obtenerDatos(URL + "?search=" + nombreLibro.replace(" ","+"));
        if(json == null || json.trim().isEmpty()){
            return "Datos no encontrado";
        }
        DatosResultado datos = convertiDato.obtenerDatos(json, DatosResultado.class);
        if (datos.dtoLibro() == null || datos.dtoLibro().isEmpty()){
            return "No se encontró resultados";
        }
        Libro guardarLibro = datos.dtoLibro().stream()
                .flatMap(l->l.dtoAutor().stream()
                        .map(a->new Libro(l,a)))
                .findFirst().orElse(null);
        assert guardarLibro != null;
        repositorio.save(guardarLibro);
        return guardarLibro.toString();
    }


    private void libroRegistrados(){
        libros = repositorio.findAll();
        libros.stream().sorted(Comparator.comparing(Libro::getTitulo))
                .forEach(System.out::println);
    }

    private void autoresRegistrados(){
        List<Autor> listaAutores= repositorio.listaAutoresDb();
        if(listaAutores.isEmpty()){
            System.out.println("No hay autores registrados");
        }else {
            listaAutores.stream().forEach(System.out::println);
        }
    }

    private void autoresVivosEnDeterminadoAno(){
        System.out.println(Menu.solicitarAno());
        //int anoSolicitado = Integer.parseInt(scanner.nextLine());
        String datoIngresado = scanner.nextLine();
        int anoSolicitado = validarDatoIngresado(datoIngresado);
        List<Autor> listaAutores= repositorio.listaAutoresEnDeterminadoano(anoSolicitado);
        if(listaAutores.isEmpty()){
            System.out.println("No hay autores registrados en ese año");
        }else {
            listaAutores.stream().forEach(System.out::println);
        }

    }

    private void libroPorIdioma(){
        System.out.println(Menu.solicitarIdioma());
        String datoIngresado = scanner.nextLine();
        int idiomaSolicitado = validarDatoIngresado(datoIngresado) ;
        String evaluarIdioma ="";
        switch (idiomaSolicitado){
            case 1: evaluarIdioma="Español";break;
            case 2: evaluarIdioma="Inglés";break;
            case 3: evaluarIdioma="Francés";break;
            case 4: evaluarIdioma="Portugués";break;
            case 5: evaluarIdioma="Finlandés";break;
            case 6: evaluarIdioma="Ruso";break;
            case 7: evaluarIdioma="Noruego";break;
            case 8: evaluarIdioma="Italiano";break;
            default:
                System.out.println("opción invalida");break;
        }
        var idioma = Idioma.verificaIdioma(evaluarIdioma);
        List<Libro> librosIdiomas= repositorio.findByIdioma(idioma);
        if(librosIdiomas.isEmpty()){
            System.out.println("No hay libros de" + idioma);
        }else
        {
            librosIdiomas.forEach(System.out::println);
        }
    }

}
