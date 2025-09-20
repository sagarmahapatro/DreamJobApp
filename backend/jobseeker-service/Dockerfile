FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/*.jar jobseeker-service.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","/app/jobseeker-service.jar"]
