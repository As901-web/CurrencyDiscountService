# CurrencyDiscountService
Here’s a complete and well-structured **README.md** for your Spring Boot project:

---

# 🧾 Currency Discount Service API

A Spring Boot application that calculates the net payable amount for a bill by applying specific discount rules and converting the total into a target currency using real-time exchange rates.

---

## Features

- Apply conditional percentage and flat-rate discounts.
-  Real-time currency conversion via external API.
-  Basic authentication to secure endpoints.
-  Unit-tested for core discount and conversion logic.

---

## Technologies Used

- Java 17
- Spring Boot
- Spring Web
- Spring Security
- Maven
- JUnit & Mockito
- REST APIs

---

##  Business Logic

### Discounts:

- **Percentage-based (one at a time):**
  - 30% for employees *(non-groceries only)*
  - 10% for affiliates *(non-groceries only)*
  - 5% for customers with >2 years tenure *(non-groceries only)*

- **Flat Discount:**
  - $5 off for every $100 on the total bill *(applies to all items)*

---

## Currency Conversion

- Uses external API like [ExchangeRates-API](https://open.er-api.com/v6/latest/{base_currency})  
- Requires an API key (`application.properties`)

---

## Authentication

- Uses HTTP **Basic Authentication**
- Credentials defined in `application.properties`:

```properties
spring.security.user.name=admin
spring.security.user.password=admin123
```

---

## API Endpoint

### `POST /api/calculate`

**Headers**:
- `Authorization: Basic <base64(admin:admin123)>`
- `Content-Type: application/json`

**Request Body Example**:
```json
{
  "items": [
    { "name": "Shoes", "category": "fashion", "price": 200 },
    { "name": "Rice", "category": "groceries", "price": 100 }
  ],
  "userType": "customer",
  "customerTenure": 3,
  "originalCurrency": "AED",
  "targetCurrency": "INR"
}
```

**Response**:
```json
{
"netAmount": 6397.08245,
"currency": "INR"
}
```

---

## 🧪 Running Tests

```bash
mvn test
```

Includes:
- `DiscountServiceTest`
- `ExchangeRateServiceTest` (mocked)
- `CalculateControllerTest` (mocked services)

---

## Configuration

In `application.properties`:`properties
exchange.api.url=https://open.er-api.com/v6/latest/
exchange.api.key=your-api-key

---

#
CurrencyDiscountService/
├── src/
│   ├── main/
│   │   ├── java/com/CurrencyExchange/
│   │   │   ├── controller/CalculateController.java
│   │   │   ├── model/Item.java
│   │   │   ├── model/BillRequest.java
│   │   │   ├── model/BillResponse.java
│   │   │   ├── service/DiscountService.java
│   │   │   ├── service/ExchangeRateService.java
│   │   │   ├── config/SecurityConfig.java
│   │   │   └── CurrencyDiscountServiceApplication.java
│   │   └── resources/
│   │       ├── application.properties
├── pom.xml
