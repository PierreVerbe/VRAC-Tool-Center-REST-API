# Release

## Docker commands
* See containers available
```bash
docker images
```
* See containers that are running
```bash
docker ps
```
* Stop docker container with id
```bash
docker container stop <id> 
```

## Docker Compose commands
* Start all containers in docker-compose file
```bash
docker-compose up
```
* Stop and remove all containers
```bash
docker-compose down
```
* Restart all containers that were previously created
```bash
docker-compose start
```
* Stop all containers
```bash
docker-compose stop
```

## Create release
### REST service
* 1 Build an image from a Dockerfile
```bash
docker build -t pierreverbe/vrac-tool-center-rest-service:x.x.x-SNAPSHOT .
```
* 1 bis (Optional) Run docker image built
```bash
docker run -p 6039:8080 pierreverbe/vrac-tool-center-rest-service:x.x.x-SNAPSHOT
```
* 2 Add Docker Hub credentials
```bash
docker login --username=pierreverbe
password
```
* 3 Push an image to Docker Hub
```bash
docker push pierreverbe/vrac-tool-center-rest-service:x.x.x-SNAPSHOT
```
* 4 Update docker-compose file to get new release

## UI
