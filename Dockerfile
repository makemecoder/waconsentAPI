# Build stage
FROM maven:3.6.3-jdk-8-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean package


# Run Stage
FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
CMD ["java","-jar","app.jar"]
