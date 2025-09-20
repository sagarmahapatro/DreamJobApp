FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/*.jar employer-service.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app/employer-service.jar"]
