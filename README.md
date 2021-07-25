# OntologySearchApp
Project for OntologySearch

## Technologies:
Java8, Spring-Boot, ReactJS, MongoDB, Docker

## INSTRUCTIONS:
- Navigate to rest-api/target folder
- Run `java -jar search-0.0.1-SNAPSHOT.jar`
- Rest API spring boot application will run on port 8888
- Navigate to react-app/ folder
- Run `npm install` and then `npm run`
- React application will run on port 3000

## KNOW MORE:
Installed application in aws EC2 instance. \
Application: (http://ec2-18-116-85-48.us-east-2.compute.amazonaws.com:3000/) \
RestAPI: (http://ec2-18-116-85-48.us-east-2.compute.amazonaws.com:8888/) \
Swagger Documentation: (http://ec2-18-116-85-48.us-east-2.compute.amazonaws.com:8888/swagger-ui.html)

### DOCKER-COMPOSE:
- Created docker images and a docker-compose file for all the servers (Frontend, API, Database(mongo:latest dockerhub image)
- Download docker-compose.yaml from the project
- Open cmd and navigate to docker-compose.yaml location
- With docker client installed, run `docker-compose up`


