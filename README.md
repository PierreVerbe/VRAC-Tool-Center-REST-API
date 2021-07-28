# VRAC Tool Center

## About this repository

## Prerequisite

## Technical Stack

## Installation

## Notes
* Todo list [file](resources/docs/todo.md) <br>
* If you have issues look at this [file](resources/docs/issue.md) <br>






docker-compose up (start all 3 containers)
docker ps (see them running)

docker-compose start (restart containers that were previously created, but were stopped. It never creates new containers)
docker-compose stop (stop)





# Stop services only
docker-compose stop

# Stop and remove containers, networks..
docker-compose down 

# Down and remove volumes
docker-compose down --volumes 

# Down and remove images
docker-compose down --rmi <all|local> 


docker exec -it vrac-tool-center_mongo_1 bash
mongo
use test
show dbs
show collections


Docker create a release of rest service 
docker build -t pierreverbe/vrac-tool-center-rest-service:0.1.0-SNAPSHOT .

see images available
docker images

run docker image 
docker run -p 6039:8080 pierreverbe/vrac-tool-center-rest-service:0.1.0-SNAPSHOT

stop docker image with id : 54eb18675981f6a9742b79f626f4ca3bfbeb4ed9565505b5d51ee4d980fcb590
docker container stop 54eb18675981f6a9742b79f626f4ca3bfbeb4ed9565505b5d51ee4d980fcb590 



docker login --username=pierreverbe
password

docker push pierreverbe/vrac-tool-center-rest-service:0.1.1-SNAPSHOT
