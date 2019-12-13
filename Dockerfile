FROM openjdk:11-jdk

ENV MONGODB_HOST mongo
ENV MONGODB_DATABASE quaklog_db
ENV MONGODB_USERNAME quaklog
ENV MONGODB_PASSWORD 123456

VOLUME /tmp
COPY build/quaklog-api.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=prod", "-jar", "/app.jar"]