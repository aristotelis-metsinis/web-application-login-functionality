package com.consoleconnect.pccwglobal.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.annotations.Managed;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Cucumber hooks class that manages WebDriver lifecycle for test scenarios.
 *
 * Responsibilities:
 * - Initialize WebDriver before each Cucumber scenario.
 * - Clean up WebDriver resources after each scenario.
 * - Provide detailed logging for driver lifecycle events and scenario results.
 * - Integrate with Serenity's WebDriver management system.
 *
 * Usage:
 * This class is automatically invoked by Cucumber. The @Before and @After
 * annotations ensure hooks run at appropriate times during the test execution lifecycle.
 *
 * Author: QA Automation Team
 * Version: 1.0.0
 */
public class Hooks {
    /** Logger instance for tracking driver lifecycle events and debugging information. */
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    /**
     * Serenity-managed WebDriver instance.
     * The @Managed annotation ensures Serenity handles driver instantiation,
     * configuration, and basic lifecycle management.
     */
    @Managed
    WebDriver driver;

    /**
     * Executed before each Cucumber scenario.
     *
     * Ensures the WebDriver is initialized and ready for the upcoming scenario.
     * Logs detailed information about driver type and setup success.
     *
     * @throws RuntimeException if driver initialization fails
     */
    @Before("@feature:login")
    public void setUp() {
        logger.info("Initializing WebDriver for new test scenario");

        try {
            WebDriver currentDriver = Serenity.getWebdriverManager().getCurrentDriver();
            if (currentDriver != null) {
                logger.info("WebDriver successfully initialized: {}", currentDriver.getClass().getSimpleName());
                logger.debug("Driver session ID is available for debugging");
            } else {
                logger.warn("WebDriver initialization returned null - check Serenity configuration");
            }
        } catch (Exception e) {
            logger.error("Failed to initialize WebDriver during setup", e);
            throw new RuntimeException("WebDriver setup failed", e);
        }
    }

    /**
     * Executed after each Cucumber scenario.
     *
     * Cleans up WebDriver resources to prevent memory leaks and browser process accumulation.
     * Logs scenario result, driver termination status, and handles exceptions gracefully.
     *
     * @param scenario The completed Cucumber scenario, containing execution results
     */
    @After("@feature:login")
    public void tearDown(Scenario scenario) {
        logger.info("Scenario '{}' completed with status: {}", scenario.getName(), scenario.getStatus());

        try {
            if (driver != null) {
                logger.info("Closing WebDriver session");
                driver.quit();
                logger.info("WebDriver session successfully terminated");
            } else {
                logger.warn("WebDriver instance was null during teardown - no cleanup needed");
            }
        } catch (Exception e) {
            logger.error("Exception occurred during WebDriver cleanup", e);
        }

        if (scenario.isFailed()) {
            logger.warn("Scenario '{}' failed - additional investigation may be required", scenario.getName());
            logger.debug("Failed scenario details logged for debugging purposes");
        }
    }
}