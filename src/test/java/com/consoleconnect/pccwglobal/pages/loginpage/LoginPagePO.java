package com.consoleconnect.pccwglobal.pages.loginpage;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class LoginPagePO extends PageObject {

    @FindBy(id = "username")
    WebElementFacade usernameField;

    @FindBy(id = "password")
    WebElementFacade passwordField;

    @FindBy(id = "submit")
    WebElementFacade loginButton;

    @FindBy(id="error")
    WebElementFacade errorMessage;

    public void enterUsername(String username) {
        typeInto(usernameField, username);
    }

    public void enterPassword(String password) {
        typeInto(passwordField, password);
    }

    public void clickLogin() {
        clickOn(loginButton);
    }

    public String getErrorMessage() {
        return errorMessage.waitUntilVisible().getText();
    }
} 