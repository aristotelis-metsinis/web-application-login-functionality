# Web Application Login Functionality - Automation Testing Pre-interview Assessment

Test Automation Framework : Serenity BDD + Cucumber + Selenium WebDriver

## Overview
This project provides a robust, maintainable, and scalable end-to-end test automation framework for verifying the login functionality of a web application at https://practicetestautomation.com/practice-test-login using Serenity BDD, Cucumber, and Selenium WebDriver.

## Project Structure

```
web-application-login-functionality/
├── docs
├── src
│   └── test
│       ├── java
│       │   └── com/consoleconnect/pccwglobal
│       │       ├── runners
│       │       ├── steps
│       │       ├── pages
│       │       ├── hooks
│       │       └── common
│       └── resources
│           ├── features
│           ├── testData
│           ├── drivers
│           ├── config
│           └── serenity.conf
├── pom.xml
└── README.md
```

## Prerequisites
- Java 11 or higher
- Maven 3.6+
- Chrome and/or Firefox browsers installed
- ChromeDriver/GeckoDriver binaries (see `src/test/resources/drivers/`)

## Setup
1. Clone this repository.
2. Install dependencies:
   ```bash
   mvn clean install
   ```
3. Configure browser drivers if needed (see `src/test/resources/drivers/`).
4. Adjust environment configs in `src/test/resources/config/` as needed.

## Running Tests
- To run all tests :
  ```bash
  mvn clean verify
  ```
- To run all tests of a specific feature :
  ```bash
  mvn -P at -D environment=default-chrome clean verify -D cucumber.filter.tags="@feature:login"
  ```
- To run specific tests :
  ```bash
  mvn -P at -D environment=default-chrome clean verify -D cucumber.filter.tags="@id:login-001 or @id:login-002 or @id:login-003"
  ```
  
## Reports
- Serenity reports are generated in `target/site/serenity/` after test execution.

## Parallel Execution
- Parallel execution is enabled via `serenity.conf`.

## Project Highlights
- Page Object Model (POM) for maintainability
- Data-driven testing with Cucumber Scenario Outlines and CSV
- Comprehensive tagging for flexible test selection
- Explicit waits and robust assertions
- Cross-browser support

## Troubleshooting
- Check `target/site/serenity/` for detailed reports and screenshots on failure.
- Ensure browser drivers are compatible with your browser versions.

## Contribution
- Follow Java and Serenity coding standards.
- Add new features in separate branches and submit pull requests. 