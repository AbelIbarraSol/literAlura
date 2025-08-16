package com.strangecorp.literalura.repository;

import com.strangecorp.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNombre(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.fallecimiento >= :año AND a.nacimiento <= :año")
    List<Autor> obtenerAutoresVivosPorAño(Integer año);
}
