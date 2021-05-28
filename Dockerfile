FROM maven:3.5.2-jdk-alpine AS MAVEN_BUILD

MAINTAINER Abhishek Kadam

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package

FROM openjdk:8-jre-alpine

COPY --from=MAVEN_BUILD /build/target/newtechserver2-0.0.1-SNAPSHOT.jar /build/target/newtechserver2-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/build/target/newtechserver2-0.0.1-SNAPSHOT.jar"]