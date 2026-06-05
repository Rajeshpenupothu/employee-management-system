# Construction Workforce Management System (EMS)

## Overview

Construction Workforce Management System (EMS) is a Spring Boot backend application developed to manage construction workers, sites, attendance tracking, overtime calculation, and workforce monitoring.

The project focuses on backend engineering concepts including REST API design, database modeling, Redis caching, PostgreSQL migration, transaction management, and business rule enforcement.

---

## Original Project Reference

Forked from: employee-management-system-spring-boot-beginner

Reason: The project provided a basic Spring Boot Employee Management foundation that could be extended into a Construction Workforce Management System while allowing focus on backend engineering requirements.

---

## Features

### Worker Management

* Create Worker
* Update Worker
* Delete Worker
* Retrieve Worker Details
* Assign Workers to Sites

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

* Active Worker Tracking
* TTL-Based Cache Expiration
* Graceful Fallback When Redis Is Unavailable

### PostgreSQL Migration

* Migrated from MySQL to PostgreSQL using Supabase

### API Documentation

* Swagger/OpenAPI Integration

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

* Swagger / OpenAPI

### Build Tool

* Maven

---

## Setup Instructions

### 1. Clone Repository

```bash
git clone https://github.com/Rajeshpenupothu/employee-management-system.git
cd employee-management-system/ems-backend
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

Update:

```properties
spring.datasource.url=jdbc:postgresql://YOUR_HOST:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

### 3. Configure Redis

```properties
spring.data.redis.host=localhost
spring.data.redis.port=6379
spring.data.redis.timeout=2000
```

### 4. Build

```bash
mvn clean install
```

### 5. Run

```bash
mvn spring-boot:run
```

### 6. Swagger

```text
http://localhost:8080/swagger-ui/index.html
```

---

## API Examples

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

### Create Site

```bash
curl -X POST http://localhost:8080/api/sites
```

### Clock In

```bash
curl -X POST "http://localhost:8080/api/attendance/clock-in?workerId=1&siteId=1"
```

### Clock Out

```bash
curl -X POST "http://localhost:8080/api/attendance/clock-out?workerId=1"
```

### Attendance History

```bash
curl "http://localhost:8080/api/attendance/log?workerId=1&from=2026-06-01&to=2026-06-30"
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
* Redis integration guidance
* PostgreSQL migration guidance
* Documentation assistance
* API review and validation guidance

All implementation, debugging, testing, API verification, and final technical decisions were manually performed and validated.

---

## Design Decisions

### Schema Design

Worker, Site, AttendanceLog, and OvertimeEntry were modeled as separate entities to maintain clear domain boundaries and improve maintainability.

### Redis Caching

Redis was used specifically for active worker tracking because attendance status is frequently queried and benefits from low-latency access.

### Overtime Processing

Overtime is automatically generated during clock-out processing to ensure consistency between attendance records and overtime entries.

### PostgreSQL Migration

Supabase PostgreSQL was selected to demonstrate migration from MySQL to a cloud-hosted relational database.

---

## Future Improvements

* JWT Authentication
* Role-Based Access Control
* Docker Deployment
* CI/CD Pipeline
* Automated Integration Tests
* Reporting Dashboard
* Audit Logging

---

## Author

Rajesh Penupothu

B.Tech Computer Science Engineering

Java | Spring Boot | PostgreSQL | Redis
