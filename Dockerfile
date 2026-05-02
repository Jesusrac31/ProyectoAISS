FROM amazoncorretto:17-alpine-jdk

# Instalamos bash (para el script) y caddy (el proxy) en Alpine
RUN apk add --no-cache bash caddy

WORKDIR /app

# Copiamos tus 3 aplicaciones desde la carpeta target (ajusta los nombres si varían)
COPY target/peertubeminer-0.0.1-SNAPSHOT.jar /app/peertube.jar
COPY target/videominer-0.0.1-SNAPSHOT.jar /app/videominer.jar
# COPY target/tu-tercera-app.jar /app/app3.jar

# Copiamos los archivos de configuración que crearemos ahora
COPY Caddyfile /app/Caddyfile
COPY start.sh /app/start.sh

# Damos permisos al script
RUN chmod +x /app/start.sh

# Render usa por defecto el puerto 10000 para Docker
EXPOSE 10000

# El punto de entrada ahora es nuestro script, no un solo JAR
ENTRYPOINT ["/app/start.sh"]