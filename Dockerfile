FROM adoptopenjdk/openjdk11:alpine AS build
MAINTAINER Abhishek Kadam 

RUN apk update && apk upgrade && apk add bash

RUN cd /usr/local/bin && wget https://services.gradle.org/distributions/gradle-5.6-all.zip && \
/usr/bin/unzip gradle-5.6-all.zip && ln -s /usr/local/bin/gradle-5.6/bin/gradle /usr/bin/gradle

RUN mkdir -p /app
COPY . /app
WORKDIR /app


FROM adoptopenjdk/openjdk11:alpine-slim
MAINTAINER Abhishek Kadam 

RUN apk update && apk upgrade && apk add bash

EXPOSE 8080

COPY --from=build /app/build/libs/newtechserver2-0.0.1-SNAPSHOT.jar /app.jar

ENV JAVA_OPTS=""
CMD exec java $JAVA_OPTS -jar /app.jar1.0.jar"]