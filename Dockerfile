FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

LABEL authors="tapia"

COPY ./target/transport-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]