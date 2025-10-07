# Reservation API

## Overview
REST Controller for managing reservation operations including CRUD functionality and approval workflow.

## Base URL
```
/reservation
```

## Endpoints

### 📋 Get All Reservations
- **Method:** `GET`
- **Path:** `/`
- **Description:** Retrieve all reservations
- **Response:**
  - `200 OK` - Returns list of Reservation objects
- **Logs:** "Called getAllReservations"

### 🔍 Get Reservation by ID
- **Method:** `GET`
- **Path:** `/{id}`
- **Parameters:**
  - `id` (path, Long) - Reservation identifier
- **Response:**
  - `200 OK` - Reservation found and returned
  - `404 NOT FOUND` - Reservation does not exist
- **Logs:** "Called getReservationById"

### ➕ Create Reservation
- **Method:** `POST`
- **Path:** `/`
- **Body:** Reservation object (JSON)
- **Response:**
  - `201 CREATED` - Reservation successfully created
- **Logs:** "Called createReservation, reservationToCreate"

### ✏️ Update Reservation
- **Method:** `PUT`
- **Path:** `/{id}`
- **Parameters:**
  - `id` (path, Long) - Reservation identifier
- **Body:** Reservation object (JSON)
- **Response:**
  - `200 OK` - Reservation successfully updated
  - `400 BAD REQUEST` - Update operation failed
- **Logs:** "Called updateReservation"

### 🗑️ Delete Reservation
- **Method:** `DELETE`
- **Path:** `/{id}`
- **Parameters:**
  - `id` (path, Long) - Reservation identifier
- **Response:**
  - `200 OK` - Reservation successfully deleted
  - `404 NOT FOUND` - Reservation does not exist
- **Logs:** "Called deleteReservation"

### ✅ Approve Reservation
- **Method:** `POST`
- **Path:** `/{id}/approve`
- **Parameters:**
  - `id` (path, Long) - Reservation identifier
- **Response:**
  - `200 OK` - Reservation successfully approved
  - `400 BAD REQUEST` - Approval failed (invalid state)
- **Logs:** "Called approveReservation"

## Error Handling

| Exception | HTTP Status | Description |
|-----------|-------------|-------------|
| `NoSuchElementException` | `404 NOT FOUND` | Requested resource not found |
| `IllegalStateException` | `400 BAD REQUEST` | Invalid operation state |
| General `Exception` | `400 BAD REQUEST` | Update operation failed |

## Technology Stack
- **Framework:** Spring Boot
- **Logging:** SLF4J
- **Architecture:** REST Controller + Service Layer

## Example Usage

### Create Reservation
```bash
curl -X POST /reservation \
  -H "Content-Type: application/json" \
  -d '{
    "customerName": "John Doe",
    "date": "2024-01-15",
    "time": "19:00",
    "partySize": 4
  }'
```

### Approve Reservation
```bash
curl -X POST /reservation/123/approve
```

## Notes
- All endpoints include comprehensive logging
- Proper HTTP status codes are returned for different scenarios
- Service layer exceptions are properly handled and mapped to appropriate HTTP responses