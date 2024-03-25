FROM openjdk:17-alpine

RUN mkdir /home/app

COPY target/*0.0.1-SNAPSHOT.jar /home/app/app.jar

WORKDIR /home/app

ENTRYPOINT exec java -jar /home/app/app.jar