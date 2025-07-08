# Visa Monitoring System

Full‑stack starter template using **React (TypeScript)**, **Spring Boot 3.2.x**, **PostgreSQL**, and **AWS CDK**.

## Structure

```
visa-monitoring-system/
├── backend/            # Spring Boot API
├── frontend/           # React UI (TypeScript)
├── infra/              # AWS CDK stack (TypeScript)
├── nightly-job/        # Python Lambda for nightly overstayer check
└── docker-compose.yml  # Local dev services
```

## Quick Start

```bash
# 1. Run services locally
docker-compose up -d postgres

# 2. Backend
cd backend
./mvnw spring-boot:run   # or use VS Code / IntelliJ

# 3. Frontend
cd ../frontend
npm install
npm start

# 4. Deploy AWS infra
cd ../infra
npm install
cdk deploy  # requires AWS credentials
```

See **infra/README.md** for CDK details.
