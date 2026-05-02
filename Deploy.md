## Deployment with just one route

Objective routes:
- VideoMiner: `https://www.example.com/api/v1/videominer`
- PeerTubeMiner: `https://www.example.com/api/v1/peertube`
- DailyMotionMiner: `https://www.example.com/api/v1/dailymotion`

For using just one domain in render, we will use a gateway (Nginx). This will let us map each app to some paths.

---

## 1) Build (optional, automatic)

For building a project, you just must open a terminal inside its folder and execute `mvn clean package`

The output will be `target/nameOfApplication-0.0.1-SNAPSHOT.jar`. Copy the file in dist directory.

---

## 2) Dockerfiles

Se han creado Dockerfiles por proyecto (build multi-stage) y un gateway:
- `VideoMiner/Dockerfile`
- `PeerTubeMiner/Dockerfile`
- `DailyMotionMiner/Dockerfile`
- `gateway/Dockerfile`

Los Dockerfiles construyen con Maven dentro del contenedor, asi no necesitas subir `target/` al repo.

---

## 3) Configuracion en Render (4 servicios)

### 3.1 Servicios privados (3)
Crea 3 Web Services privados en Render, uno por proyecto. Nombres de servicio (importante para el gateway):
- `videominer`
- `peertubeminer`
- `dailymotionminer`

Para cada servicio privado:
- Build: Docker
- Build Context:
   - `VideoMiner`
   - `PeerTubeMiner`
   - `DailyMotionMiner`
- Dockerfile Path:
   - `VideoMiner/Dockerfile`
   - `PeerTubeMiner/Dockerfile`
   - `DailyMotionMiner/Dockerfile`
- Environment Variables:
   - `SERVER_SERVLET_CONTEXT_PATH`:
      - `/api/v1/videominer`
      - `/api/v1/peertube`
      - `/api/v1/dailymotion`

### 3.2 Gateway publico (1)
Crea un Web Service publico llamado `gateway`:
- Build: Docker
- Build Context: `gateway`
- Dockerfile Path: `gateway/Dockerfile`
- Environment Variables:
   - `PORT=8080`

El gateway expone una sola URL y enruta por path a los tres servicios privados.

---

## 4) Dominio y verificacion

1. Asigna tu dominio `www.example.com` al servicio `gateway`.
2. Verifica:
    - `https://www.example.com/api/v1/videominer`
    - `https://www.example.com/api/v1/peertube`
    - `https://www.example.com/api/v1/dailymotion`

---

## 5) Version de Java

Los proyectos usan Java 17 (ver `<java.version>17</java.version>` en cada `pom.xml`).

---

## 6) Notas finales

- Los `.dockerignore` ya evitan subir `target/` en las builds de Docker.
- Si necesitas ejecutar localmente un contenedor, define `PORT=8080`:
   - `docker run -e PORT=8080 -p 8080:8080 <imagen>`