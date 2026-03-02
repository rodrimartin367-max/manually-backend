# Fase 1: Construcción
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
# Forzamos a saltar los tests para evitar errores de conexión a DB durante el build
RUN mvn clean package -DskipTests

# Fase 2: Ejecución
FROM openjdk:17-jdk-slim
WORKDIR /app
# Copiamos el archivo .jar generado (buscando cualquier nombre en la carpeta target)
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
# Comando de arranque
ENTRYPOINT ["java", "-jar", "app.jar"]