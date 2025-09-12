package com.consoleconnect.pccwglobal.steps.loginpage;

import com.consoleconnect.pccwglobal.pages.landingpage.LandingPagePO;
import com.consoleconnect.pccwglobal.pages.loginpage.LoginPagePO;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;

/**
 * Step Definitions for the Login Page scenarios.
 *
 * This class provides the glue between Gherkin feature files and the Login/Landing
 * Page Objects. It handles all login-related steps, including entering credentials,
 * submitting the form, verifying successful login, and validating error messages.
 *
 * Responsibilities:
 * - Open the login page
 * - Enter username and password from feature file DataTables
 * - Submit the login form
 * - Verify landing page content after successful login
 * - Verify error messages after failed login attempts
 *
 * Author: QA Automation Team
 * Version: 1.0.0
 */
public class LoginPageSteps {
    /** Logger instance for tracking step execution and debugging information. */
    private static final Logger logger = LoggerFactory.getLogger(LoginPageSteps.class);

    /** Stores the username entered during the current scenario for verification purposes. */
    private String username;

    /** Step-injected Login Page Object for login interactions. */
    @Steps
    LoginPagePO loginPage;

    /** Step-injected Landing Page Object for post-login verification. */
    @Steps
    LandingPagePO landingPage;

    /**
     * Opens the login page in the browser.
     *
     * It ensures that the login page is loaded before any interactions occur.
     */
    @Given("^I am on the Login page$")
    public void iAmOnLoginPage() {
        logger.info("Opening the Login page");
        loginPage.open();
        logger.info("Login page opened successfully");
    }

    /**
     * Enters login credentials and submits the form.
     *
     * Accepts a Cucumber DataTable with "Username" and "Password" keys.
     *
     * @param table DataTable containing username and password
     */
    @When("^I login with the following credentials:$")
    public void iLoginWithCredentials(DataTable table) {
        logger.info("Logging in with credentials: {}", table);

        Map<String, String> data = table.asMap(String.class, String.class);
        this.username = data.getOrDefault("Username", "");
        String password = data.getOrDefault("Password", "");

        if (username == null) username = "";
        if (password == null) password = "";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        logger.info("Login form submitted for user '{}'", username);
    }

    /**
     * Verifies that the user is redirected to the landing page after successful login.
     *
     * Checks include:
     * - URL contains expected path segment
     * - Landing page header text
     * - Landing page content text personalized with username
     * - Presence of the "Log out" link
     */
    @Then("^I should be redirected to the landing page$")
    public void iShouldBeRedirectedToLandingPage() {
        logger.info("Verifying landing page after successful login");

        MatcherAssert.assertThat(
                "I should be on the landing page",
                Objects.requireNonNull(landingPage.getDriver().getCurrentUrl()),
                Matchers.containsString("logged-in-successfully")
        );
        MatcherAssert.assertThat(
                "Landing page should contain the expected header text",
                landingPage.getPostHeaderText(),
                Matchers.equalTo("Logged In Successfully")
                );
        MatcherAssert.assertThat(
                "Landing page should contain the expected content text",
                landingPage.getPostContentText(),
                Matchers.equalTo(String.format("Congratulations %s. You successfully logged in!", this.username))
        );
        MatcherAssert.assertThat(
                "Landing page should contain the expected log-out button",
                landingPage.getPostContentLink(),
                Matchers.equalTo("Log out")
        );

        logger.info("Landing page verification completed successfully");
    }

    /**
     * Verifies that the expected error message is displayed after a failed login attempt.
     *
     * @param expectedMessage Expected error message text
     */
    @Then("^I should see the error message \"([^\"]*)\"$")
    public void iShouldSeeTheErrorMessage(String expectedMessage) {
        logger.info("Verifying login error message: '{}'", expectedMessage);

        MatcherAssert.assertThat(
            "Error message should match",
            loginPage.getErrorMessage(),
            Matchers.equalTo(expectedMessage)
        );

        logger.info("Error message verification completed successfully");
    }
}