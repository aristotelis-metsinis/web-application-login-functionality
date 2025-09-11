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

public class LoginPageSteps {
    private static final Logger logger = LoggerFactory.getLogger(LoginPageSteps.class);

    private String username;

    @Steps
    LoginPagePO loginPage;

    @Steps
    LandingPagePO landingPage;

    @Given("^I am on the Login page$")
    public void iAmOnLoginPage() {        
        loginPage.open();
    }

    @When("^I login with the following credentials:$")
    public void iLoginWithCredentials(DataTable table) {
        logger.info("Login with credentials: {}", table);

        Map<String, String> data = table.asMap(String.class, String.class);
        this.username = data.getOrDefault("Username", "");
        String password = data.getOrDefault("Password", "");
        if (username == null) username = "";
        if (password == null) password = "";
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }

    @Then("^I should be redirected to the landing page$")
    public void iShouldBeRedirectedToLandingPage() {
        logger.info("I should be redirected to the landing page");

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
    }

    @Then("^I should see the error message \"([^\"]*)\"$")
    public void iShouldSeeTheErrorMessage(String expectedMessage) {
        logger.info("I should see the error message: {}", expectedMessage);

        MatcherAssert.assertThat(
            "Error message should match",
            loginPage.getErrorMessage(),
            Matchers.equalTo(expectedMessage)
        );
    }
} 