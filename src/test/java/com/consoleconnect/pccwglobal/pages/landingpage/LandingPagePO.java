package com.consoleconnect.pccwglobal.pages.landingpage;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class LandingPagePO extends PageObject {

    @FindBy(xpath = "//div[contains(@class,'post-header')]")
    WebElementFacade postHeaderDiv;

    @FindBy(xpath = "//div[contains(@class,'post-content')]//p")
    WebElementFacade postContentDivParagraph;

    @FindBy(xpath = "//div[contains(@class,'post-content')]//a")
    WebElementFacade postContentDivLink;

    public String getPostHeaderText() {
        return postHeaderDiv.waitUntilVisible().getText().trim();
    }

    public String getPostContentText() {
        return postContentDivParagraph.waitUntilVisible().getText().trim();
    }

    public String getPostContentLink() {
        return postContentDivLink.waitUntilVisible().getText().trim();
    }
} 