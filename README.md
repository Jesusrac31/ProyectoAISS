# AISS Project

Integration project built around three independent miner services and a gateway that exposes them through a single entry point.

## Overview

This repository contains the following services:

- Video Miner
- DailyMotion Miner
- PeerTube Miner

Each service can be executed locally, deployed independently, and consumed through the shared gateway.

## Documentation

Swagger UI path: `/swagger-ui/index.html`

## Services

### Deployed in Render

| Service | URL |
| --- | --- |
| Video Miner | https://videominer-cdfi.onrender.com |
| DailyMotion Miner | https://dailymotionminer-j78v.onrender.com |
| PeerTube Miner | https://peertubeminer-1r1n.onrender.com |

### Local endpoints

| Service | Local URL |
| --- | --- |
| Video Miner | http://localhost:8080 |
| DailyMotion Miner | http://localhost:8081 |
| PeerTube Miner | http://localhost:8082 |

## Gateway

The gateway provides a single public access point for the whole system:

- https://proyectoaiss-042e.onrender.com

## Deployment Platform

All services are deployed on Render.

## Deployment Guide

For the full deployment procedure, see [Deploy.md](Deploy.md).

The deployment flow is summarized as follows:

1. Build the project with Maven.
2. Copy the generated JAR to the distribution folder used by the Docker image.
3. Use the provided Dockerfile configuration.
4. Create the Render services.
5. Configure the required environment variables for PeerTube Miner and DailyMotion Miner.
6. Deploy the latest commit.

## Postman tests

For testing, you must dowload the test and the enviroments from the [Postman](Postman) folder. Each enviroment test the proyect in some different deployment:

| Enviroment | Function |
| --- | --- |
| LocalAISSProject | Test the project deployed in your PC using different ports. |
| CloudAISSProject | Test the project deployed in the cloud, using for each project its URI. |
| GatewayAISSProject | Test the project deployed in the cloud, using the gateway for accessing each project. |

We had to change some of the tests and add new tests for Postman. Here are some of these changes:

- We added enviroments that must be used in order to select whether you are testing your local version, the one deployed or using the gateway.
- The data model used the name "link" for caption name. However, tests uses "name" instead in the creation. We updated that name.
- In the tests, the one named "Get all captions" expected to get 8 captions while just 7 are posted. We changed that number.
- We added all tests related to import channels from PeerTube and DailyMotion.
- We added the tests for CRUD operations. There were only tests for getters.
- We added some test that are supposed to return errors in order to see whether our Global Exception Handler works.

Remember that tests only works if the database is cleared, otherwise it is possible to receive some unexpected 409 status codes.

## Additional Notes

- The project follows a modular architecture with one Spring Boot application per miner.
- The gateway centralizes access to the individual services.
- Local ports must remain distinct to avoid conflicts when running multiple services at the same time.
- The cloud URLs are useful for validating the deployed version without requiring a local setup.
