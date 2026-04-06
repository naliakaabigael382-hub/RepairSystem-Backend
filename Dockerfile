FROM openjdk:17-jdk-slim
COPY build/libs/RepairSystem-Backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 3087
ENTRYPOINT ["java", "-jar", "app.jar"]