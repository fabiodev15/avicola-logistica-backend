# 🐔 Avícola Logística Backend

Sistema de gestión logística para Avícola Vega - Proyecto de Tesis 8vo Ciclo

## 📋 Descripción

Backend desarrollado con Spring Boot para la gestión de logística de una empresa avícola, incluyendo autenticación JWT, gestión de datos con JPA/Hibernate y base de datos MySQL.

## 🛠️ Tecnologías

- **Java 25**
- **Spring Boot 4.0.0**
- **Spring Security** con JWT
- **Spring Data JPA**
- **MySQL 8.0**
- **Lombok**
- **Gradle**
- **Docker & Docker Compose**

## 🚀 Inicio Rápido con Docker (Recomendado)

### Requisitos Previos

- [Docker](https://www.docker.com/get-started) instalado
- [Docker Compose](https://docs.docker.com/compose/install/) instalado

### Ejecutar el Proyecto

1. **Clonar el repositorio** (si aún no lo has hecho):
   ```bash
   git clone <url-del-repositorio>
   cd avicola-logistica-backend/demo
   ```

2. **Iniciar todos los servicios** (aplicación + base de datos):
   ```bash
   docker-compose up
   ```

   O en segundo plano:
   ```bash
   docker-compose up -d
   ```

3. **Acceder a la aplicación**:
   - API: http://localhost:8080
   - Swagger UI: http://localhost:8080/swagger-ui.html (si está configurado)

### Comandos Útiles de Docker

```bash
# Ver logs de la aplicación
docker-compose logs -f app

# Ver logs de MySQL
docker-compose logs -f mysql

# Detener todos los servicios
docker-compose down

# Detener y eliminar volúmenes (¡cuidado! elimina datos de la BD)
docker-compose down -v

# Reconstruir la imagen después de cambios en el código
docker-compose up --build

# Acceder al contenedor de la aplicación
docker exec -it avicola-backend sh

# Acceder a MySQL
docker exec -it avicola-mysql mysql -uroot -pavicola123 avicola_db
```

## 💻 Desarrollo Local (Sin Docker)

### Requisitos Previos

- Java 25 JDK instalado
- MySQL 8.0 instalado y ejecutándose
- Gradle (o usar el wrapper incluido)

### Configuración

1. **Crear la base de datos**:
   ```sql
   CREATE DATABASE avicola_db;
   ```

2. **Configurar credenciales** en `src/main/resources/application.properties`:
   ```properties
   spring.datasource.password=TU_PASSWORD_AQUI
   ```

3. **Ejecutar la aplicación**:
   ```bash
   # En Windows
   .\gradlew bootRun

   # En Linux/Mac
   ./gradlew bootRun
   ```

## 🗂️ Estructura del Proyecto

```
demo/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/gestion/logistica/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── build.gradle
├── Dockerfile
├── docker-compose.yml
└── .dockerignore
```

## 🔧 Configuración

### Variables de Entorno (Docker)

El archivo `docker-compose.yml` configura automáticamente estas variables:

- `SPRING_DATASOURCE_URL`: URL de conexión a MySQL
- `SPRING_DATASOURCE_USERNAME`: Usuario de la base de datos (default: `root`)
- `SPRING_DATASOURCE_PASSWORD`: Contraseña de la base de datos (default: `avicola123`)

### Credenciales por Defecto

**MySQL (Docker):**
- Host: `localhost`
- Puerto: `3306`
- Base de datos: `avicola_db`
- Usuario: `root`
- Contraseña: `avicola123`

> ⚠️ **Importante**: Cambia estas credenciales en producción editando el archivo `docker-compose.yml`

## 🐛 Solución de Problemas

### El contenedor de la aplicación no inicia

1. Verifica los logs:
   ```bash
   docker-compose logs app
   ```

2. Asegúrate de que MySQL esté saludable:
   ```bash
   docker-compose ps
   ```

### Error de conexión a la base de datos

- Espera unos segundos más, MySQL puede tardar en inicializarse completamente
- Verifica que el healthcheck de MySQL esté pasando: `docker-compose ps`

### Puerto 8080 o 3306 ya en uso

Cambia los puertos en `docker-compose.yml`:
```yaml
ports:
  - "8081:8080"  # Para la aplicación
  - "3307:3306"  # Para MySQL
```

### Cambios en el código no se reflejan

Reconstruye la imagen:
```bash
docker-compose up --build
```

## 📝 Notas Adicionales

- Los datos de MySQL se persisten en un volumen Docker (`mysql_data`)
- La aplicación usa `spring.jpa.hibernate.ddl-auto=update` para crear/actualizar tablas automáticamente
- El proyecto incluye Spring Boot DevTools para desarrollo local

## 👥 Contribuir

Este es un proyecto de tesis. Para contribuir, contacta al equipo de desarrollo.

## 📄 Licencia

Proyecto académico - Avícola Vega
