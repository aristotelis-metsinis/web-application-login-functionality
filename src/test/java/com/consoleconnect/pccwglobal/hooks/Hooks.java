package com.consoleconnect.pccwglobal.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.annotations.Managed;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Managed
    WebDriver driver;

    @Before
    public void setUp() {
        logger.info("Setting up the driver");
        // Serenity will handle WebDriver setup
        Serenity.getWebdriverManager().getCurrentDriver();
    }

    @After
    public void tearDown() {
        logger.info("Tearing down the driver");
        if (driver != null) {
            driver.quit();
        }
        logger.info("Driver is closed");
    }
} 