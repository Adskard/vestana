# nss-projekt

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
| N°  | Task                | Details                                     | Status | Author |
|:---:|---------------------|---------------------------------------------|:------:|:------:|
| 01  | Basic entities      | based on the UML class diagram              |  Done  |   AS   |
| 02  | DAO                 | generate DAO                                |        |   MK   |
| 03  | reservation service | start service, for now focus on reservation |        |   MK   |
| 04  | sign in / register  | most likely Basic authorization             |        |        |
| 05  | controllers         | 2-3                                         |        |   AS   |
| 06  | git tasks/use case  | create system for correct commit naming     |  Done  |   AS   |
| 07  | loan service        |                                             |        |   MK   |
| 08  | clothing service    |                                             |  Done  |   MK   |

## TODO Frontend
| N°  | Task                | Details                                     | Status | Author |
|:---:|---------------------|---------------------------------------------|:------:|:------:|
| 01  | Overall look        | overall style and structure of pages        |        |   AŠ   |
| 02  | Auth                | connected auth with basic checks            |        |   AŠ   |
| 03  | Clothing display    | display list of available clothing          |        |   AŠ   |
| 04  | Clothing article    | details of clothing article with reservation|        |   AŠ   |
| 05  | Employee functions  | Reservation overview clothes CRUD           |        |   AŠ   |


## Requirements
<!-- must be displayed per instructions -->

### Mandatory requirements
| Functionality                    | Where       | Completed (%) | Details                                                    |
|----------------------------------|-------------|:-------------:|------------------------------------------------------------|
| Technology/Language selection    | -           |     100%      | Java/SpringBoot                                            |
| Readme.md documentation          | `Readme.md` |      40%      |                                                            |
| Use of DB                        | Backend     |               | PostgreSQL                                                 |
| Deployment on the server         |             |       0%      | Heroku                                                     |
| SW architecture design selection |             |               | Monolith + 3 Tier                                          |
| Initialization instructions      |`Readme.md`  |               | how to deploy, where to find basic data, such as admin psw |
| Use 5 design patterns            |             |               | Must make sense.                                           |
| Two UC per team member           |             |               | non-trivial, 3 members => 6 UC                             |



### Non-mandatory requirements (-2 points if not present)
| Functionality            | Where     | Completed (%) | Details                                        |
|--------------------------|-----------|:-------------:|------------------------------------------------|
| Cache                    | `example` |      0%       | Hazelcast                                      |
| Messaging principle      |           |      0%       | Kafka or JVM                                   |
| Security                 |           |      0%       | Basic or OAuth2                                |
| Interceptor              |           |      0%       | e.i. logging                                   |
| Use one given technology |           |      0%       | SOAP, REST, graphQL, Java RMI, Corba, XML-RPC  |
| ElasticSearch            |           |      0%       |                                                |
|                          |           |               |                                                |

### Bonus requirements (+2 points if not present)
| Functionality | Where | Completed (%) | Details    |
|---------------|-------|:-------------:|------------|
| Cloud service |       |      0%       | azure, aws |

