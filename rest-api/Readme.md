### RestAPI for Ontology Search Application

#PREREQUISITES:
Java8 or Openjdk8 must be installed.
Mongodb must be installed on port 27017

#INSTRUCTIONS:
To run the rest api
1.Naviagte to /target folder
2.In cmd run "java -jar search-0.0.1-SNAPSHOT.jar"
Application will run on port 8888

#REFERENCES:
Swagger documentation: http://localhost:8888/swagger-ui.html

#ENDPOINTS:
/ -> Home page
/api/getOntology/{id} -> To get the Ontology by id.
/api/create -> to create the ontology id. Expects JSON payload.

