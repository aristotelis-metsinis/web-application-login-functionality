package com.consoleconnect.pccwglobal.steps.api;

import com.consoleconnect.pccwglobal.utils.JsonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Step Definitions for REST API CRUD operations using restful-api.dev.
 *
 * This class implements the following operations as part of a single scenario:
 * - Create a new object and extract its ID
 * - Update the created object using the stored ID
 * - Delete the object and verify deletion
 *
 * Assertions are performed after each operation, including verification
 * with a GET request to confirm the changes.
 *
 * Author: QA Automation Team
 * Version: 1.0.0
 */
public class ApiSteps {
    /** Logger instance for tracking step execution and debugging information. */
    private static final Logger logger = LoggerFactory.getLogger(ApiSteps.class);

    /** Base endpoint for restful-api.dev objects API. */
    private static final String BASE_URL = "https://api.restful-api.dev/objects";

    /** Path to the sample request body JSON file. */
    private static final String JSON_REQUEST_BODY_FILE_PATH ="src/test/resources/data/requestBody.json";

    /** Holds the object ID of the created resource for use in update and delete steps. */
    private String objectId;

    /**
     * Creates a new object using the API and stores its ID.
     * Also performs assertions on the response and verifies creation with a GET request.
     *
     * @throws IOException if the request body file cannot be read
     */
    @Given("^I create a new object$")
    public void iCreateANewObject() throws IOException {
        logger.info("Creating a new object using JSON body: {}", JSON_REQUEST_BODY_FILE_PATH);

        String requestBody = Files.readString(Paths.get(JSON_REQUEST_BODY_FILE_PATH));

        Response postResponse = SerenityRest.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post(BASE_URL)
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().response();

        objectId = postResponse.jsonPath().getString("id");

        logger.debug("Response from create: {}", postResponse.asPrettyString());
        logger.info("Object created successfully with ID: {}", objectId);

        assertThat("Object ID should not be null", objectId, notNullValue());
        assertThat(postResponse.jsonPath().getString("name"), equalTo("Test Object Name"));

        // Verify creation with GET
        Response getResponse = SerenityRest.given()
                .when()
                .get(BASE_URL + "/" + objectId)
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().response();

        assertThat(getResponse.jsonPath().getString("name"), equalTo("Test Object Name"));
        assertThat(getResponse.jsonPath().getInt("data.year"), equalTo(2019));
        assertThat(getResponse.jsonPath().getDouble("data.price"), equalTo(1849.99));
        assertThat(getResponse.jsonPath().getString("data.'CPU model'"), equalTo("Intel Core i9"));
        assertThat(getResponse.jsonPath().getString("data.'Hard disk size'"), equalTo("1 TB"));

        logger.info("Object with ID {} created successfully", objectId);
    }

    /**
     * Updates the object's name using its ID and verifies the update.
     *
     * @param newName the new name to update in the object
     * @throws IOException if the request body file cannot be read
     */
    @When("^I update the object name to \"(.+)\"$")
    public void iUpdateTheObjectName(String newName) throws IOException {
        logger.info("Updating object ID: {} with new name: {}", objectId, newName);

        Map<String, Object> requestMap = JsonUtils.readJsonAsMap(JSON_REQUEST_BODY_FILE_PATH);
        requestMap.put("name", newName);
        String requestBody = JsonUtils.toJsonString(requestMap);

        Response putResponse = SerenityRest.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().put(BASE_URL + "/" + objectId)
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().response();

        logger.debug("Response from update: {}", putResponse.asPrettyString());

        assertThat(putResponse.jsonPath().getString("name"), equalTo("Test Object Updated Name"));

        // Verify update with GET
        Response getResponse = SerenityRest.given()
                .when()
                .get(BASE_URL + "/" + objectId)
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().response();

        assertThat(getResponse.jsonPath().getString("name"), equalTo("Test Object Updated Name"));
        assertThat(getResponse.jsonPath().getInt("data.year"), equalTo(2019));
        assertThat(getResponse.jsonPath().getDouble("data.price"), equalTo(1849.99));
        assertThat(getResponse.jsonPath().getString("data.'CPU model'"), equalTo("Intel Core i9"));
        assertThat(getResponse.jsonPath().getString("data.'Hard disk size'"), equalTo("1 TB"));

        logger.info("Object with ID {} updated successfully", objectId);
    }

    /**
     * Deletes the object using its ID and verifies that it no longer exists.
     */
    @Then("^I delete the object$")
    public void iDeleteTheObject() {
        logger.info("Deleting object with ID: {}", objectId);

        Response deleteResponse = SerenityRest.given()
                .when().delete(BASE_URL + "/" + objectId)
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().response();

        logger.debug("Response from delete: {}", deleteResponse.asPrettyString());

        assertThat(deleteResponse.jsonPath().getString("message"),
                equalTo(String.format("Object with id = %s has been deleted.", objectId)));

        // Verify deletion with GET (expect 404)
        Response getResponse = SerenityRest.given()
                .when()
                .get(BASE_URL + "/" + objectId)
                .then()
                .contentType("application/json")
                .extract().response();

        assertThat(getResponse.statusCode(), equalTo(404));

        logger.info("Object with ID {} deleted successfully", objectId);
    }
}