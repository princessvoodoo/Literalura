# Literalura
# Buscador de Libros

Este es un proyecto de un buscador de libros implementado en Java Spring. El proyecto permite buscar libros y guarda los detalles de los libros buscados en una base de datos.

## Características

- Buscar libros por título, autor, género, etc.
- Guardar los libros buscados en una base de datos.
- Interfaz de usuario simple y amigable.
- API REST para interactuar con la aplicación.

## Requisitos

- Java 17
- Spring Boot 3.2
- Maven 4
- Proyecto en Jar
- MySQL

## Instalación

1. **Clonar el repositorio:**

    ```bash
    git clone https://github.com/tu_usuario/buscador-de-libros.git
    cd buscador-de-libros
    ```

2. **Configurar la base de datos:**

    Crea una base de datos en MySQL y actualiza el archivo `application.properties` con tus credenciales de base de datos:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/nombre_de_tu_base_de_datos
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña
    spring.jpa.hibernate.ddl-auto=update
    ```

3. **Construir y ejecutar la aplicación:**

    ```bash
    ./mvnw clean install
    ./mvnw spring-boot:run
    ```

## Uso

1. **Buscar libros:**

2. **Consultar libros guardados:**
   
3. **Consultar Libros por idioma:**
   
5. **Autores vivos por año:**   

## Estructura del Proyecto

- **src/main/java:** Código fuente de la aplicación.
  - **com.tu_usuario.buscadorlibros:** Paquete principal de la aplicación.
  - **controller:** Controladores REST.
  - **service:** Servicios de negocio.
  - **repository:** Repositorios JPA.
  - **model:** Clases de modelo de datos.
- **src/main/resources:** Recursos de la aplicación.
  - **application.properties:** Archivo de configuración de Spring Boot.
 - **templates:** Plantillas Thymeleaf.

   **Autor**
   Juliana Andrea Salcedo- Princess Voodoo
