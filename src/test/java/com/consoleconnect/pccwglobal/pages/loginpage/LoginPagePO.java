package com.consoleconnect.pccwglobal.pages.loginpage;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Page Object representing the Login Page of the Practice Test Automation website.
 *
 * This class encapsulates all web elements and interactions required to perform
 * login operations. It follows the Page Object Model (POM) pattern to provide a
 * clean interface for test scenarios, abstracting low-level Selenium interactions.
 *
 * Responsibilities:
 * - Enter username and password into login form fields.
 * - Submit login form.
 * - Retrieve error messages displayed upon failed login attempts.
 * - Provide logging for page interactions and debugging purposes.
 *
 * Author: QA Automation Team
 * Version: 1.0.0
 */
public class LoginPagePO extends PageObject {
    /** Logger instance for tracking page interactions and debugging information. */
    private static final Logger logger = LoggerFactory.getLogger(LoginPagePO.class);

    /** Web element representing the username input field. */
    @FindBy(id = "username")
    WebElementFacade usernameField;

    /** Web element representing the password input field. */
    @FindBy(id = "password")
    WebElementFacade passwordField;

    /** Web element representing the login button. */
    @FindBy(id = "submit")
    WebElementFacade loginButton;

    /** Web element representing the error message displayed after failed login. */
    @FindBy(id="error")
    WebElementFacade errorMessage;

    /**
     * Enters the provided username into the username input field.
     *
     * Wait Strategy: Waits until the username field is visible before interaction.
     *
     * @param username The username string to enter
     * @throws RuntimeException if the username field is not visible or interaction fails
     */
    public void enterUsername(String username) {
        logger.info("Entering username: '{}'", username);

        try {
            usernameField.waitUntilVisible().type(username);
            logger.info("Username entered successfully");
        } catch (Exception e) {
            logger.error("Failed to enter username", e);
            throw new RuntimeException("Unable to enter username", e);
        }
    }

    /**
     * Enters the provided password into the password input field.
     *
     * Wait Strategy: Waits until the password field is visible before interaction.
     *
     * @param password The password string to enter
     * @throws RuntimeException if the password field is not visible or interaction fails
     */
    public void enterPassword(String password) {
        logger.info("Entering password");

        try {
            passwordField.waitUntilVisible().type(password);
            logger.info("Password entered successfully");
        } catch (Exception e) {
            logger.error("Failed to enter password", e);
            throw new RuntimeException("Unable to enter password", e);
        }
    }

    /**
     * Clicks the login button to submit the login form.
     *
     * Wait Strategy: Waits until the login button is clickable before interaction.
     *
     * @throws RuntimeException if the login button is not clickable or interaction fails
     */
    public void clickLogin() {
        logger.info("Clicking login button");

        try {
            loginButton.waitUntilClickable().click();
            logger.info("Login button clicked successfully");
        } catch (Exception e) {
            logger.error("Failed to click login button", e);
            throw new RuntimeException("Unable to click login button", e);
        }
    }

    /**
     * Retrieves the error message displayed after a failed login attempt.
     *
     * Wait Strategy: Waits until the error message element is visible before extracting text.
     *
     * @return String containing the error message text
     * @throws RuntimeException if the error message element is not visible or interaction fails
     */
    public String getErrorMessage() {
        logger.info("Retrieving login error message");

        try {
            String errorText = errorMessage.waitUntilVisible().getText().trim();
            logger.info("Retrieved error message: '{}'", errorText);
            return errorText;
        } catch (Exception e) {
            logger.error("Failed to retrieve login error message", e);
            throw new RuntimeException("Unable to get login error message", e);
        }
    }
}