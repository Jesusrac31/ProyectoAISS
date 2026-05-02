#!/bin/bash

# Flags extremas para limitar RAM:
# -Xms16m : Memoria inicial muy baja
# -Xmx80m : Memoria máxima bajada a 80MB (3 apps x 80 = 240MB, deja margen al sistema)
# -XX:MaxMetaspaceSize=64m : Limita la memoria "clase" interna
# -XX:+UseSerialGC : Usa un recolector de basura muy básico que consume menos RAM
# -Xss256k : Reduce el tamaño de la pila de cada hilo

JAVA_OPTS="-Xms16m -Xmx80m -XX:MaxMetaspaceSize=64m -XX:+UseSerialGC -Xss256k"

echo "Iniciando DailyMotion Miner en puerto 8081..."
java $JAVA_OPTS -jar /app/dailymotion.jar --server.address=127.0.0.1 --server.port=8081 &
sleep 20 # Aumentamos el tiempo de espera a 20s para no saturar la CPU baja

echo "Iniciando PeerTube Miner en puerto 8082..."
java $JAVA_OPTS -jar /app/peertube.jar --server.address=127.0.0.1 --server.port=8082 &
sleep 20

echo "Iniciando VideoMiner en puerto 8083..."
java $JAVA_OPTS -jar /app/videominer.jar --server.address=127.0.0.1 --server.port=8083 &
sleep 20

echo "Iniciando Caddy Gateway..."
caddy run --config /app/Caddyfile --adapter caddyfile