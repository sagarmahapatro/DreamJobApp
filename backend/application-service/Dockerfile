FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/*.jar application-service.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","/app/application-service.jar"]
