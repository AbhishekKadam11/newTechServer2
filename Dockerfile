FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD

MAINTAINER Abhishek Kadam

RUN mkdir -p /app
COPY . /app
WORKDIR /app
COPY pom.xml /app/
COPY src /app/src/

WORKDIR /app/
RUN mvn package

FROM openjdk:8-jre-alpine



COPY --from=MAVEN_BUILD /app/target/newtechserver2-0.1.0-SNAPSHOT.jar /app/target/newtechserver2-0.1.0-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "newtechserver2-0.1.0-SNAPSHOT.jar"]