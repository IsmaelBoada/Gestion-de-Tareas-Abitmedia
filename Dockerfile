# Usamos Java 21
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Copiamos el JAR construido
COPY target/abitmedia-0.0.3.jar app.jar

# Exponemos el puerto
EXPOSE 9025

# Comando para ejecutar la app
ENTRYPOINT ["java","-jar","app.jar"]
