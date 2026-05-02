We will deploy our apps using render.

We will deploy each app in some different URI and then, make the communication using enviroment variables.

= How to deploy

1. Access to the main folder of the project and execute: `mvn clean package`. If the module is not found, install maven: https://maven.apache.org/install.html
2. Move your file from target/name-0.0.1-SNAPSHOT.jar to the folder dist.
3. Check you have your dockerfile:
FROM amazoncorretto:17-alpine-jdk
COPY dist/AppName-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar", "--server.port=8080"]