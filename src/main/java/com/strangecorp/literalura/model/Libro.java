package com.strangecorp.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 500)
    private String titulo;

    // Relación Many-to-Many con Autor
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores;

    @Column(name = "temas", columnDefinition = "text[]")
    private String[] temas;

    @Column(name = "lenguajes", columnDefinition = "varchar(10)[]")
    private String[] lenguajes;

    @Column(name = "etiquetas", columnDefinition = "text[]")
    private String[] etiquetas;

    @Column(name = "resumen", columnDefinition = "TEXT")
    private String resumen;

    @Column(name = "descargas")
    private Integer descargas;

    public Libro() {}

    public String[] getTemas() {
        return temas;
    }

    public void setTemas(String[] temas) {
        this.temas = temas;
    }

    // Método helper para convertir List<String> a String[]
    public void setTemasFromList(List<String> temasList) {
        if (temasList != null && !temasList.isEmpty()) {
            this.temas = temasList.toArray(new String[0]);
        }
    }

    // Método helper para obtener List<String> desde String[]
    public List<String> getTemasAsList() {
        if (temas != null) {
            return Arrays.asList(temas);
        }
        return new ArrayList<>();
    }

    public String[] getLenguajes() {
        return lenguajes;
    }

    public void setLenguajes(String[] lenguajes) {
        this.lenguajes = lenguajes;
    }

    public void setLenguajesFromList(List<String> lenguajesList) {
        if (lenguajesList != null && !lenguajesList.isEmpty()) {
            this.lenguajes = lenguajesList.toArray(new String[0]);
        }
    }

    public List<String> getLenguajesAsList() {
        if (lenguajes != null) {
            return Arrays.asList(lenguajes);
        }
        return new ArrayList<>();
    }

    public String[] getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(String[] etiquetas) {
        this.etiquetas = etiquetas;
    }

    public void setEtiquetasFromList(List<String> etiquetasList) {
        if (etiquetasList != null && !etiquetasList.isEmpty()) {
            this.etiquetas = etiquetasList.toArray(new String[0]);
        }
    }

    public List<String> getEtiquetasAsList() {
        if (etiquetas != null) {
            return Arrays.asList(etiquetas);
        }
        return new ArrayList<>();
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public void setResumenFromList(List<String> resumenList) {
        if (resumenList != null && !resumenList.isEmpty()) {
            this.resumen = resumenList.get(0);
        }
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
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

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        return  "---------- LIBRO ----------\n"+
                "Titulo= " + getTitulo() +"\n"+
                "Autores= " + getAutores().stream().map(autor -> autor.getNombre()).collect(Collectors.joining(", ")) +"\n"+
                "Lenguajes= " + Arrays.stream(getLenguajes()).collect(Collectors.joining(", "))+"\n"+
                "Descargas= " + getDescargas()+"\n"+
                "---------------------------\n";
    }
}