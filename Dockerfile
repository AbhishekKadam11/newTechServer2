FROM openjdk:8-jdk-alpine

MAINTAINER Abhishek Kadam

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package

FROM openjdk:8-jre-alpine

WORKDIR /app

COPY /build/target/newtechserver2-0.1.0.jar /app/

ENTRYPOINT ["java", "-jar", "newtechserver2-0.1.0.jar"]