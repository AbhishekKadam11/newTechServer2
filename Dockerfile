FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD

MAINTAINER Abhishek Kadam

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package

EXPOSE 8080

FROM openjdk:8-jre-alpine

COPY --from=MAVEN_BUILD /build/target/newtechserver2-0.0.1-SNAPSHOT.jar /build/target/newtechserver2-0.0.1-SNAPSHOT.jar

ENV JAVA_OPTS=""
CMD exec java $JAVA_OPTS -jar /build/target/newtechserver2-0.0.1-SNAPSHOT.jar