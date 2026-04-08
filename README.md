# 🧪 Swag Labs Test Automation Framework

A robust, scalable, and maintainable Test Automation Framework built from scratch to automate testing for the Swag Labs website using industry best practices.

---

## 🚀 Overview

This framework is designed using:
- Selenium WebDriver (Java)
- TestNG
- Page Object Model (POM)
- Fluent Design Pattern

---

## 🏗️ Project Structure
```
src
 ├── main/java
 │   ├── drivers
 │   ├── pages
 │   ├── utils
 │   └── listeners
 │
 ├── test/java
 │   └── tests
 │
 ├── test/resources
 │   └── test-data.json
 │
 └── main/resources
     ├── allure.properties
     ├── environment.properties
     ├── log4j.properties
     └── web.properties

test-outputs/
 ├── allure-results
 ├── logs
 ├── screenshots
```
---

## 🧩 Design Patterns Used

- Page Object Model (POM)
- Fluent Pattern
  - Enables readable and chained test steps:
```
loginPage.login("standard_user", "secret_sauce")
         .addProductToCart("Sauce Labs Backpack")
         .checkout();
```
- Factory Pattern
   - Used for initializing different browsers dynamically

---

## ⚙️ Key Features

🔹 Cross Browser Support

      - Chrome
      - Firefox
      - Edge
      
🔹 Test Execution

      - Powered by TestNG
      - Supports parallel execution
      
🔹 Reporting (Allure)

      - Detailed HTML reports
      - Test steps tracking
      - Attachments (screenshots & logs)

🔹 Logging

      - Log4j integration
      - Logs stored under:
          
          test-outputs/logs/
          
🔹 Screenshots

    Captured automatically:
       - On success
       - On failure
    Stored under:
    
            test-outputs/screenshots/
     
🔹 Test Data Management

      - External JSON file:
    
              test-data.json
    
🔹 Configuration Management

    - web.properties → browser & base URL
    - environment.properties → environment info
    - allure.properties → Allure setup
    - log4j.properties → logging config
 
 🔹 Utilities
 
    Custom-built utilities for:
    
     - Browser actions
     - Element interactions
     - Explicit & Fluent waits
     - Assertions
     - JSON handling
     - File operations
     - Logging
     - Timestamp generation
     - Terminal execution (for Allure reports)
---

## 🛠️ Tech Stack

- Java
- Selenium WebDriver
- TestNG
- Maven
- Allure Reports
- Log4j

---

## 📦 Setup & Installation

1. Clone the repository:
   ```
   git clone https://github.com/youssefzaafan/Automation-Testing-For-Swag-Labs.git
   ```

3. Install dependencies:
   ```
   mvn clean install

5. Configure:
```
   src/main/resources/web.properties
```
     Example:
     
         browser=chrome
         baseUrl=https://www.saucedemo.com/
      
---

## ▶️ Running Tests
```
mvn test
```
---

## 📊 Allure Report

```allure serve test-outputs/allure-results```

## 📁 Output Directory
```
 test-outputs/
 ├── allure-results/
 ├── logs/
 ├── screenshots/
```
## 🧪 Example Test Scenarios
  - Valid Login Test
  - Add Product to Cart
  - Checkout Flow
  - Fill User Information
  - Order Completion Validation
---
## 📌 Best Practices Applied

  - Clean Code principles
  - Reusable components
  - Separation of concerns
  - Externalized configurations
  - Scalable framework design
---

## 🔮 Future Enhancements
  - CI/CD Integration (GitHub Actions / Jenkins)
  - Docker support
  - API Testing integration
  - Retry mechanism for flaky tests
  - Parallel execution optimization
---

## 👨‍💻 Author

Youssef Zaafan

Software Testing Engineer
