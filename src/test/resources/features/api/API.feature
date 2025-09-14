@feature:api @testing:regression @testing:acceptance
Feature: API CRUD operations
  As a user
  I want to use the REST API
  So that I can create, update, retrieve and delete custom objects with various attributes of different types

  @feature:api @id:api-001 @scenario:create_update_delete_object @testing:acceptance @type:positive_test
  Scenario: Create, update and delete object
    Given I create a new object
    When I update the object name to "Test Object Updated Name"
    Then I delete the object