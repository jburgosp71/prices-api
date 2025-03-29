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

## Execution

### Run the Application
```
./gradlew bootRun
```

### Query the Endpoint
**Endpoint:** `/prices`

**Parameters:**
- `brandid`: Brand identifier
- `date`: Application date (ISO 8601 format)
- `productid`: Product identifier

**Example Request:**
````
GET /prices?brandid=1&date=2020-06-14T10:00:00Z&productid=35455
````

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
2. Request at 16:00 on the 14th for product 35455
3. Request at 21:00 on the 14th for product 35455
4. Request at 10:00 on the 15th for product 35455
5. Request at 21:00 on the 16th for product 35455

**Run integration tests:**
````
./gradlew integrationTest
````
