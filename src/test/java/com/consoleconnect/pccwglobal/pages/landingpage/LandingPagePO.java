package com.consoleconnect.pccwglobal.pages.landingpage;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Page Object representing the Landing Page of the Practice Test Automation website.
 *
 * This class encapsulates all web elements and interactions available on the landing page
 * at https://practicetestautomation.com/practice-test-login. It follows the Page Object
 * Model pattern to provide a clean interface for test scenarios to interact with the page
 * without dealing with low-level WebDriver operations.
 *
 * Responsibilities:
 * - Retrieve post header content and metadata.
 * - Extract post content paragraph text for verification.
 * - Access navigation links within content.
 * - Implement wait strategies for reliable element interactions.
 *
 * Page Structure:
 * - Post header section containing page title/heading.
 * - Post content section with descriptive text.
 * - Navigation links embedded within content.
 *
 * Author: QA Automation Team
 * Version: 1.0.0
 */
public class LandingPagePO extends PageObject {
    /** Logger instance for tracking page interactions and debugging information. */
    private static final Logger logger = LoggerFactory.getLogger(LandingPagePO.class);

    /** Web element representing the post header section of the landing page. */
    @FindBy(xpath = "//div[contains(@class,'post-header')]")
    WebElementFacade postHeaderDiv;

    /** Web element representing the paragraph text within the post content section. */
    @FindBy(xpath = "//div[contains(@class,'post-content')]//p")
    WebElementFacade postContentDivParagraph;

    /** Web element representing navigation links within the post content section. */
    @FindBy(xpath = "//div[contains(@class,'post-content')]//a")
    WebElementFacade postContentDivLink;

    /**
     * Retrieves the text content from the post header section.
     *
     * Wait Strategy: Waits until the post header element is visible before interaction.
     *
     * @return String containing the post header text, trimmed of whitespace
     * @throws RuntimeException if element cannot be located or is not visible
     */
    public String getPostHeaderText() {
        logger.info("Attempting to retrieve post header text from landing page");

        try {
            String headerText = postHeaderDiv.waitUntilVisible().getText().trim();
            logger.info("Successfully retrieved post header text: '{}'", headerText);
            return headerText;
        } catch (Exception e) {
            logger.error("Failed to retrieve post header text from landing page", e);
            throw new RuntimeException("Unable to get post header text", e);
        }
    }

    /**
     * Retrieves the paragraph text content from the post content section.
     *
     * Wait Strategy: Waits until the post content paragraph element is visible before interaction.
     *
     * @return String containing the post content paragraph text, trimmed of whitespace
     * @throws RuntimeException if element cannot be located or is not visible
     */
    public String getPostContentText() {
        logger.info("Attempting to retrieve post content paragraph text from landing page");

        try {
            String contentText = postContentDivParagraph.waitUntilVisible().getText().trim();
            logger.info("Successfully retrieved post content text: '{}'", contentText);
            return contentText;
        } catch (Exception e) {
            logger.error("Failed to retrieve post content paragraph text from landing page", e);
            throw new RuntimeException("Unable to get post content text", e);
        }
    }

    /**
     * Retrieves the link text from navigation links within the post content section.
     *
     * Wait Strategy: Waits until the post content link element is visible before interaction.
     * Note: If multiple links exist, this method returns the text from the first matching element.
     *
     * @return String containing the link text, trimmed of whitespace
     * @throws RuntimeException if element cannot be located or is not visible
     */
    public String getPostContentLink() {
        logger.info("Retrieving post content link text from landing page");

        try {
            String linkText = postContentDivLink.waitUntilVisible().getText().trim();
            logger.info("Retrieved post content link text: '{}'", linkText);
            return linkText;
        } catch (Exception e) {
            logger.error("Failed to retrieve post content link text from landing page", e);
            throw new RuntimeException("Unable to get post content link text", e);
        }
    }
}