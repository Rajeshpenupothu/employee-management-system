# Construction Workforce Management System (EMS)

## Overview

Construction Workforce Management System is a Spring Boot backend application designed to manage workers, construction sites, attendance tracking, overtime calculation, and workforce monitoring.

The system was developed as part of the Backend Engineering Assignment and focuses on schema design, business rules, caching, data integrity, and API development.

---

## Forked Project

Forked from an open-source Employee Management System built using Spring Boot.

Reason: It already provided a basic employee management foundation, allowing faster implementation of the workforce attendance and overtime management requirements while focusing on backend engineering tasks.

---

## Features

### Worker Management

* Create Worker
* Update Worker
* Delete Worker
* Get Worker Details
* Assign Worker to Site

### Site Management

* Create Site
* Update Site
* Delete Site
* Manage Construction Locations

### Attendance Management

* Clock In
* Clock Out
* Attendance History
* Active Worker Tracking
* Attendance Anomaly Detection

### Overtime Management

* Automatic Overtime Calculation
* Monthly Overtime Summary
* Overtime Settlement
* Monthly Overtime Limit Validation

### Redis Integration

* Active Worker Cache
* TTL Based Cache Expiration
* Graceful Fallback When Redis Is Unavailable

### PostgreSQL Migration

* Migrated persistence layer from MySQL to PostgreSQL (Supabase)

### API Documentation

* Swagger / OpenAPI Integration

---

## Technology Stack

### Backend

* Java 17
* Spring Boot 3
* Spring Data JPA
* Spring Security
* Hibernate

### Database

* PostgreSQL (Supabase)

### Cache

* Redis

### Documentation

* Swagger OpenAPI

### Build Tool

* Maven

---

## Project Structure

```text
controller/
service/
service/impl/
repository/
entity/
dto/
mapper/
exception/
config/
common/
```

---

## Setup Instructions

### 1. Clone Repository

```bash
git clone <repository-url>
cd employee-management-system
```

### 2. Configure PostgreSQL (Supabase)

Create:

```text
src/main/resources/application.properties
```

Copy values from:

```text
application-example.properties
```

Example:

```properties
spring.datasource.url=jdbc:postgresql://YOUR_HOST:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

### 3. Configure Redis

Install Redis locally.

Example:

```properties
spring.data.redis.host=localhost
spring.data.redis.port=6379
spring.data.redis.timeout=2000
```

### 4. Build Project

```bash
mvn clean install
```

### 5. Run Project

```bash
mvn spring-boot:run
```

### 6. Access Swagger

```text
http://localhost:8080/swagger-ui/index.html
```

---

## API Examples (curl)

### Create Worker

```bash
curl -X POST http://localhost:8080/api/workers \
-H "Content-Type: application/json" \
-d '{
"employeeCode":"W001",
"fullName":"Rajesh Kumar",
"phoneNumber":"9876543210",
"designation":"MASON",
"dailyWage":850,
"active":true,
"siteId":1
}'
```

### Clock In

```bash
curl -X POST "http://localhost:8080/api/attendance/clock-in?workerId=1&siteId=1"
```

### Clock Out

```bash
curl -X POST "http://localhost:8080/api/attendance/clock-out?workerId=1"
```

### Overtime Summary

```bash
curl "http://localhost:8080/api/overtime/summary/1?month=2026-06"
```

### Overtime Settlement

```bash
curl -X POST "http://localhost:8080/api/overtime/settle/1?month=2026-05"
```

---

## AI Tools Used

### ChatGPT

Used for:

* Requirement analysis
* Architecture discussions
* API design reviews
* Redis integration guidance
* PostgreSQL migration guidance
* Documentation assistance

All implementation code was reviewed, tested, and integrated manually.

---

## Design Decisions

### Schema Design

Worker, Site, AttendanceLog, and OvertimeEntry were separated into dedicated entities to maintain clear domain boundaries and support future scalability.

### Redis Caching

Redis was used for active worker tracking to reduce database reads and support near real-time workforce monitoring.

### Overtime Calculation

Overtime is automatically generated during clock-out processing to ensure data consistency and eliminate manual entry errors.

### PostgreSQL Migration

Supabase PostgreSQL was selected to demonstrate cloud-hosted database integration and migration capability.

---

## What I Would Improve With More Time

* JWT Authentication
* Role-Based Access Control
* Docker Deployment
* CI/CD Pipeline
* Frontend Dashboard
* Audit Logging
* Automated Testing Suite

---

## Author

Rajesh Penupothu

B.Tech Computer Science Engineering

Java | Spring Boot | PostgreSQL | Redis
