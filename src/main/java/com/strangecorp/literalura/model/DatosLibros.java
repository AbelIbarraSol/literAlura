package com.strangecorp.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
    @JsonAlias("title") String titulo,
    @JsonAlias("subjects") List<String> temas,
    @JsonAlias("authors") List<DatosAutor> autor,
    @JsonAlias("summaries") List<String> resumen,
    @JsonAlias("bookshelves") List<String> etiquetas,
    @JsonAlias("languages") List<String> lenguajes,
    @JsonAlias("download_count") Integer descargas)
{}
