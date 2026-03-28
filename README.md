Transport Orders API

Tecnologías utilizadas
Java 17
Spring Boot
Spring Data JPA
PostgreSQL
Docker
Swagger


Cómo ejecutar el proyecto
1. Clonar repositorio
git clone https://github.com/TapiaCalixAlejandro/transport.git
cd transport-api
2. Construir proyecto
mvn clean package -DskipTests
3. Ejecutar con Docker
docker-compose up --build

Accesos
API: http://localhost:8001
Swagger UI: http://localhost:8001/swagger-ui.html

Funcionalidades
Ordenes
Crear orden
Consultar orden por ID
Listar órdenes con filtros
Cambiar estado (con validación de flujo)
Asignar conductor a una orden
Subida de archivos (PDF e imagen)

Conductores
Crear conductor
Listar conductores activos
Manejo de archivos

Los archivos se almacenan en un volumen Docker:
/uploads

Se incluyen pruebas unitarias básicas con JUnit y Mockito.

Ejemplos de uso
Crear orden
POST /orders
{
"origin": "CDMX",
"destination": "Puebla"
}

Crear conductor
POST /drivers
{
"name": "Juan Perez",
"licenseNumber": "ABC123",
"active": true
}