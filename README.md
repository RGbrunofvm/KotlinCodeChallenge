# API Consumer with RestAssured

This Kotlin project demonstrates how to consume a RESTful API using RestAssured. It fetches a list of users, filters them based on their city, and validates the output using unit tests.

---

## Features
- Fetches user data from the [JSONPlaceholder API](https://jsonplaceholder.typicode.com/users).
- Filters users by city (`Gwenborough`).
- Uses RestAssured for HTTP requests.
- Implements JSON deserialization with Jackson.
- Includes unit tests to validate functionality.

---

## Requirements
- **JDK**: 17 or higher
- **Gradle**: 8.1 or higher (use Gradle Wrapper if Gradle is not installed)
- **Internet Connection**: Required to fetch data from the API.

---

## Installation and Setup

### Clone the Repository
Clone this repository to your local environment:
```bash
git clone <repository-url>
cd <repository-directory>
```
---

## Running the Application

### Step 1: Execute the Program
To fetch and filter user data:
```bash
./gradlew run
```
Expected output
```
Filtered Users:
Name: Leanne Graham, Email: Sincere@april.biz, City: Gwenborough
```
### Step 2: View Test Results
After running the tests, open the detailed report:
```
build/reports/tests/test/index.html
```
