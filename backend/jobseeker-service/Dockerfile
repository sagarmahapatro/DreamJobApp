FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/*.jar jobseeker-service.jar
# Install curl
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*
EXPOSE 8083
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]
