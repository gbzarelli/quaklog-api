FROM openjdk:8-jdk-alpine
ENV MONGODB_HOST mongo
VOLUME /tmp
COPY build/quaklog-api.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=prod", "-jar", "/app.jar"]