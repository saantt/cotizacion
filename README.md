# Proyecto Cotización

Servicio REST de cotización desarrollado con Spring Boot. Esta aplicación ofrece una API estructurada para gestionar datos de cotización, coberturas, datos de riesgo, deducibles, impuestos, estados y tomadores.

## Descripción general

El proyecto está diseñado para:

- Exponer endpoints REST basados en JSON.
- Proveer documentación OpenAPI/Swagger automáticamente.
- Integrarse con PostgreSQL en producción y con H2 en memoria para pruebas locales.
- Utilizar Spring Boot 4, Spring Data JPA, validación de datos y un diseño modular de servicios.

## Tecnologías principales

- Java 17
- Spring Boot 4.0.7
- Spring Web
- Spring Data JPA
- Spring Validation
- Spring Boot Actuator
- Springdoc OpenAPI
- Lombok
- MapStruct
- PostgreSQL / H2

## Requisitos

- Java 17 instalado
- Maven instalado o uso del wrapper incluido
- PostgreSQL disponible para entorno local/prod

## Estructura del proyecto

- `src/main/java`: código fuente de la aplicación
- `src/main/resources`: configuración y recursos de Spring Boot
- `src/test/java`: pruebas unitarias e integradas
- `pom.xml`: configuración de Maven y dependencias
- `mvnw`, `mvnw.cmd`: wrapper de Maven para ejecución consistente

## Configuración

El archivo principal de configuración es `src/main/resources/application.properties`.

Ejemplo de configuración:

```properties
spring.application.name=cotizacion
server.port=9090
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.api-docs.path=/api-docs
spring.datasource.url=jdbc:postgresql://localhost:5432/mi_db_test
spring.datasource.username=admin
spring.datasource.password=secret
spring.datasource.driver-class-name=org.postgresql.Driver
```

### Parámetros clave

- `server.port`: puerto donde se expone el servicio
- `springdoc.swagger-ui.path`: ruta de acceso a Swagger UI
- `springdoc.api-docs.path`: ruta donde se publica la especificación OpenAPI
- `spring.datasource.url`: URL de conexión a la base de datos
- `spring.datasource.username`: usuario de base de datos
- `spring.datasource.password`: contraseña de base de datos

> Asegúrate de actualizar los valores de conexión de PostgreSQL antes de iniciar la aplicación.

## Ejecución del proyecto

### Con Maven Wrapper en Windows

```powershell
cd c:\Users\mvalencia\Documents\spring-boot\cotizacion
.\mvnw.cmd spring-boot:run
```

### Con Maven Wrapper en Linux/macOS

```bash
cd /ruta/al/proyecto/cotizacion
./mvnw spring-boot:run
```

### Construir y ejecutar JAR

```bash
./mvnw clean package
java -jar target/cotizacion-0.0.1-SNAPSHOT.jar
```

## Pruebas

Ejecuta el conjunto de pruebas con:

```bash
./mvnw test
```

## Documentación de la API

La documentación integrada está disponible en:

- Swagger UI: `http://localhost:9090/swagger-ui.html`
- OpenAPI JSON: `http://localhost:9090/api-docs`

## Endpoints REST

Base URL: `http://localhost:9090`

### Coverage

- `GET /coverage`
  - Lista todas las coberturas.
- `GET /coverage/{id}`
  - Obtiene una cobertura por su identificador.
- `POST /coverage`
  - Crea una nueva cobertura.
- `PUT /coverage/{id}`
  - Actualiza una cobertura existente.
- `DELETE /coverage/{id}`
  - Elimina una cobertura por ID.

### Cobertura Riesgo

- `GET /coberturariesgo`
  - Lista todas las coberturas de riesgo.
- `GET /coberturariesgo/{idCotizacion}/{idCobertura}`
  - Obtiene una cobertura de riesgo específica.
- `POST /coberturariesgo`
  - Crea una nueva cobertura de riesgo.
- `PUT /coberturariesgo/{idCotizacion}/{idCobertura}`
  - Actualiza una cobertura de riesgo.
- `DELETE /coberturariesgo/{idCotizacion}/{idCobertura}`
  - Elimina una cobertura de riesgo determinada.

### Datos Riesgo

- `GET /api/datos-riesgo`
  - Lista todos los datos de riesgo.
- `GET /api/datos-riesgo/{placa}`
  - Obtiene datos de riesgo por placa.
- `POST /api/datos-riesgo`
  - Crea un nuevo registro de datos de riesgo.
- `PUT /api/datos-riesgo/{id}`
  - Actualiza un registro de datos de riesgo.
- `DELETE /api/datos-riesgo/{id}`
  - Elimina un registro de datos de riesgo.

### Estados Cotización

- `GET /api/estados-cotizacion`
  - Lista todos los estados de cotización.
- `GET /api/estados-cotizacion/{id}`
  - Obtiene un estado de cotización por ID.
- `POST /api/estados-cotizacion`
  - Crea un nuevo estado de cotización.
- `PUT /api/estados-cotizacion/{id}`
  - Actualiza un estado de cotización existente.
- `DELETE /api/estados-cotizacion/{id}`
  - Elimina un estado de cotización.

### Impuestos Cotización

- `GET /api/impuestos-cotizacion`
  - Lista todos los impuestos de cotización.
- `GET /api/impuestos-cotizacion/{id}`
  - Obtiene un impuesto por su ID.
- `POST /api/impuestos-cotizacion`
  - Crea un impuesto de cotización.
- `PUT /api/impuestos-cotizacion/{id}`
  - Actualiza un impuesto existente.
- `DELETE /api/impuestos-cotizacion/{id}`
  - Elimina un impuesto por ID.

### Marca Vehículo

- `GET /api/marcavehiculo`
  - Lista todas las marcas de vehículo.
- `GET /api/marcavehiculo/{id}`
  - Obtiene una marca por ID.
- `GET /api/marcavehiculo/nombre/{nombre}`
  - Busca marcas por nombre.
- `GET /api/marcavehiculo/pais/{pais}`
  - Busca marcas por país.
- `GET /api/marcavehiculo/existe/{nombreMarca}`
  - Verifica si existe una marca por nombre.
- `GET /api/marcavehiculo/abreviatura/{abreviatura}`
  - Busca marca por abreviatura.
- `POST /api/marcavehiculo`
  - Crea una nueva marca.
- `PUT /api/marcavehiculo/{id}`
  - Actualiza una marca existente.
- `DELETE /api/marcavehiculo/{id}`
  - Elimina una marca de vehículo.

### Tomadores

- `GET /api/tomadores`
  - Lista todos los tomadores.
- `GET /api/tomadores/{cc}`
  - Obtiene un tomador por cédula.
- `GET /api/tomadores/buscar`
  - Realiza una búsqueda de tomadores.
- `POST /api/tomadores`
  - Crea un nuevo tomador.
- `PUT /api/tomadores/{cc}`
  - Actualiza un tomador existente.
- `DELETE /api/tomadores/{cc}`
  - Elimina un tomador por cédula.

## Notas adicionales

- El proyecto emplea MapStruct y Lombok para simplificar el código de mapeo y reducir boilerplate.
- Recarga la aplicación después de cualquier cambio en el archivo `application.properties`.
- Swagger UI y OpenAPI se exponen automáticamente gracias a `springdoc-openapi-starter-webmvc-ui`.
- Comprueba que el servicio de PostgreSQL esté activo antes de iniciar la aplicación.
