package br.com.challangeqa.automation.test.authentication;

import br.com.challangeqa.automation.constant.ConstantString;
import br.com.challangeqa.automation.enums.Messages;
import br.com.challangeqa.automation.model.response.authentication.AuthenticationResponse;
import br.com.challangeqa.automation.service.AuthenticationService;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.*;

public class AuthenticationStepDefinition {

    private AuthenticationService authenticationService;
    private AuthenticationResponse authenticate;
    private boolean isValidSchema;

    @Before("@init")
    public void before() {

        authenticationService = new AuthenticationService();
    }

    @Given("I call authentication API")
    public void i_have_a_valid_header() {

        authenticate = authenticationService.authenticate();
    }

    @Given("I call authentication API without header")
    public void i_have_a_valid_header_without_header() {

        authenticate = authenticationService.authenticateWithNoHeader();
    }

    @Given("I call authentication API with invalid header")
    public void i_have_a_valid_header_with_invalid_header() {

        authenticate = authenticationService.authenticateInvalidHeader();
    }

    @When("I get API schema")
    public void i_get_API_schema() {

        isValidSchema = authenticationService.validateSchema();
    }

    @Then("should return authentication data")
    public void should_return_authentication_data() {

        assertTrue(authenticate.isAuth());
        assertNotNull(authenticate.getToken());
        assertNotEquals("", authenticate.getToken());
    }

    @Then("status code should be {int}")
    public void status_code_should_be(int code) {

        assertEquals(code, authenticate.getStatusCode());
    }

    @Then("schema should be valid")
    public void schema_should_be_valid() {

        assertTrue(isValidSchema);
    }

    @Then("should return message error")
    public void should_return_message_error() {

        assertEquals(Messages.UNAUTHORIZED.getMessage(),authenticate.getMessage());
    }
}
