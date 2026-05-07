# Deployment Guide

This project is deployed on Render.

Each service is published under a different URI and they communicate through environment variables.

## How to Deploy

1. Open the root folder of the project and run `mvn clean package`.
2. If Maven is not installed, follow the official installation guide: https://maven.apache.org/install.html
3. Copy the generated JAR file from `target/name-0.0.1-SNAPSHOT.jar` to the `dist` folder.
4. Make sure the Dockerfile is configured correctly:

	```dockerfile
	FROM amazoncorretto:17-alpine-jdk
	COPY dist/AppName-0.0.1-SNAPSHOT.jar /app.jar
	EXPOSE 8080
	ENTRYPOINT ["java", "-jar", "/app.jar", "--server.port=8080"]
	```

5. Create the Render services for each application.
6. Add the required environment variables for PeerTube Miner and DailyMotion Miner.
7. Deploy the latest commit.

## Notes

- Each service must use its own URI on Render.
- Environment variables are required for the services that need to communicate with other applications.
- Make sure the exposed port in Docker matches the port configured by each Spring Boot service.