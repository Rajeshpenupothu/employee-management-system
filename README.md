# Construction Workforce Management System (EMS)

## Overview

The Construction Workforce Management System (EMS) is a Spring Boot based backend application designed to manage construction workers, sites, attendance tracking, overtime calculation, and workforce monitoring.

The system enables site supervisors and administrators to efficiently manage workers across construction sites while maintaining accurate attendance and overtime records.

---

## Features

### Worker Management

* Create, update, retrieve, and delete workers
* Unique employee code validation
* Worker designation management
* Site assignment support

### Site Management

* Create and manage construction sites
* Track site details and supervisors
* Assign workers to sites

### Attendance Management

* Worker clock-in and clock-out
* Attendance history tracking
* Attendance pagination support
* Duplicate clock-in prevention
* Attendance anomaly detection

### Overtime Management

* Automatic overtime calculation
* 1.5x hourly rate for first 2 overtime hours
* 2x hourly rate for additional overtime hours
* Monthly overtime summary
* Overtime settlement processing
* Monthly overtime cap validation

### Redis Integration

* Active worker tracking
* Cache-based workforce monitoring
* TTL-based cache expiration
* Graceful fallback when Redis is unavailable

### Exception Handling

* Centralized global exception handling
* Consistent error responses
* Business rule validation

### API Documentation

* Swagger / OpenAPI integration
* Interactive API testing interface

---

## Technology Stack

### Backend

* Java 17
* Spring Boot 3
* Spring Web
* Spring Data JPA
* Spring Security
* Hibernate

### Database

* PostgreSQL (Supabase)

### Caching

* Redis

### Documentation

* Swagger / OpenAPI

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

## Database Entities

### Worker

* id
* employeeCode
* fullName
* phoneNumber
* designation
* dailyWage
* active
* siteId

### Site

* id
* siteCode
* siteName
* location
* supervisorName

### AttendanceLog

* id
* workerId
* siteId
* attendanceDate
* clockInTime
* clockOutTime
* anomalyFlag

### OvertimeEntry

* id
* workerId
* workDate
* overtimeHours
* overtimeAmount
* status

---

## API Endpoints

### Worker APIs

```http
GET    /api/workers
GET    /api/workers/{id}
POST   /api/workers
PUT    /api/workers/{id}
DELETE /api/workers/{id}
```

### Site APIs

```http
GET    /api/sites
GET    /api/sites/{id}
POST   /api/sites
PUT    /api/sites/{id}
DELETE /api/sites/{id}
```

### Attendance APIs

```http
GET    /api/attendance
GET    /api/attendance/{id}
POST   /api/attendance
PUT    /api/attendance/{id}
DELETE /api/attendance/{id}

POST   /api/attendance/clock-in
POST   /api/attendance/clock-out

GET    /api/attendance/log
GET    /api/attendance/active
```

### Overtime APIs

```http
GET    /api/overtime
GET    /api/overtime/{id}
POST   /api/overtime
DELETE /api/overtime/{id}

GET    /api/overtime/summary/{workerId}
POST   /api/overtime/settle/{workerId}
```

---

## Local Setup

### Clone Repository

```bash
git clone <your-repository-url>
cd employee-management-system
```

### Configure Application

Copy:

```text
application-example.properties
```

to:

```text
application.properties
```

Update the PostgreSQL and Redis credentials.

### Build Project

```bash
mvn clean install
```

### Run Application

```bash
mvn spring-boot:run
```

---

## Swagger Documentation

After starting the application:

```text
http://localhost:8080/swagger-ui/index.html
```

---

## Future Enhancements

* JWT Authentication
* Role Based Access Control
* Docker Deployment
* CI/CD Pipeline
* Frontend Dashboard
* Reporting and Analytics

---

## Author

Rajesh Penupothu

B.Tech Computer Science Engineering

Java | Spring Boot | PostgreSQL | Redis
