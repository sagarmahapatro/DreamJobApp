./gradlew bootJar

gradlew.bat clean
gradlew.bat build

docker-compose build
docker-compose up 

docker ps -a -f status=exited

docker-compose down

git bash only docker rmi -f $(docker images -q)

docker system prune -a

https://github.com/sagarmahapatro/DreamJobApp.git

https://github.com/sagarmahapatro/dreamjob-config-repo.git


http://localhost:8080/actuator/gateway/routes