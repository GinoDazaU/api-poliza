FROM openjdk:17-jdk-alpine

WORKDIR /app

# Se debe compilar primero con el comando desde el directorio raiz(este): ./mvn clean package
COPY target/api-poliza-0.0.1-SNAPSHOT.jar /app/apipoliza.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/apipoliza.jar"]
