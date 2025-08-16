![Conejo Cambista](src/main/java/com/strangecorp/literalura/assets/banner.png)
![Versión](https://img.shields.io/badge/versión-1.0.0-blue) ![Estado](https://img.shields.io/badge/estado-Finalizado%20%2F%20Mejoras%20posibles-brightgreen) ![Lenguaje](https://img.shields.io/badge/Java-24-orange) ![Plataforma](https://img.shields.io/badge/Plataforma-Consola-lightgrey)
![Licencia](https://img.shields.io/badge/Licencia-MIT-blue) ![Contribuciones](https://img.shields.io/badge/Contribuciones-bienvenidas-blueviolet) ![Repo](https://img.shields.io/badge/Repositorio-activo-brightgreen)

Aplicación de consola Java para almacenamiento y consulta de libros usando la API de [Gutendex](https://github.com/garethbjohnson/gutendex) y PostgreSQL.

## :floppy_disk: ¿Cómo lo descargo?
Clona el repositorio.
```bash
  git clone https://github.com/AbelIbarraSol/literAlura.git
```
o [⬇ Descarga el proyecto](https://github.com/AbelIbarraSol/literAlura/archive/refs/heads/main.zip) y descomprimelo.

## :point_up::sunglasses: ¿Cómo usarlo?

1. Abre el proyecto con IntelliJ IDEA o tu IDE favorito.
2. Crea una base de datos en PostgreSQL.
3. Modifica las variables de entorno en el archivo **application.properties**.
```properties
spring.datasource.url=jdbc:postgresql://${DB_HOST}/${DB_NAME}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
```
3. Ejecuta el proyecto.
4. Busca libros para almacenarlos en tu Base de Datos y poder realizar las otras consultas del menu.

## ¿Qué cosas puedo hacer con Money Strange?
1. Buscar libros usando la API de Gutendex.<br>
2. Listar todos los libros que buscaste.<br>
3. Listar autores.
4. Consultar los autores que vivieron en un año determinado.<br>
5. Listar los libros almacenados por idioma (Inglés, Español, Portugués o Francés).<br>

## :clipboard: Requisitos previos
- Java 17 o superior.
- IntelliJ IDEA (o cualquier IDE que soporte proyectos Java).
- Conexión a Internet (para acceder a la API de Gutendex).
- PostgreSQL 17.6 o superior

## :coffee: Tecnologías usadas en el proyecto
- Java 24 2025-03-18
- Libreria Gson 2.13.1
- IntelliJ IDEA 2025.1.3 (Community Edition)
- [Gutendex](https://github.com/garethbjohnson/gutendex)
- PostgreSQL 17.6
- Postman v11.58.5

## ⚖ Licencia
El presente proyecto esta bajo la licencia del [MIT](https://choosealicense.com/licenses/mit/)

## :heart: Agradecimientos
El presente proyecto fue creado para el Challenge LiterAlura Oracle Next Education (ONE) con la ayuda y guia de [@AluraLatam](https://www.linkedin.com/school/alura-latam/posts/?feedView=all)

## 👨‍💻 Autor
[@AbelIbarraSol](https://github.com/AbelIbarraSol) aka **MrStrange**.

## :raised_hands: Contribuciones

¡Tus contribuciones son bienvenidas :heart:!<br>Puedes hacer un fork del repositorio y enviar un pull request.
