# spring-elk-example

The purpose of this project is to demonstrate how to aggregate and monitor Spring application logs with the ELK stack.

## Tech Stack

Spring Web, Spring Core, Filebeat, Logstash, Elasticsearch, Kibana, Lombok, Gradle

## Description

The project contains a simple microservice (`user-service`) whose logs are stored in the `user-service/logs` directory. The directory is monitored by __Filebeat__ and the logs are sent to __Logstash__ where they are split into fields and sent to __Elasticsearch__.
__Kibana__ is used for data analysis.  
The __user-service__ exposes REST methods for managing the built-in collection of users.
Each method will generate additional logs.

The whole environment is dockerized, and [docker-compose.yml](docker-compose.yml) can be used for building and starting the containers.

The _user-service_ image can be built via the Gradle task `dockerBuild` (`$ ./gradlew clean dockerBuild`).1