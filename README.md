# nss-projekt

[FULL PROJECT ANALYSIS](https://docs.google.com/document/d/1iwqCdzNI0flSrte5WLlODTj-EJvJFIHnS9dFIRaR8gM/edit?usp=sharing) with diagrams and other goodies.

[heroku](https://nss-semestral.herokuapp.com/) - unfortunately, during last minute checks i noticed the heroku app
fetching data from localhost and not from itself!

Team members:
- Michal Kalista (kalismic)
- Anna Skalická (skalian1)
- Adam Škarda (skardada)

## Projekt Vestana - Dress Rental Service

The main goal of this project is to create an app for 
managing dress rental service. This will include Spring
Boot application and website presentation.

## Git project guidelines
- never commit into the main branch
- create branches for one or multiple tasks (if their purpose is somewhat similar)
- commit messages are compulsory and must start with the task number, 
  e.i. 01-user entities added
- try to make at least three commits per task

## TODO Backend
| N°  | Task                   | Details                                     | Status | Author |
|:---:|------------------------|---------------------------------------------|:------:|:------:|
| 01  | Basic entities         | based on the UML class diagram              |  Done  |   AS   |
| 02  | DAO                    | generate DAO                                |  Done  |   MK   |
| 03  | reservation service    | start service, for now focus on reservation |  Done  |   MK   |
| 04  | sign in / register     | most likely Basic authorization             |  Done  |   MK   |
| 05  | controllers            | 2-3                                         |  Done  |   AS   |
| 06  | git tasks/use case     | create system for correct commit naming     |  Done  |   AS   |
| 07  | loan service           |                                             |  Done  |   MK   |
| 08  | clothing service       |                                             |  Done  |   MK   |
| 09  | reservation controller |                                             |  Done  |   MK   |
| 10  | interceptor            | create interceptor for logging requests     |  Done  |   AS   |
| 11  | runner                 | app initialization                          |  Done  |   MK   |
| 12  | loan controller        |                                          | - |  MK   |

## TODO Frontend
| N°  | Task                | Details                                     | Status | Author |
|:---:|---------------------|---------------------------------------------|:------:|:------:|
| 01  | Overall look        | overall style and structure of pages        |Done    |   AŠ   |
| 02  | Auth                | connected auth with basic checks            |Done    |   AŠ   |
| 03  | Clothing display    | display list of available clothing          |Done    |   AŠ   |
| 04  | Clothing article    | details of clothing article with reservation|Done    |   AŠ   |
| 05  | Employee functions  | Reservation clothes CRUD               |90% no create|   AŠ   |
| 06  | Reservation         | Reservations                                |60% no delete|   AŠ   |
| 07  | Loan                 | Loans                                      |  -  |   AŠ   |


## Requirements
<!-- must be displayed per instructions -->

### Mandatory requirements
| Functionality                    | Where       | Completed (%) | Details                                                    |
|----------------------------------|-------------|:-------------:|------------------------------------------------------------|
| Technology/Language selection    | Analysis     |     100%      | Java/SpringBoot + JS/React + PostgreSQL                    |
| Readme.md documentation          | here        |     100%      |                                                            |
| Use of DB                        | Backend     |      100%     | PostgreSQL                                                 |
| Deployment on the server         |[deployment](# Deployment) |     100%     | Heroku  deploy                                |
| SW architecture design selection | here        |     100%   | Monolith + 3 Tier (frontend, backend, db)                     |
| Initialization instructions      |[deployment](# Deployment)  |50%  | how to deploy, where to find basic data, such as admin psw |
| Use 5 design patterns            | [patterns](# Patterns)  |   99%  | Must make sense.                                           |
| Two UC per team member           | [use-case](#Use-case)  |   80%    | non-trivial, 3 members => 6 UC                             |



### Non-mandatory requirements (-2 points if not present)
| Functionality            | Where     | Completed (%) | Details                                        |
|--------------------------|-----------|:-------------:|------------------------------------------------|
| Cache                    |NOT IMPLEMENTED|      0%       | Hazelcast                                      |
| Messaging principle      |NOT IMPLEMENTED|      0%       | Kafka or JVM                                   |
| Security                 |JWT token  |     100%      | Basic or OAuth2                                |
| Interceptor              | Logging   |     100%      | e.i. logging                                   |
| Use one given technology | Controller - REST |      100%       | SOAP, REST, graphQL, Java RMI, Corba, XML-RPC  |
| ElasticSearch            |NOT IMPLEMENTED|      0%       |                                                |

### Bonus requirements (+2 points if present)
| Functionality | Where | Completed (%) | Details    |
|---------------|-------|:-------------:|------------|
| Cloud service | NOT IMPLEMENTED |      0%       | azure, aws |


### Deployment

Admin and sample data is loaded through cz.cvut.fel.vestana.SampleDataLoader during Main startup.

To run docker-compose from root:
  1. first build app using "mvn -Pdev clean install package" - build jar with dependecies
  1. then run "docker-compose -f docker-compose.dev.yml up"

Can be deployed to Heroku with [Herocu CLI and git]("https://devcenter.heroku.com/articles/git").
Steps:
  1. login to heroku "heroku login"
  1. add remote heroku repo as "heroku git:remote -a example-app"
  1. add buildpack heroku/java
  1. add postgreSQL DB and configure env variables for DB (datasource url, username, password, driver, ...)
  1. push project to heroku to deploy "git push heroku main"

References:
 - [Spring boot and heroku](https://devcenter.heroku.com/articles/deploying-spring-boot-apps-to-heroku)
 - [Heroku maven plugin](https://devcenter.heroku.com/articles/deploying-java-applications-with-the-heroku-maven-plugin)

### Patterns

Used patterns:
  - Data access object DAO - cz.cvut.fel.vestana.repo/
  - Chain of responsibility - Controller -> service -> DAO
  - Builder - cz.cvut.fel.vestana.SampleDataLoader
  - Interceptor - cz.cvut.fel.vestana.log
  - ???

### Use-case

backend:
  - Loans
  - Reservation
  - Authorization
  - User
  - Clothes
  - ???
frontend:
  - Reservation - create and view (no delte unfortunetaly)
  - Authorization
  - Clothes - CRUD withou create so just RUD


