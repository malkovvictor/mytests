FROM adoptopenjdk/openjdk15:alpine
EXPOSE 8080
RUN addgroup -S mytests && adduser -S mytests -G mytests
USER mytests:mytests
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=containertest", "-jar", "/app.jar"]