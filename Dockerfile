FROM openjdk:17

ADD target/weltuserapi-0.0.1-SNAPSHOT.jar weltuserapi-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","weltuserapi-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080