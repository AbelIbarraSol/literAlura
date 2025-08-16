package com.strangecorp.literalura.service;

import com.strangecorp.literalura.model.*;
import com.strangecorp.literalura.repository.AutorRepository;
import com.strangecorp.literalura.repository.LibroRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibroService {
    private ConsumoDeAPI consumoAPI;
    private ConvierteDatos conversor;
    private LibroRepository libroRepository;

    private String URL_BASE = "https://gutendex.com/books/?search=";

    private AutorRepository autorRepository;

    //Inyeccion de dependencias
    public LibroService(ConsumoDeAPI consumoAPI, ConvierteDatos conversor, LibroRepository libroRepository, AutorRepository autorRepository) {
        this.consumoAPI = consumoAPI;
        this.conversor = conversor;
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public Libro buscarLibrosEnLaAPI(String titulo) {
        try {
            String url = URL_BASE+titulo.replace(" ", "%20").trim();
            System.out.println("Enlace: "+url);
            String json = consumoAPI.obtenerDatos(url);
            Datos datos = conversor.obtenerDatos(json, Datos.class);
            List<Libro> libros = convertirDatosALibros(datos.resultado());

            Optional<Libro> primerLibroEncontrado = libros.stream().findFirst();

            if (primerLibroEncontrado.isPresent()) {
                Libro libro =  primerLibroEncontrado.get();
                System.out.println(libro);
                guardarLibrosEnBD(libro);
                return libro;
            }else {
                System.out.println("❌ No se encontraron libros con el título: " + titulo);
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error en busqueda de API"+ e.getMessage());
        }
    }

    private List<Libro> convertirDatosALibros(List<DatosLibros> datosLibros){
        return datosLibros.stream()
                .map(this::convertirDatosALibro)
                .collect(Collectors.toList());
    }

    private Libro convertirDatosALibro(DatosLibros dL) {
        Libro libro = new Libro();

        libro.setTitulo(dL.titulo());
        libro.setDescargas(dL.descargas());
        libro.setTemasFromList(dL.temas());
        libro.setLenguajesFromList(dL.lenguajes());
        libro.setEtiquetasFromList(dL.etiquetas());
        libro.setResumenFromList(dL.resumen());

        List<Autor> autores = procesarAutores(dL.autor());
        libro.setAutores(autores);

        return libro;
    }

    private List<Autor> procesarAutores(List<DatosAutor> datosAutores) {
        return datosAutores.stream()
                .map(this::procesarAutor)
                .collect(Collectors.toList());
    }

    private Autor procesarAutor(DatosAutor datosAutor) {
        //Option para saber si existe el autor o no
        Optional<Autor> autorExiste = autorRepository.findByNombre(datosAutor.nombre());
        if (autorExiste.isPresent()) {
            return autorExiste.get();
        }
        //Else -> crear autor
        Autor autor = new Autor(
                datosAutor.nombre(),
                datosAutor.nacimiento(),
                datosAutor.fallecimiento());
        return autor;
    }

    @Transactional
    private void guardarLibrosEnBD(Libro libro) {
        if (!libroRepository.existsByTitulo(libro.getTitulo())) {
            try {
                List<Autor> autoresExistentes = new ArrayList<>();

                for (Autor autor : libro.getAutores()) {
                    Optional<Autor> autorExistente = autorRepository.findByNombre(autor.getNombre());

                    if (autorExistente.isPresent()) {
                        // Usar el autor existente directamente
                        autoresExistentes.add(autorExistente.get());
                    } else {
                        // Para autores nuevos, asegurarse de que no tengan ID
                        Autor nuevoAutor = new Autor();
                        nuevoAutor.setNombre(autor.getNombre());
                        nuevoAutor.setNacimiento(autor.getNacimiento());
                        nuevoAutor.setFallecimiento(autor.getFallecimiento());

                        Autor autorGuardado = autorRepository.save(nuevoAutor);
                        autoresExistentes.add(autorGuardado);
                    }
                }

                // Asignar los autores (existentes o nuevos) al libro
                libro.setAutores(autoresExistentes);

                libroRepository.save(libro);
                System.out.println("💾 Guardado: " + libro.getTitulo());

            } catch (Exception e) {
                System.err.println("❌ Error al guardar: " + libro.getTitulo() + " - " + e.getMessage());
                e.printStackTrace(); // Para ver más detalles del error
            }
        } else {
            System.out.println("📖 Ya existe: " + libro.getTitulo() + " en la base de datos");
        }
    }

    public List<Libro> obtenerLibros(){
        return libroRepository.findAll();
    }

    public List<Autor> obtenerAutores(){
        return autorRepository.findAll();
    }

    public List<Autor> obtenerAutoresVivosPorAño(Integer año) {
        List<Autor> autoresVivos = autorRepository.obtenerAutoresVivosPorAño(año);
        if (autoresVivos.isEmpty()) {
            System.out.println("No se encontraron autores vivos en esas fechas");
            return autoresVivos;
        }else {
            return autoresVivos;
        }
    }

    public List<Libro> obtenerLibrosPorIdioma(String idioma) {
        return libroRepository.obtenerLibrosPorIdioma(idioma);
    }
}
