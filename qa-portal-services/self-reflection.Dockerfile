FROM maven:3.6.1-jdk-8-slim as build
ARG PROJECT
WORKDIR /build
COPY . .
RUN mvn clean package --projects api-common,self-reflection
FROM openjdk:8-alpine
WORKDIR /opt/self-reflection
COPY --from=build /build/self-reflection/target/self-reflection-*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["/usr/bin/java", "-Dspring.datasource.url=jdbc:postgresql://postgres:5432/qa-portal", "-jar", "./app.jar"]
