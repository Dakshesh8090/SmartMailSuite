
FROM maven:3.9.3-eclipse-temurin-17-alpine AS build

WORKDIR /app


COPY . .


RUN mvn clean package -DskipTests



FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app


COPY --from=build /app/target/EmailWriter-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9090


ENTRYPOINT ["java", "-jar", "app.jar"]
