# Web Application Login & API CRUD Functionality - Automation Testing Pre-interview Assessment

**Test Automation Framework:** Serenity BDD + Cucumber + Selenium WebDriver + Rest-Assured

---

## Overview
This project provides a **robust, maintainable, and scalable end-to-end test automation framework** for verifying both **UI** and **API** functionality:

- **UI Automation**: Login functionality of [Practice Test Automation Login](https://practicetestautomation.com/practice-test-login) using **Serenity BDD**, **Cucumber**, and **Selenium WebDriver**.
- **API Automation**: CRUD operations against [Restful API](https://restful-api.dev/) using **Serenity BDD**, **Cucumber**, and **Rest-Assured**.

---

## Project Structure

```
web-application-login-functionality
 ├── docs                                                     # Project documentation (requirements, assignment details, etc.)
 ├── src
 │    └── test
 │         ├── java
 │         │    └── com
 │         │         └── consoleconnect
 │         │              └── pccwglobal
 │         │                   ├── hooks                      # Cucumber hooks (setup/teardown)
 │         │                   ├── pages                      # Page Object Model classes
 │         │                   │    ├── landingpage           # Landing page PO class
 │         │                   │    └── loginpage             # Login page PO class
 │         │                   ├── steps                      # Step definition classes
 │         │                   │    ├── api                   # Steps for API CRUD scenarios
 │         │                   │    └── loginpage             # Steps for login feature
 │         │                   └── utils                      # Utility classes (e.g., JSON manipulation)
 │         └── resources	 
 │              ├── data                                      # Test data files (e.g., API request bodies)
 │              ├── features                                  # Cucumber feature files
 │              │    ├── api                                  # API feature scenarios
 │              │    └── login                                # Login feature scenarios
 │              ├── webdriver                                 # WebDriver binaries
 │              │    └── linux                                # Linux-specific driver executables
 │              ├── cucumber-with-serenity-tests-runner.vm    # VM template for runners
 │              └── serenity.conf                             # Serenity configuration file
 ├── .gitignore                                               # Git ignore rules
 ├── Jenkinsfile_LoginFunctionality                           # Builds the project, runs Serenity tests, and publishes HTML reports
 ├── pom.xml                                                  # Maven project configuration
 └── README.md                                                # Project overview / instructions
```

---

## Prerequisites
- **Java 11+**
- **Maven 3.6+**
- **Chrome and/or Firefox browsers installed**
- **ChromeDriver / GeckoDriver binaries** (see `src/test/resources/webdriver/`)

---

## Setup
1. Clone the repository:
   ```bash
   git clone <repository-url>
   ``` 
2. Install dependencies:
   ```bash
   mvn clean install
   ```
3. Configure browser drivers if needed (see `src/test/resources/webdriver/`).
4. Adjust environment settings in `src/test/resources/serenity.conf` as needed.

---

## Running Tests
- **Run all tests**:
  ```bash
  mvn clean verify
  ```
- **Run all tests of login feature**:
  ```bash
  mvn -P at -D environment=default-chrome clean verify -D cucumber.filter.tags="@feature:login"
  ```
- **Run specific tests by ID**:
  ```bash
  mvn -P at -D environment=default-chrome clean verify -D cucumber.filter.tags="@id:login-001 or @id:login-002 or @id:login-003"
  ```
- **Run only API tests**:
  ```bash
  mvn -P at -D environment=default-chrome clean verify -D cucumber.filter.tags="@feature:api"
  ```

---
  
## Reports
- Serenity HTML reports are generated in: 
 ```
 target/site/serenity/
 ``` 
- Includes step-by-step screenshots and logs for debugging failures.

---

## Parallel Execution
- Parallel execution is enabled via `serenity.conf` and Maven Failsafe plugin.
- Can run across multiple browsers or environments concurrently.

---

## Tools & Libraries
- **Serenity BDD**: Reporting and structured BDD framework
- **Cucumber**: Behavior-driven development framework
- **Selenium WebDriver**: Browser automation
- **Rest-Assured**: API testing (CRUD operations)
- **Jackson**: JSON parsing and manipulation utilities

---

## Project Highlights
- **Page Object Model (POM)** for maintainable and reusable page interactions
- **Data-driven testing** with Cucumber Scenario Outlines
- **Comprehensive tagging** for flexible test selection (positive, negative, security, exploratory)
- **Explicit waits and robust assertions** for stable test execution
- **Cross-browser support** (Chrome, Firefox, headless and GUI modes)
- **API testing support** with **Rest-Assured** for CRUD validations
- **Reusable JSON utilities** for dynamic request body manipulation
- **Centralized test data** in `/data` folder for consistency and reuse

---

## Troubleshooting
- Check `target/site/serenity/` for detailed test reports and screenshots
- Ensure browser drivers match installed browser versions
- Use `-D environment=<env>` to switch between Chrome/Firefox and headless modes

---

## Documentation
All project requirements, design decisions, and assignment details can be found in the `docs/` folder.

---

## Contribution
- Follow **Java and Serenity coding standards**
- Add new features in separate branches and submit pull requests
- Maintain logging, waits, and reusable Page Objects
- Keep API tests data-driven and reusable with `JsonUtils` and `/data` folder 