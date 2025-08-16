package com.strangecorp.literalura.repository;

import com.strangecorp.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Verificar si un libro ya existe por título
    boolean existsByTitulo(String titulo);

    @Query(value = "SELECT l.* FROM libros l WHERE :idioma= ANY(l.lenguajes)", nativeQuery = true)
    List<Libro> obtenerLibrosPorIdioma(String idioma);
}
