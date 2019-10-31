FROM maven:3.6.1-jdk-8-slim as build
ARG PROJECT
WORKDIR /build
COPY . .
RUN mvn clean package --projects api-common,${PROJECT}

FROM openjdk:8-alpine
ARG PROJECT
ENV ENABLE_ONEDRIVE=true
ENV ENABLE_PUBLIC_ENDPOINTS=false

WORKDIR /opt/${PROJECT}
COPY --from=build /build/${PROJECT}/target/${PROJECT}-*.jar app.jar
RUN mkdir -p target/classes
COPY --from=build /build/${PROJECT}/target/classes target/classes
EXPOSE 8080

ENTRYPOINT [ \
    "/usr/bin/java", \
    "-Donedrive.isActive=${ENABLE_ONEDRIVE}", \
    "-Dcv.enable_public_endpoints=${ENABLE_PUBLIC_ENDPOINTS}", \
    "-Dserver.port=8080", \
    "-Dspring.datasource.url=jdbc:postgresql://${POSTGRES_HOST}:5432/qa-portal", \
    "-jar", \
    "./app.jar" \
]

