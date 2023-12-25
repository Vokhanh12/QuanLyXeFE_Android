FROM openjdk:11-jre-slim

WORKDIR /app

COPY build/libs/*.jar app.jar

CMD ["java", "-jar", "app.jar"]
