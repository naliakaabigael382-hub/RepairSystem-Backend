FROM openjdk:17-jdk-slim
COPY build/libs/*.jar app.jar
EXPOSE 3087
ENTRYPOINT ["java", "-jar", "app.jar"]