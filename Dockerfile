# Docker multi-stage build

# 1. Building the App with Maven
FROM maven:3-jdk-11

ADD . /github-api-integration
WORKDIR /github-api-integration

# Just echo so we can see, if everything is there :)
RUN ls -l

# Run Maven build
RUN mvn clean install


# Just using the build artifact and then removing the build-container
FROM openjdk:11-jdk

MAINTAINER Evandro Souza

VOLUME /tmp

# Add Spring Boot app.jar to Container
COPY --from=0 "/github-api-integration/back-end/target/back-end-1.0-SNAPSHOT.jar" app.jar

ENV JAVA_OPTS=""

# Fire up our Spring Boot app by default
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]