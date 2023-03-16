# Build stage
FROM maven:3.6.3-jdk-8-slim AS build
RUN mvn clean package
ARG JAR=target/*.jar

# Run Stage
FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
