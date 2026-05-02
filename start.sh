#!/bin/bash

echo "Iniciando DailyMotion Miner en puerto 8081..."
java -Xmx120m -jar /app/dailymotion.jar --server.address=127.0.0.1 --server.port=8081 &
# Le damos 15 segundos enteros de CPU exclusiva a esta app
sleep 15 

echo "Iniciando PeerTube Miner en puerto 8082..."
java -Xmx120m -jar /app/peertube.jar --server.address=127.0.0.1 --server.port=8082 &
sleep 15

echo "Iniciando VideoMiner en puerto 8083..."
java -Xmx120m -jar /app/videominer.jar --server.address=127.0.0.1 --server.port=8083 &
sleep 15

echo "Iniciando Caddy Gateway..."
caddy run --config /app/Caddyfile --adapter caddyfile