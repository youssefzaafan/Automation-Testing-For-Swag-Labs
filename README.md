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

---

## 🧩 Design Patterns Used

- Page Object Model (POM)
- Fluent Pattern
- Factory Pattern

---

## ⚙️ Key Features

- Cross-browser support (Chrome, Firefox, Edge)
- Allure reporting with screenshots
- Log4j logging
- JSON test data handling
- Configurable properties files
- Utilities for waits, actions, assertions, and more

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
   git clone <your-repo-url>

2. Install dependencies:
   mvn clean install

3. Configure:
   src/main/resources/web.properties

---

## ▶️ Running Tests

mvn test

---

## 📊 Allure Report

allure serve test-outputs/allure-results

---

## 👨‍💻 Author

Youssef Zaafan
