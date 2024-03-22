FROM openjdk:latest
COPY ./target/cwWorld.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "cwWorld.jar","localhost:3306", "30000"]