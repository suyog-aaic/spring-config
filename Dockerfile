FROM 3.5.2-jdk-17  AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package 

FROM openjdk:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar /app/app.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "app.jar"]