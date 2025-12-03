# Stage 1: Build
FROM eclipse-temurin:25-jdk-alpine AS build

WORKDIR /app

# Copiar archivos de Gradle
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Dar permisos de ejecución a gradlew
RUN chmod +x gradlew

# Copiar código fuente
COPY src src

# Compilar la aplicación (sin ejecutar tests para acelerar el build)
RUN ./gradlew build -x test --no-daemon

# Stage 2: Run
FROM eclipse-temurin:25-jre-alpine

WORKDIR /app

# Copiar el JAR compilado desde el stage anterior
COPY --from=build /app/build/libs/*.jar app.jar

# Exponer el puerto 8080
EXPOSE 8080

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
