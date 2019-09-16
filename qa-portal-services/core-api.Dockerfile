FROM maven:3.6.1-jdk-8-slim as build
ARG PROJECT
WORKDIR /build
COPY . .
RUN mvn clean package --projects api-common,core-api
FROM openjdk:8-alpine
WORKDIR /opt/core-api
COPY --from=build /build/${PROJECT}/target/core-api-*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["/usr/bin/java", "-Dspring.datasource.url=jdbc:postgresql://postgres:5432/qa-portal", "-jar", "./app.jar"]
