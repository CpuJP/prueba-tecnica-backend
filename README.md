# Prueba Técnica Backend - API de Películas 🎬

Este proyecto es una **API RESTful** desarrollada en **Java 21** utilizando **Spring Boot 3.5.0**, como parte de una prueba técnica para el rol de **desarrollador backend**. La aplicación permite gestionar un catálogo de películas, ofreciendo operaciones de consulta, registro y validación.

## 🚀 Tecnologías utilizadas

* 🔧 **Java 21**
* ⚙️ **Spring Boot 3.5.0**
* 🛠️ **Spring Data JPA**
* 🧪 **JUnit 5 + Mockito**
* 📄 **Swagger OpenAPI 3**
* 🗄️ **Base de datos en memoria H2**

---

## 📦 Requisitos previos

* Java 21 instalado
* Maven 3.8+ (o usar `./mvnw`)
* Git

---

## 🛠️ Instrucciones para ejecutar el proyecto

1. Clona el repositorio:

```bash
git clone https://github.com/CpuJP/prueba-tecnica-backend
cd prueba-tecnica-backend
```

2. Ejecutá el proyecto:

```bash
./mvnw spring-boot:run
```

O bien desde tu IDE ejecutando la clase `PruebaTecnicaScApplication`.

3. Accedé a Swagger UI para probar los endpoints:

```
http://localhost:8080/swagger-ui.html
```

4. Accedé a la base de datos H2:

```
http://localhost:8080/h2-console
```

* JDBC URL: `jdbc:h2:mem:testdb`
* Usuario: `sa`
* Contraseña: *(vacío)*

---

## 📄 Endpoints principales

* `GET /movie?id={id}` → Obtener película por ID
* `GET /movies?total=10&order=asc` → Listar películas
* `POST /movie` → Agregar una nueva película

---

## 🔐 Validaciones y manejo de errores

El proyecto valida los datos de entrada usando **Jakarta Validation** y devuelve respuestas personalizadas ante errores:

* `400 Bad Request`: campos faltantes o inválidos
* `404 Not Found`: película no encontrada
* `409 Conflict`: película ya existente

Todos los errores se manejan globalmente desde `GlobalExceptionHandler` y se devuelven en un formato estructurado.

---

## 🧪 Pruebas unitarias

Se incluyen pruebas unitarias con **JUnit 5** y **Mockito** sobre la capa de servicios:

* `MovieServiceImplTest` valida los tres métodos principales: obtener por ID, listar y crear películas.

Ejecutá las pruebas con:

```bash
./mvnw test
```

---

## 📚 Documentación OpenAPI (Swagger)

La API está completamente documentada usando **springdoc-openapi**:

* Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
* JSON OpenAPI: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## 👨‍💻 Autor

**Juan Pablo Giraldo Collazos**
📧 [cpujuanpis@gmail.com](mailto:cpujuanpis@gmail.com)
🔗 [github.com/CpuJP](https://github.com/CpuJP)

---

## 📝 Licencia

Este proyecto está licenciado bajo la **Licencia MIT**. Ver [`LICENSE`](LICENSE) para más información.
