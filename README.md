# VibraOps – Asset Health Sandbox

VibraOps is a complete, production‑style system designed to showcase a wide
variety of back‑end and front‑end technologies in a cohesive demo.  It
implements condition monitoring for machines, generating synthetic vibration
samples, detecting anomalies in real time, and displaying them via a modern
web interface.  This project proves skills across Spring Boot, WebSocket
messaging, Postgres, Flyway, Next.js, React, Redux Toolkit, Tailwind CSS,
CI/CD, Docker and Kubernetes.

## Why VibraOps?

Many job ads require experience with REST APIs, SOAP services, real‑time
messaging, database migrations, modern front‑end frameworks and container
orchestration.  VibraOps touches them all in one elegant package.  It
includes:

* A Spring Boot API exposing REST endpoints for device management, sample
  retrieval and anomaly queries.
* A simple SOAP hello endpoint for completeness.
* A scheduled simulator that generates vibration samples every two seconds
  and flags anomalies using z‑score and interquartile range rules.
* WebSocket (STOMP) broadcasts so the UI receives live anomaly alerts.
* A Next.js front end using Redux Toolkit and Tailwind to display devices
  and a live anomaly feed.
* CI workflows and Docker Compose for easy local development and deployment.
* Kubernetes manifests for both the API and the web, ready for cloud.

## Getting Started

### Prerequisites

* Java 21
* Node.js 20
* Maven 3.8+
* Docker and Docker Compose (for containerized runs)

### Running locally

1. **Start Postgres** (via Docker Compose):

   ```bash
   cd vibraops/infra
   docker compose up -d db
   ```

2. **Run the API** (HTTP 8080) in a separate terminal:

   ```bash
   cd vibraops/backend
   mvn spring-boot:run
   ```

3. **Seed a device** (requires basic auth `admin:admin123`):

   ```bash
   curl -u admin:admin123 \
     -H "Content-Type: application/json" \
     -d '{"name":"Pump A","status":"OK","location":"Line 1"}' \
     http://localhost:8080/api/devices
   ```

4. **Run the web app** (HTTP 3000) in another terminal:

   ```bash
   cd vibraops/web
   npm install
   cp .env.example .env.local
   npm run dev
   # Visit http://localhost:3000/devices and http://localhost:3000/anomalies
   ```

### Docker Compose

Run all services together:

```bash
cd vibraops/infra
docker compose up --build
# API at http://localhost:8080, Web at http://localhost:3000
```

### Features

#### Backend

* **Spring Boot 3**: REST API, SOAP, WebSocket STOMP, scheduling.
* **Security**: basic auth (`admin` / `admin123`), easily upgradable to JWT.
* **Flyway + Postgres**: automated database migrations and persistent storage.
* **Simulator**: generates sample data and detects anomalies using statistical rules.
* **WebSockets**: real‑time anomaly notifications published to `/topic/anomalies`.
* **SOAP endpoint**: minimal hello service at `/ws/hello`.

#### Frontend

* **Next.js 14** with the app router and TypeScript.
* **Redux Toolkit** for state management.
* **Tailwind CSS** for styling and clean UI.
* **STOMP client** (`@stomp/stompjs`) to subscribe to live anomalies.
* Device list and create form; anomaly feed with live updates.

#### Infra

* **Docker Compose** for local orchestration.
* **Kubernetes manifests** (`infra/k8s`) for API and web deployments.
* **CI workflows** under `.github/workflows` for both API and web builds.
* **Setup script** under `scripts/setup.sh` to build and run via Docker Compose.

### Next Steps

* Extend rules in `SampleSimulator` to use more sophisticated analytics or
  integrate external sensors.
* Replace basic auth with JWT and add user registration.
* Add more UI pages (device detail with charts of recent samples, anomaly history).
* Configure Helm charts and ingress for production Kubernetes deployments.
