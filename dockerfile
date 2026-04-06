# Build stage - using proven working Java 17 (most stable for Spring Boot)
FROM maven:3.8.6-openjdk-17 AS build

WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Run stage - using proven working Java 17 runtime
FROM openjdk:17-jdk-slim

WORKDIR /app

# Create receipts directory
RUN mkdir -p src/main/resources/receipts

# Copy the built JAR file
COPY --from=build /app/target/*.jar app.jar

# Expose port
EXPOSE 3087

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]