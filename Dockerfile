FROM openjdk:latest
COPY ./target/cwWorld-1.2-SNAPSHOT-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "cwWorld-1.2-SNAPSHOT-jar-with-dependencies.jar"]