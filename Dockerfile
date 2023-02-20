FROM maven:3.8-eclipse-temurin-17-alpine AS build
ENV TZ="Asia/Jakarta"

WORKDIR /app
#copy pom
COPY pom.xml .
#copy source
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
#ARG JAR_FILE=target/*.jar
WORKDIR /app

COPY --from=build app/target/*.jar Auth-0.0.1-SNAPSHOT.jar
# Run the web service on container startup.
EXPOSE 9090
ENTRYPOINT ["java","-jar","Auth-0.0.1-SNAPSHOT.jar","-Dspring.config.location=file:application.properties"]