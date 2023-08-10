FROM maven:3.6.3-jdk-11 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package -DskipTests
FROM openjdk:11-jre
WORKDIR /app
COPY --from=build /app/target/*.jar /app/app.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "app.jar"]