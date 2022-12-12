FROM maven:3.8.6-sapmachine-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:20-ea-17-jdk
WORKDIR /app
COPY --from=build ./app/target/*.jar ./app.jar

ARG MSCREDITCARD_EUREKA_SERVER=localhost
ARG MSCREDITCARD_KEYCLOAK_SERVER=localhost
ARG MSCREDITCARD_KEYCLOAK_PORT=8081

ENTRYPOINT java -jar app.jar