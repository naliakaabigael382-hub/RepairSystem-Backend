# Use public Docker Hub images without auth issues
FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /app

# Copy Maven wrapper if you have it
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Make mvnw executable
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline -B

# Copy source and build
COPY src ./src
RUN ./mvnw clean package -DskipTests -B

# Runtime stage
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Create receipts directory
RUN mkdir -p src/main/resources/receipts

# Copy the JAR
COPY --from=build /app/target/*.jar app.jar

EXPOSE 3087

ENTRYPOINT ["java", "-jar", "app.jar"]