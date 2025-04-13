FROM maven:3.8.5-openjdk-17 AS build
COPY ..
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/E-commerce5-00.1-SNAPSHOT.jar E-commerce5.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","E-commerce5.jar"]