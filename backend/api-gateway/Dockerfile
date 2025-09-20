FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/*.jar api-gateway.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/api-gateway.jar"]
