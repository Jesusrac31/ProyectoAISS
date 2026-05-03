FROM alpine:latest
RUN apk add --no-cache caddy
WORKDIR /app
COPY Caddyfile /app/Caddyfile
EXPOSE 10000
ENTRYPOINT ["caddy", "run", "--config", "/app/Caddyfile", "--adapter", "caddyfile"]