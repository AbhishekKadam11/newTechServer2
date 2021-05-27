FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD

MAINTAINER Abhishek Kadam


RUN mvn package

FROM openjdk:8-jre-alpine



COPY /target/newtechserver2-0.1.0-SNAPSHOT.jar newtechserver2-0.1.0-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "newtechserver2-0.1.0-SNAPSHOT.jar"]