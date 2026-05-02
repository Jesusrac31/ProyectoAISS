FROM amazoncorretto:17-alpine-jdk
COPY dist/peertubeminer-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar", "--server.port=8080"]