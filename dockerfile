# Multi-stage build for Spring Boot
FROM maven:3.9.8-eclipse-temurin-21 AS build

WORKDIR /app

# Copy Maven files
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:21-jdk-slim

WORKDIR /app

# Copy the built JAR file
COPY --from=build /app/target/*.jar app.jar

# Create receipts directory
RUN mkdir -p src/main/resources/receipts

# Expose port
EXPOSE 3087

# Run the application
ENTRYPOINT ["java", "-Xmx256m", "-jar", "app.jar"]