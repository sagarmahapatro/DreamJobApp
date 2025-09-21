#!/bin/sh
until curl -sf http://eureka-server:8761/eureka/apps ; do
  echo "Waiting for Eureka..."
  sleep 5
done
exec java -jar /app/application-service.jar