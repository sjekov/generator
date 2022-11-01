FROM openjdk:11
MAINTAINER offerista.com
COPY target/producer-0.0.1-SNAPSHOT.jar producer-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/producer-0.0.1-SNAPSHOT.jar"]