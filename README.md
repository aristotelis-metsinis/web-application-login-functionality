# Web Application Login Functionality - Automation Testing Pre-interview Assessment

**Test Automation Framework:** Serenity BDD + Cucumber + Selenium WebDriver

---

## Overview
This project provides a **robust, maintainable, and scalable end-to-end test automation framework** for verifying the **login functionality** of the web application at [Practice Test Automation Login](https://practicetestautomation.com/practice-test-login) using **Serenity BDD**, **Cucumber**, and **Selenium WebDriver**.

---

## Project Structure

```
web-application-login-functionality
 ├── docs                                                     # Project documentation
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
 │         │                   └── steps                      # Step definition classes
 │         │                        └── loginpage             # Steps for login feature
 │         └── resources	 
 │              ├── features                                  # Cucumber feature files
 │              │    └── login                                # Login feature scenarios
 │              ├── webdriver                                 # WebDriver binaries
 │              │    └── linux                                # Linux-specific driver executables
 │	            ├── cucumber-with-serenity-tests-runner.vm    # VM template for runners
 │              └── serenity.conf                             # Serenity configuration file
 ├── .gitignore                                               # Git ignore rules
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
- **Run all tests for a specific feature**:
  ```bash
  mvn -P at -D environment=default-chrome clean verify -D cucumber.filter.tags="@feature:login"
  ```
- **Run specific tests by ID**:
  ```bash
  mvn -P at -D environment=default-chrome clean verify -D cucumber.filter.tags="@id:login-001 or @id:login-002 or @id:login-003"
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

## Project Highlights
- **Page Object Model (POM)** for maintainable and reusable page interactions
- **Data-driven testing** with Cucumber Scenario Outlines
- **Comprehensive tagging** for flexible test selection (positive, negative, security, exploratory)
- **Explicit waits and robust assertions** for stable test execution
- **Cross-browser support** (Chrome, Firefox, headless and GUI modes)

---

## Troubleshooting
- Check `target/site/serenity/` for detailed test reports and screenshots
- Ensure browser drivers match installed browser versions
- Use `-D environment=<env>` to switch between Chrome/Firefox and headless modes

---

## Contribution
- Follow **Java and Serenity coding standards**
- Add new features in separate branches and submit pull requests
- Maintain logging, waits, and reusable Page Objects