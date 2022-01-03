###### Phone rest app

a Spring Boot RESTful Web Service application

Code developed using Intellij and built using maven

Code build using maven :

```
mvn clean package
```
target folder is added to project containg "restapp-0.0.1-SNAPSHOT.jar" 

Run jar using :
```
java -jar restapp-0.0.1-SNAPSHOT.jar
```

###### Actuator

Check Actuator endpoint at : http://localhost/actuator/health

###### Swagger UI

Swagger used to document api at 
http://localhost/swagger-ui/#/user-controller

###### Postman

Import "Phone app.postman_collection.json"
test the api by running individual requests

###### Database

Using H2 database which is recreated with test data
during application start. can be accessed when the application is running
http://localhost/h2-console/ 
user:sa
password:password


######  Logging
log files are located in the logs folder 
app.log - contains general application logs
requests.log - log requests