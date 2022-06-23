FROM openjdk:8

COPY develop/target/*.jar vestana.jar

EXPOSE 8080
ENTRYPOINT [  "java",                                     \
              "-jar",                                     \
              "/vestana.jar"                        \
           ]