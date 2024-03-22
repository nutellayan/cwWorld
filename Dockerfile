FROM openjdk:latest
COPY ./target/cwWorld-1.-SNAPSHOT-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "cwWorld-1.3-SNAPSHOT-jar-with-dependencies.jar"]