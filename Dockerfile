FROM eclipse-temurin:17-dj-jammy

ARG JAR_FILE=build/libs/ecs-pratice-starter-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]

EXPOSE 8080