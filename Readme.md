# ABC Ignite - Gym Management System

## Overview
A Spring Boot REST API implementation for managing gym classes and bookings. The system provides endpoints for creating classes, managing bookings, and searching through booking records. Built with a focus on clean architecture, proper validation, and error handling.

## Implementation Details
- **In-Memory Data Storage**: Uses thread-safe collections (`ArrayList` with `AtomicLong` for IDs)
- **Validation Layer**: Comprehensive input validation for dates, capacity, and booking rules
- **Error Handling**: Custom exception handling with specific error codes and messages
- **RESTful Design**: Follows REST principles for endpoints and HTTP methods

## Key Features
- **Class Management**
  - Create classes with schedule and capacity
  - Automatic validation for dates and capacity
  - Support for recurring daily classes
- **Booking System**
  - Book spots with capacity management
  - Automatic validation against class schedule
  - Support for multiple bookings per member
- **Search Functionality**
  - Filter bookings by member name
  - Filter by date range
  - Combine search criteria

## Technical Stack
- Java 17
- Spring Boot 3.4.2
- Maven (Build Tool)
- Lombok
- In-Memory Storage
- RESTful API

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher
- Postman (for testing)

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/verma1999/abc-ignite.git
   cd abc-ignite
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080`

## API Documentation

### 1. Create a Class
**Endpoint:** `POST /api/classes`

**Request Body:**
```json
{
    "name": "Yoga",
    "startDate": "2024-01-01",
    "endDate": "2024-01-20",
    "startTime": "14:00:00",
    "duration": 60,
    "capacity": 10
}
```

### 2. Book a Class
**Endpoint:** `POST /api/bookings`

**Request Body:**
```json
{
    "memberName": "John Doe",
    "classId": 1,
    "participationDate": "2024-01-05"
}
```

### 3. Search Bookings
**Endpoint:** `GET /api/bookings/search`

**Query Parameters:**
- `memberName` (optional): Filter by member name
- `startDate` (optional): Filter by start date (inclusive)
- `endDate` (optional): Filter by end date (inclusive)

**Example:**
```
GET /api/bookings/search?memberName=John&startDate=2024-01-01&endDate=2024-01-10
```

## Business Rules and Validations

### Class Creation
- End date must be after start date
- End date must be in the future
- Capacity must be at least 1
- Class name does not need to be unique

### Booking
- Participation date must be within class schedule
- Participation date must be in the future
- Cannot exceed class capacity
- Members can book multiple classes for the same time

## Project Structure
```
src/main/java/com/project/abc_ignite/
├── controller/         # REST controllers
├── service/           # Business logic
├── model/            # Domain models
├── exception/        # Error handling
└── AbcIgniteApplication.java
```

## Error Handling
The API implements comprehensive error handling with specific error codes and messages:
- `CLASS_NOT_FOUND`: When requested class doesn't exist
- `INVALID_DATE`: For date-related validation failures
- `CAPACITY_EXCEEDED`: When class is full
- `INVALID_CAPACITY`: When capacity is less than 1
- `END_DATE_BEFORE_START_DATE`: When end date precedes start date

## Testing
A Postman collection is provided in the `postman` folder for testing all API endpoints.

## License
This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details