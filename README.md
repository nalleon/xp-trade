<div align="justify">

# XP Trade

<div align="center">
    <img src="./img/cover-logo.png">
</div>

## Índice
- [Anteproyecto.](./preproject)
  - [Objetivo.](#objetivo)
  - [Descripción del problema.](#descripción-del-problema)
  - [Arquitectura y tecnologías a utilizar.](#tecnologías-utilizadas)
- [Diagrama de Casos de Uso](#diagrama-de-casos-de-uso)
- [Diagrama Entidad/Relación](#diagrama-entidadrelación)
- [Diagrama de Clases](#diagrama-de-clases)
- [Diagrama de Paquetes](#diagrama-de-paquetes)


***

<br>

## Anteproyecto
### Objetivo.

El objetivo del proyecto es la creación de una API en Java que será consumida por un cliente web de React y un cliente
de aplicación móvil como React Native.

### Descripción del problema.

El sistema debe de permitir la creación de publicaciones relacionadas con los juegos así como comentarios en estas.
Además, un usuario puede añadir juegos a su colección para llevar un registro así como sus juegos favoritos.

Por parte de los usuarios administradores, en ellos recaerá la parte de moderación tanto de las publicaciones como de los comentarios.

### Tecnologías utilizadas.

Estas son las tecnologías que utilizaremos a lo largo del desarrollar del proyecto:

- **Gestión de Dependencias:**
    - [Maven](https://www.mysql.com/) para la gestión de dependencias y compilación del proyecto.

- **Bases de Datos y ORM:**
    - [MySQL](https://www.sqlite.org/index.html) como base de datos relacional.
    - [Hibernate/JPA](https://hibernate.org/) como ORM para la gestión de entidades relacionales.

- **Frameworks:**
    - [Spring Boot](https://spring.io/projects/spring-boot) como framework principal para el desarrollo de la aplicación del lado del server.
    - [Spring Data JPA](https://spring.io/projects/spring-data-jpa) para la interacción con bases de datos relacionales.
    - [Swagger](https://swagger.io/) para las pruebas de los endpoints de la API.
    - [SoapUI](https://www.soapui.org/) para las pruebas de los servicios SOAP.
    - [React](https://es.react.dev/) para el cliente web de la aplicación.
    - [React Native](https://reactnative.dev/) para el cliente de aplicación móvil.

- **Securización:**
    - [Spring Security](https://spring.io/projects/spring-security): como framework para la gestión de autenticación y autorización.
    - [JSON Web Tokens (JWT)](https://jwt.io/): para la creación y validación de tokens seguros para la autenticación de usuarios.
    - [Spring Security Test](https://docs.spring.io/spring-security/reference/testing/overview.html): para facilitar la creación de pruebas relacionadas con la seguridad.

- **Documentación:**
    - [Markdown](https://daringfireball.net/projects/markdown/) para la creación de documentos estructurados y legibles.
    - [Swagger UI](https://swagger.io/tools/swagger-ui/) como interfaz gráfica para la documentación de la API rest.
    - [SoapUI](https://www.soapui.org/) para la documentación de los servicios SOAP.

- **Despliegue:**
    - [Docker](https://www.docker.com/) para la creación de contenedores y despliegue del proyecto en diferentes entornos.

***

<br>

## Diagrama de Casos de Uso

Este ha sido el diagrama de casos de uso que hemos diseñado para nuestra aplicación:


<div align="center">
    <img src="./img/diagrama-cu.drawio.png">
</div>

## Diagrama Entidad/Relación

Tras analizar nuestro objetivo hemos diseñado este diagrama para la definición de la base de datos:

<div align="center">
    <img src="./img/diagrama-er.png">
</div>


## Diagrama de Clases

Hemos propuesto la siguiente implementación programática de las entidades planteadas:

<div align="center">
    <img src="./img/diagrama-clases.png">
</div>


## Diagrama de Paquetes

Esta será la estructura de paquetes que utilizaremos durante el desarrollo del proyecto:

<div align="center">
<img src="./img/diagrama-pkg.drawio.png">
</div>

</div>
