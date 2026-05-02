FROM amazoncorretto:17-alpine-jdk

# Instalamos bash y caddy
RUN apk add --no-cache bash caddy

WORKDIR /app

# Copiamos los 3 JARs desde tu carpeta local 'dist' al contenedor
COPY dist/dailymotionminer-0.0.1-SNAPSHOT.jar /app/dailymotion.jar
COPY dist/peertubeminer-0.0.1-SNAPSHOT.jar /app/peertube.jar
COPY dist/videominer-0.0.1-SNAPSHOT.jar /app/videominer.jar

# Copiamos la configuración del proxy y el script de arranque
COPY Caddyfile /app/Caddyfile
COPY start.sh /app/start.sh

# Permisos de ejecución
RUN chmod +x /app/start.sh

# Render usa el puerto 10000 por defecto
EXPOSE 10000

ENTRYPOINT ["/app/start.sh"]