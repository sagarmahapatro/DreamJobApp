FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/*.jar jobopening-service.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/app/jobopening-service.jar"]
