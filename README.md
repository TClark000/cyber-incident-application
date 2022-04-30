# Demo CyberIncident Application

> Replace the Python/Django backend of a full stack web application:
> 
> Python/Django -> Java Spring Boot/ API /Java Persistence API (JPA) 

## Table of contents
* [General Info](#general-info)
* [Architecture Before & After](#architecture-before--after)
* [Technologies](#technologies)
* [Setup](#setup)
* [Upgrade the DB](#upgrade-the-db)
* [Testing](#testing)
* [To-do list](#to-do-list)
* [Status](#status)
* [Inspiration](#inspiration)
* [Contact](#contact)

## General Info
Utilising a project previously created <a href="https://github.com/TClark000/sei-project-4#readme" target="_blank">'React & Django | sei50-project IV'</a>, the goal of this project was to replace the Python/Django backend of a web application with a Java application.

Python/Django -> Java Spring Boot/ API /Java Persistence API (JPA)

The final objective was to render the chloropeth map, the full functionality of the original project 'React & Django | sei50-project IV' was not kept (such as user login).

Additional tasks:
* upgrade the postgres database to the latest version
* containerization with docker and docker compose 
* JUnit 5 testing

## Architecture Before & After

<center><img src="./img/Cyber Incident Application - previous.png" alt="drawing" width="70%" style="border:1px solid gray"/></center>


<center><img src="./img/Cyber Incident Application - new.png" alt="drawing" width="70%" style="border:1px solid gray"/></center>

<center><img src="./img/Docker Stack.png" alt="drawing" width="70%" style="border:1px solid gray"/></center>

List of the running containers:
```bash
IMAGE              COMMAND                              PORTS                    NAMES
postgres:14.2      "docker-entrypoint.sh postgres"      0.0.0.0:5432->5432/tcp   demo-cyberincident-postgres-1
restful-ci:local   "/usr/bin/mvn"                       0.0.0.0:8080->8080/tcp   demo-cyberincident-restful-ci-1
map:local          "docker-entrypoint.sh yarn start"    0.0.0.0:3000->3000/tcp   demo-cyberincident-front-end-1
```

## Technologies
Back End:
* Java 18
* Spring Boot
* Java Persistence API (JPA)
* Maven
* JUnit5
* PostgreSQL 14.2
* H2Database (for self-contained test as an in-memory database)

Tools:
* IntelliJ IDEA
* Spring Initializr
* Postman
* TablePlus
* Docker & Docker Compose

Front End as per <a href="https://github.com/TClark000/sei-project-4#technologies" target="_blank">'React & Django | sei50-project IV'</a>

## Setup

Run the java application locally:
```bash
mvn spring-boot:run
```
Build the java application with docker:
```bash
docker build --target restful-ci --build-arg NODE_ENV=development -t restful-ci:local .
```
Bring up java application, react and postgres:
```bash
docker compose up -d
```
<span style="font-size:0.75em;">(-d detach, indicates if there is a problem a volume)</span>

---
Front End (<a href="https://github.com/TClark000/sei-project-4/tree/base/frontend/" target="_blank">code</a>):

Target the api in setupProxy.js to the new java application:
```javascript
app.use(createProxyMiddleware('/api', { target: 'http://restful-ci:8080' }))
```
Build the React & JavaScript application with docker:
```
docker build --target map --build-arg NODE_ENV=development -t map:local .
```

## Upgrade the DB

postgres (PostgreSQL) 12.10 -> postgres (PostgreSQL) 14.2

Backup the db:
```bash 
pg_dumpall > /backup/backup-demo-cyberincident.sql
```

Restore db:
```bash 
psql -U postgres -d demo-cyberincident < /backup/backup-demo-cyberincident.sql
```

Reset password.

## Testing

Mock self-contained test with an in-memory H2 database:
```bash 
mvn surefire:test 
```
```bash 
mvn test
```

Integration Tests (a profile ’surefire’ for integration tests):
```bash 
mvn verify -Psurefire
```
```bash 
mvn -Dtest=IntegrationTest# test
```

Run Mock and Integration Tests:
```bash 
* mvn -Dtest=IntegrationTest#,Test# test
```

## To-do list:
* Logging

## Status
Project is: _finished_

## Inspiration
Project inspired by... working with Java.

<center><img src="./img/Wave.svg" alt="drawing" width="20%"/></center>

## Contact
Created by <a href="https://github.com/tclark000/" target="_blank">@teresaclark</a>