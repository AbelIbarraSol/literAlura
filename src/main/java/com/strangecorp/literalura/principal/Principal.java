package com.strangecorp.literalura.principal;

import com.strangecorp.literalura.service.LibroService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Principal {
    Scanner scanner = new Scanner(System.in);
    private LibroService service;

    // Constructor para inyección de dependencias
    public Principal(LibroService service) {
        this.service = service;
    }

    public void menu(){
        boolean continuar = true;
        while (continuar){
            System.out.print("""
                    ==================== 📚LITERALURA 📖 ====================
                    1- Buscar libro.
                    2- Listar libros registrados.
                    3- Listar autores registrados.
                    4- Listar autores vivos en un determinado año.
                    5- Listar libros por idioma.
                    =========================================================
                    Ingrese una opcion: """);
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion){
                case 1 -> {
                    System.out.println("Ingrese el titulo del libro que esta buscando: ");
                    String titulo = scanner.nextLine();
                    service.buscarLibrosEnLaAPI(titulo);
                }
                case 2 ->{
                    service.obtenerLibros().stream().forEach(System.out::println);
                }
                case 3 -> {
                    service.obtenerAutores().stream().forEach(System.out::println);
                }
                case 4 ->{
                    System.out.println("Ingrese una año, para saber que autores estaban vivos: ");
                    service.obtenerAutoresVivosPorAño(scanner.nextInt()).stream().forEach(System.out::println);
                }
                case 5 -> {
                    System.out.print("""
                            ---------- 🌐 IDIOMAS 🌐 ----------
                            1) en - Inglés
                            2) es - Español
                            3) pt - Portugués
                            4) fr - Francés
                            -----------------------------------
                            Ingrese el numero del idioma que desea buscar: """);
                    String idioma = "";
                    switch (scanner.nextInt()){
                        case 1 -> idioma = "en";
                        case 2 -> idioma = "es";
                        case 3 -> idioma = "pt";
                        case 4 -> idioma = "fr";
                        default -> System.out.println("¡No se encontro la opcion ingresada!");
                    }
                    service.obtenerLibrosPorIdioma(idioma).stream().forEach(System.out::println);
                }
                case 0 -> {
                    System.out.println("Fin del programa");
                    continuar = false;
                }
                default -> System.out.println("Ingrese una opcion de la lista");
            }
        }
    }
}
