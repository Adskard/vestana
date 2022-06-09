FROM maven:3.8.5-openjdk-8 AS MAVEN_BUILD
WORKDIR /build/
COPY pom.xml .
COPY mvnw .
COPY src ./src/

RUN mvn clean install -Dmaven.test.skip=true -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=WARN -B --no-transfer-progress -e && mvn package  -Dmaven.test.skip=true -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=WARN -B --no-transfer-progress -e

FROM openjdk:8

WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/vestana-1.0-SNAPSHOT.jar /app/vestana.jar
EXPOSE 5000
ENTRYPOINT [  "java",                                     \
              "-Dfile.encoding=UTF-8",                    \
              "-Djava.security.egd=file:/dev/./urandom",  \
              "-jar",                                     \
              "/app/vestana.jar"                        \
           ]