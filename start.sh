#!/bin/bash

echo "Starting DailyMotion Miner in port 8081..."
java -Xmx120m -jar /app/dailymotion.jar --server.address=127.0.0.1 --server.port=8081 &

echo "Starting PeerTube Miner in port 8082..."
java -Xmx120m -jar /app/peertube.jar --server.address=127.0.0.1 --server.port=8082 &

echo "Starting VideoMiner in port 8083..."
java -Xmx120m -jar /app/videominer.jar --server.address=127.0.0.1 --server.port=8083 &

# Esperar a que las apps respiren un poco
sleep 12

echo "Iniciando Caddy Gateway..."
caddy run --config /app/Caddyfile --adapter caddyfile