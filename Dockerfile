FROM openjdk:latest
COPY ./target/cwWorld.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "cwWorld.jar","db:33060", "30000"]