FROM openjdk:17-jdk
COPY target/prueba_tecnica-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]