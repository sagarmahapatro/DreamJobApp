#!/bin/sh
until curl -sf http://eureka-server:8761/eureka/apps ; do
  echo "Waiting for Eureka..."
  sleep 5
done

until curl -sf http://config-server:8888/actuator/health ; do
  echo "Waiting for Config Server..."
  sleep 5
done

exec java -jar /app/jobapplication-service.jar