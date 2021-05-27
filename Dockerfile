FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD

MAINTAINER Abhishek Kadam

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package

FROM openjdk:8-jre-alpine

WORKDIR /app
ls -lsa
COPY --from=MAVEN_BUILD /build/target/newtechserver2-0.1.0-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "-jar", "newtechserver2-0.1.0-SNAPSHOT.jar"]