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
| Video Miner | https://videominer.onrender.com |
| DailyMotion Miner | https://dailymotion-miner.onrender.com |
| PeerTube Miner | https://peertube-miner.onrender.com |

### Local endpoints

| Service | Local URL |
| --- | --- |
| Video Miner | http://localhost:8080 |
| DailyMotion Miner | http://localhost:8081 |
| PeerTube Miner | http://localhost:8082 |

## Gateway

The gateway provides a single public access point for the whole system:

- https://proyectointegracion-hbtv.onrender.com

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

## Additional Notes

- The project follows a modular architecture with one Spring Boot application per miner.
- The gateway centralizes access to the individual services.
- Local ports must remain distinct to avoid conflicts when running multiple services at the same time.
- The cloud URLs are useful for validating the deployed version without requiring a local setup.
