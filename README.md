# FacilityFlow

**A configurable facilities management platform for multi-site organizations.**

> **Status: Early development** — The backend foundation and database infrastructure are in place. The application is not yet a finished facilities management system.

FacilityFlow is a full-stack software engineering portfolio project inspired by real-world facilities and maintenance operations. Its goal is to bring work orders, preventive maintenance, assets, locations, and operational oversight into one configurable system that can serve different industries without relying on a fixed property layout.

The project is being developed with a focus on maintainable backend architecture, relational data modeling, API design, and practical business workflows.

## Technology stack

| Layer                            | Technology                        | Status                        |
| -------------------------------- | --------------------------------- | ----------------------------- |
| Backend                          | Java 21, Spring Boot 4.1.1, Maven | In progress                   |
| Persistence                      | Spring Data JPA / Hibernate       | Configured                    |
| Database                         | PostgreSQL 17                     | Configured via Docker Compose |
| Schema migrations                | Flyway                            | In use                        |
| Local infrastructure             | Docker Compose                    | Configured                    |
| Frontend                         | Next.js, React, TypeScript        | Planned                       |
| Authentication and authorization | To be designed                    | Planned                       |

## Project goals

FacilityFlow is intended to support:

- **Multi-site organizations:** Organizations with regions and multiple facilities or properties, with visibility appropriate to a user's scope.
- **Configurable location hierarchies:** Nested location structures that fit each facility, rather than a rigid building/unit/room schema.
- **Work-order management:** Track maintenance requests, priorities, statuses, locations, assignments, and resolution history.
- **Preventive maintenance:** Define recurring maintenance schedules and track completion.
- **Asset management:** Associate equipment with locations, service history, and maintenance activity.
- **Role-based access:** Distinguish site-level, regional, and organization-wide permissions, enforced on the backend.
- **Operational reporting:** Surface maintenance workloads, completion trends, and other useful metrics.

These are **product goals**, not claims that all listed features are currently implemented.

## Current implementation

The early backend foundation includes:

- A Java 21 Spring Boot application using Maven.
- PostgreSQL development infrastructure managed by Docker Compose.
- Flyway migrations for database schema changes.
- Hibernate schema validation (`ddl-auto=validate`) instead of automatic schema generation.
- Initial work-order persistence modeling, including a work-order entity with status, priority, location, and timestamps.
- An initial health endpoint for checking that the application starts.

**Not yet implemented as complete features:** Work-order CRUD APIs, full organization and location models, authentication and authorization, preventive maintenance workflows, and the frontend.

## Architecture direction

FacilityFlow is planned as a **modular monolith** with a **package-by-feature** backend structure. The goal is to keep related business logic together while retaining the simplicity of a single deployable application during early development.

Planned feature areas include organizations, sites, locations, work orders, assets, preventive maintenance, users, and access control. Feature boundaries and interfaces will evolve as functionality is implemented.

### Data-modeling principles

- **Database-first schema management:** PostgreSQL schema changes are versioned with Flyway; Hibernate checks that entity mappings agree with the schema.
- **Configurable structures:** Different facilities should be able to model their own location trees and terminology.
- **Scoped access:** Organization, region, and site boundaries will determine what authenticated users may access.
- **Preserved history:** Location changes and maintenance activity should not silently erase relevant historical context.

## Getting started

### Prerequisites

- JDK 21
- Docker Desktop or Docker Engine with Compose
- Git

The repository includes a Maven wrapper, so a separate Maven installation is not required.

### 1. Clone the repository

```bash
git clone https://github.com/Mr-Rogers-Builds/FacilityFlow.git
cd FacilityFlow
```

### 2. Configure the local database credentials

Create a `.env` file at the **repository root**:

```dotenv
DB_USERNAME=facilityflow
DB_PASSWORD=replace-with-a-local-development-password
```

The Docker Compose configuration reads these variables to create the PostgreSQL database user. The Spring Boot configuration also reads `DB_USERNAME` and `DB_PASSWORD` and defaults to `jdbc:postgresql://localhost:5432/facilityflow` for the database URL.

**Security:** Do not commit `.env` or real credentials to version control.

### 3. Start PostgreSQL

From the repository root:

```bash
docker compose up -d
```

Check the container:

```bash
docker compose ps
```

PostgreSQL is exposed on local port `5432`. If another PostgreSQL instance already uses that port, adjust the port mapping and application database URL accordingly.

### 4. Run the backend

From the repository root:

**Windows PowerShell:**

```powershell
cd backend
.\mvnw.cmd spring-boot:run
```

**macOS / Linux:**

```bash
cd backend
./mvnw spring-boot:run
```

On startup, Flyway applies pending migrations and Hibernate validates the resulting schema. The backend uses Spring Boot's default port `8080` unless you override it.

### 5. Run tests

From `backend/`:

**Windows PowerShell:**

```powershell
.\mvnw.cmd test
```

**macOS / Linux:**

```bash
./mvnw test
```

The presence of a test command does not imply comprehensive automated test coverage; tests will be expanded as features are added.

### 6. Stop the database

From the repository root:

```bash
docker compose down
```

The named PostgreSQL volume preserves local database data. Avoid `docker compose down -v` unless you intentionally want to delete it.

## Repository structure

```text
FacilityFlow/
├── backend/              # Spring Boot application, Maven wrapper, and source code
├── docker-compose.yml    # Local PostgreSQL service
├── .gitignore
├── LICENSE
└── README.md
```

A frontend directory and further documentation will be added as development progresses.

## Development roadmap

- [x] Initialize the Spring Boot backend and Maven project
- [x] Configure local PostgreSQL using Docker Compose
- [x] Introduce Flyway migrations and Hibernate schema validation
- [x] Create the initial work-order persistence model
- [ ] Implement work-order repository, service, and REST endpoints
- [ ] Add request validation, error handling, and automated tests
- [ ] Model organizations, regions, sites, and hierarchical locations
- [ ] Design authentication and scoped authorization
- [ ] Implement asset and preventive maintenance workflows
- [ ] Build the Next.js / React frontend
- [ ] Add end-to-end integration, deployment, and operational documentation

The sequence may change as the design evolves.

## Why this project?

FacilityFlow draws on hands-on experience managing maintenance operations, work orders, preventive maintenance, staff, and vendors. That domain knowledge shapes the business requirements and edge cases. The engineering goal is to turn those requirements into a well-structured application while developing practical skills in backend development, data modeling, testing, and full-stack delivery.

## Project maturity

FacilityFlow is an **active learning and portfolio project**, not production-ready software. Functionality, data models, and APIs are subject to change. As the project progresses, this README will be updated to reflect implemented features rather than aspirational ones.

## License

Copyright © 2026 Mr-Rogers-Builds. All rights reserved.

This project is shared for **personal, educational, and portfolio evaluation purposes only**. Commercial use, resale, or integration into commercial products or services is not permitted without prior written permission. See the [LICENSE](./LICENSE) file for full details.
