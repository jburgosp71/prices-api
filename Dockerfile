FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY build/libs/*.jar app.jar

EXPOSE 8081
ENV SERVER_PORT=8081

ENTRYPOINT ["java", "-jar", "app.jar"]
