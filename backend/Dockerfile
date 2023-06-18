FROM maven:3.9.2-eclipse-temurin-17-alpine AS builder
ADD ./pom.xml pom.xml
ADD ./src src/
RUN mvn clean package

From eclipse-temurin:17-jre-alpine
COPY --from=builder target/backend-1.0-SNAPSHOT.jar backend-1.0-SNAPSHOT.jar
CMD ["java", "-jar", "backend-1.0-SNAPSHOT.jar"]