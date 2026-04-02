# TaskTracker 🚀

[![Java 21](https://img.shields.io/badge/Java-21-blue.svg)](https://openjdk.org/)
[![Spring Boot 4](https://img.shields.io/badge/Spring%20Boot-4.0.5-green.svg)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-orange.svg)](https://postgresql.org/)
[![Kafka](https://img.shields.io/badge/Kafka-7.5-red.svg)](https://kafka.apache.org/)
[![Docker](https://img.shields.io/badge/Docker-Compose-blue.svg)](https://docker.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

REST API для управления задачами с Kafka events.

## ✨ Features
- ✅ **CRUD задачи** с пагинацией (`GET /api/v1/tasks`)
- ✅ **Получение по ID** (`GET /api/v1/tasks/{id}`)
- ✅ **Создание задачи** (`POST /api/v1/tasks`) → Kafka `task.created`
- ✅ **Назначение исполнителя** (`PATCH /api/v1/tasks/{id}/assignee`) → Kafka `task.assigned`
- ✅ **Смена статуса** (`PATCH /api/v1/tasks/{id}/status`)
- ✅ **DDD архитектура** (Ports & Adapters)
- ✅ **Docker Compose** (PostgreSQL + Kafka + Zookeeper + App)
- ✅ **OpenAPI/Swagger** docs (`/swagger-ui.html`)
- ✅ **Liquibase** миграции + индексы

## 🏗️ Tech Stack
```
Backend:     Java 21 + Spring Boot 4.0.5
Database:    PostgreSQL 15 + JPA + Liquibase
Messaging:   Kafka 7.5 + Spring Kafka
API:         Spring WebMVC + OpenAPI 3
DevOps:      Docker + Maven
Scale:       Pagination + UUID + Indexes (10k users / 100k tasks ✅)
```

## 🚀 Quick Start

```bash
# Клонировать и запустить
git clone https://github.com/YarullinAL/tasktracker.git
cd tasktracker
docker-compose up -d

# API доступно:
# Swagger: http://localhost:8080/swagger-ui.html
# API docs: http://localhost:8080/api/v3/api-docs
```

### 🧪 Test API (curl)

```bash
# 1. Список задач (page=0, size=10)
curl "http://localhost:8080/api/v1/tasks?page=0&size=5"

# 2. Создать задачу (Kafka event!)
curl -X POST http://localhost:8080/api/v1/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Fix login bug",
    "description": "User cannot login",
    "assigneeId": "550e8400-e29b-41d4-a716-446655440001"
  }'

# 3. Назначить исполнителя (Kafka event!)
curl -X PATCH http://localhost:8080/api/v1/tasks/{TASK_ID}/assignee \
  -H "Content-Type: application/json" \
  -d '{"assigneeId": "550e8400-e29b-41d4-a716-446655440002"}'

# 4. Сменить статус
curl -X PATCH http://localhost:8080/api/v1/tasks/{TASK_ID}/status \
  -H "Content-Type: application/json" \
  -d '{"status": "IN_PROGRESS"}'
```

## 📁 Структура проекта
```
tasktracker/
├── docker-compose.yaml     # Postgres + Kafka + App
├── Dockerfile              # Multi-stage build
├── pom.xml                 # Java 21 + Spring Boot 4
├── src/main/java/          # DDD Hexagonal
│   ├── domain/             # Entities, Ports, Events
│   ├── application/        # UseCases + DTOs
│   └── infrastructure/     # JPA, Kafka, REST
└── src/main/resources/db/  # Liquibase migrations
```

## 🛠️ Local Development

```bash
# Dev режим
docker-compose up postgres kafka zookeeper
mvn spring-boot:run

# Тесты (TODO: добавить!)
mvn test
```

## 📈 Performance (NFT требования)
- **10k users / 100k tasks**: Pagination + JPA indexes ✅
- **Kafka async events**: Не блокирует REST
- **UUID primary keys**: Partitioning-ready

## 🔍 Swagger UI
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## 🐳 Docker Services
```yaml
postgres:5432    # tasktracker_db / postgres
kafka:9092       # topics: task.created, task.assigned
app:8080         # REST API
```

## 📝 Models
```java
Task { id: UUID, name: String, description: String, status: Enum, assigneeId: UUID }
User { id: UUID, name: String, email: String }
```

## 🤔 Архитектура
```
[REST Controller] → [UseCase] → [Domain Port] → [JPA/Kafka Adapter]
                           ↓ Events
                      [Kafka Producer]
```

## 👨‍💼 Author
**Айдар Яруллин** [@YarullinAL](https://t.me/YarullinAL)
