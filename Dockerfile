FROM maven:3.5.2-jdk-8-alpine

ADD target/newtechserver2-0.1.0-SNAPSHOT.jar newtechserver2-0.1.0-SNAPSHOT.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","newtechserver2-0.1.0-SNAPSHOT.jar"]