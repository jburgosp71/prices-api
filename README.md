# Product Price Query Service (Spring Boot)

This project implements a REST service in Spring Boot to query product prices in an e-commerce database, using Java 21.

## Description

The application provides a REST endpoint that accepts as input parameters:
- Application date
- Product identifier
- Brand identifier

It returns as output data:
- Product identifier
- Brand identifier
- Applicable rate
- Application dates
- Final price to apply (and currency)

An in-memory H2 database is used to store price data.

## Requirements

- Java 21
- Gradle
- Spring Boot

## Configuration

### H2 Database
- Configured in `application.properties`
- Sample data inserted into `BRANDS` and `PRICES` tables via `data.sql`
- Access H2 console after startup:  
  `http://localhost:8081/h2-console`

### Dependencies
Managed in `build.gradle`:
- Spring Boot
- H2 Database
- Mockito
- JoCoCo
- Lombok
- SpotBugs

## Execution

### Run the Application
```
./gradlew bootRun
```

## How to Run the Application with Docker Compose

To run the `prices-api` application using Docker Compose, follow these steps:

### Prerequisites
1. Ensure you have **Docker** and **Docker Compose** installed on your system.
    - [Install Docker](https://docs.docker.com/get-docker/)
    - [Install Docker Compose](https://docs.docker.com/compose/install/)


2. Start application:
````
docker-compose run -d --build
````

3. Stop application
````
docker-compose down
````

### Query the Endpoint
**Endpoint:** `/price-seatch`

**Parameters:**
- `brandid`: Brand identifier
- `date`: Application date (ISO 8601 format)
- `productid`: Product identifier

**Example Request:**
````
GET /price-search?brandid=1&date=2020-06-14T10:00:00Z&productid=35455
````

**Request endpoint documentation** `/price-seatch`

## Testing

### Unit Tests
Tests for:
- `BrandService`
- `PriceService`
- `PriceController`

**Run tests:**
````
./gradlew test
````

### Integration Tests
Test cases for REST endpoint:

1. Request at 10:00 on the 14th for product 35455 (brand 1/ZARA)
2. Request at 16:00 on the 14th for product 35455 (brand 1/ZARA)
3. Request at 21:00 on the 14th for product 35455 (brand 1/ZARA)
4. Request at 10:00 on the 15th for product 35455 (brand 1/ZARA)
5. Request at 21:00 on the 16th for product 35455 (brand 1/ZARA)

**Run integration tests:**
````
./gradlew integrationTest
````

## Linter: SpotBugs

This project includes **SpotBugs** as a static code analysis tool. SpotBugs helps identify potential bugs, vulnerabilities, and bad practices in the code.

### How to use SpotBugs

1. **Installation**: SpotBugs is already integrated into the project, so no manual installation is required.

2. **Running SpotBugs**: 

    ```bash
    ./gradlew spotbugsMain
    ```

3. **Results**: The SpotBugs analysis results are saved in a `build/spotbugs` directory. You can review these reports for more details on the issues detected.

## TODO

1. **Parameters no case-sensitive**
2. **Not found page when access to unknown endpoint**
3. **Apply infrastructure/kubernetes/pricesapi-deployment.tpl.yml on .gitlab-cy.yml**