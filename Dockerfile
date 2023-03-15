# Build stage
# FROM maven:3.6.3-jdk-8-slim AS build
# WORKDIR usr/src/app
# COPY . ./
# RUN mvn clean package

# Package stage
# FROM openjdk:8-jdk-alpine
# EXPOSE 8080
# ARG JAR_NAME="consentAPI"
# WORKDIR /usr/src/app
# COPY --from=build /usr/src/app/target/${JAR_NAME}.jar ./app.jar
# CMD ["java","-jar", "./app.jar"]

FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/consentAPI.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
