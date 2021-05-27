FROM adoptopenjdk/openjdk11:alpine AS MAVEN_BUILD

MAINTAINER Abhishek Kadam

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package

FROM adoptopenjdk/openjdk11:alpine-slim

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/newtechserver2-0.1.0.jar /app/

ENTRYPOINT ["java", "-jar", "newtechserver2-0.1.0.jar"]