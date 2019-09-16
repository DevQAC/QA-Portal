FROM maven:3.6.1-jdk-8-slim as build
ARG PROJECT
WORKDIR /build
COPY . .
RUN mvn clean package --projects api-common,${PROJECT}
FROM openjdk:8-alpine
ARG PORT
ARG PROJECT
WORKDIR /opt/${PROJECT}
COPY --from=build /build/${PROJECT}/target/${PROJECT}-*.jar app.jar
EXPOSE ${PORT}
ENTRYPOINT ["/usr/bin/java", "-Dspring.datasource.url=jdbc:postgresql://${POSTGRES_HOST}:5432/qa-portal", "-jar", "./app.jar"]
