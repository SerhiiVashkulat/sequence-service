# Sequence Service API Documentation

The Sequence Service API provides endpoints for working sequences, enabling operations such as GC content calculation, motif indexing, reverse complement generation, and more.

## Base URL
```
http://<your-domain>/api/v1/sequences
```

## Endpoints

### 1. Validate a Sequence
**Endpoint:** `POST /valid`

**Description:** Validates sequence to ensure it only contains valid characters (A, T, C, G).

**Request:**
```json
{
  "sequence": "ATCG"
}
```

**Response:**
- Success:
  ```json
  {
    "valid": true
  }
  ```
- Error:
  ```json
  {
    "description": "Sequence must consist only of A, T, C, G",
    "path": "/api/v1/sequences/valid",
    "timestamp": "2025-01-15T13:52:00"
  }
  ```

### 2. Calculate GC Content
**Endpoint:** `POST /gc-content`

**Description:** Calculates the GC content percentage of a given sequence.

**Request:**
```json
{
  "sequence": "ATCG"
}
```

**Response:**
```json
{
  "gc_content": 50.0
}
```

### 3. Replace T with U
**Endpoint:** `POST /rna-sequence`

**Description:** Converts sequence by replacing all occurrences of T with U.

**Request:**
```json
{
  "sequence": "ATCG"
}
```

**Response:**
```json
{
  "rna_sequence": "AUCG"
}
```

### 4. Retrieve Motif Indexes
**Endpoint:** `POST /motif-indexes`

**Description:** Finds all start indexes of a motif in a given sequence.

**Request:**
```json
{
  "sequence": "ATCGATCG",
  "motif": "AT"
}
```

**Response:**
```json
{
  "motif_indexes": [0, 4]
}
```

### 5. Reverse Complement
**Endpoint:** `POST /reverse-complement`

**Description:** Generates the reverse complement of a DNA sequence.

**Request:**
```json
{
  "sequence": "ATCG"
}
```

**Response:**
```json
{
  "reverse_complement": "CGAT"
}
```

## Error Handling
All endpoints return error responses in the following format:

```json
{
  "description": "<Error description>",
  "path": "<Request path>",
  "timestamp": "<Timestamp>"
}
```

## Example URL Requests

### Validate a Sequence
```bash
curl -X POST \
  http://<your-domain>/api/v1/sequences/valid \
  -H 'Content-Type: application/json' \
  -d '{"sequence": "ATCG"}'
```

### Calculate GC Content
```bash
curl -X POST \
  http://<your-domain>/api/v1/sequences/gc-content \
  -H 'Content-Type: application/json' \
  -d '{"sequence": "ATCG"}'
```

### Retrieve Motif Indexes
```bash
curl -X POST \
  http://<your-domain>/api/v1/sequences/motif-indexes \
  -H 'Content-Type: application/json' \
  -d '{"sequence": "ATCGATCG", "motif": "AT"}'
```

### Replace T with U
```bash
curl -X POST \
  http://<your-domain>/api/v1/sequences/rna-sequence \
  -H 'Content-Type: application/json' \
  -d '{"sequence": "ATCGTGCT"}'
```

### Reverse Complement
```bash
curl -X POST \
  http://<your-domain>/api/v1/sequences/reverse-complement \
  -H 'Content-Type: application/json' \
  -d '{"sequence": "ATCG"}'
```

## How to Run the Project

### Prerequisites
1. **Java Development Kit (JDK)**: Ensure JDK 17 or higher is installed.
2. **Maven**: Ensure Apache Maven is installed.
3. **Database**: Set up a compatible database  (H2).
5. **Git**: Clone the repository.

### Steps to Run Locally
1. **Clone the Repository**:
   ```bash
   git clone <https://github.com/SerhiiVashkulat/sequence-service.git>
   cd sequence-service
   ```

2. **Configure the Application**:
  - Open `application.yml` in the `src/main/resources` directory.
  - Set up the database connection details:
    ```yaml
    spring:
      datasource:
        url: your_url
        username: your_username
        password: your_password
    ```
4. **Build the Project**:
   ```bash
   mvn clean install
   ```

4. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```

5. **Access the API**:
  - By default, the application will run on `http://localhost:8080`.
  - Test the endpoints using tools like [Postman](https://www.postman.com/) or `curl`.

---
## Technologies Used

1. **Programming Language**: Kotlin
2. **Framework**: Spring Boot (v3.4.1)
  - **Spring Data JPA**: For database interactions.
  - **Spring Web**: For building REST APIs.
3. **Database**: H2 
4. **Testing**:
  - JUnit 5
  - Mockito
  - Spring Boot Test
5. **Build Tool**: Maven

---
## Interaction with LLM
The main queries were about algorithms and entity binding. Helped quickly with regex. Many algorithms were considered for the program, but I chose the best for me. Used ChatGpt and Gemini   
