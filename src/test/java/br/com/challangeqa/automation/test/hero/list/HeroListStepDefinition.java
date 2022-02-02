package br.com.challangeqa.automation.test.hero.list;

import br.com.challangeqa.automation.enums.Messages;
import br.com.challangeqa.automation.model.response.ResponseObject;
import br.com.challangeqa.automation.model.response.hero.HeroResponse;
import br.com.challangeqa.automation.service.AuthenticationService;
import br.com.challangeqa.automation.service.HeroListService;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.junit.Assert.*;

public class HeroListStepDefinition {

    private AuthenticationService authenticationService;
    private HeroListService heroListService;
    private String token;
    private List<HeroResponse> heroesList;
    private ResponseObject responseObject;
    private boolean isValidSchema;

    @Before("@init")
    public void init() {

        authenticationService = new AuthenticationService();
        heroListService = new HeroListService();
    }

    @Given("I have a valid token")
    public void i_have_a_valid_token() {

        token = authenticationService.getToken();
    }

    @Given("I call get heroes list API without header")
    public void i_call_get_heroes_list_API_without_header() {

        responseObject = heroListService.getHeroesListWithNoHeader();
    }

    @Given("I call get heroes list API with invalid header")
    public void i_call_get_heroes_list_API_with_invalid_header() {

        responseObject = heroListService.getHeroesListWithInvalidHeader();
    }

    @When("I call get heroes list API")
    public void i_call_get_heroes_list_API() {

        heroesList = heroListService.getHeroesList(token);
    }

    @When("I get API schema")
    public void i_get_API_schema() {

        isValidSchema = heroListService.validateSchema();
    }

    @Then("should return list data")
    public void should_return_list_data() {

        heroesList.forEach(hero -> {

            assertNotNull(hero.getId());
            assertNotEquals("", hero.getId());

            assertNotNull(hero.getName());
            assertNotEquals("", hero.getName());

            assertNotNull(hero.getSkills());
            assertTrue(hero.getSkills().getPower() >= 0 && hero.getSkills().getPower() <= 200);
            assertTrue(hero.getSkills().getVelocity() >= 0 && hero.getSkills().getVelocity() <= 200);
            assertTrue(hero.getSkills().getCombat() >= 0 && hero.getSkills().getCombat() <= 200);
        });
    }

    @Then("list result should return status code {int}")
    public void list_result_should_return_status_code(int code) {

        HeroResponse heroResponse = heroesList.stream()
                .filter(hero -> hero.getStatusCode() != 0)
                .findFirst()
                .get();

        assertEquals(code, heroResponse.getStatusCode());
    }

    @Then("schema should be valid")
    public void schema_should_be_valid() {

        assertTrue(isValidSchema);
    }

    @Then("should return message error")
    public void should_return_message_error() {

        assertEquals(Messages.UNAUTHORIZED.getMessage(), responseObject.getMessage());
    }

    @Then("status code should be {int}")
    public void status_code_should_be(int code) {

        assertEquals(code, responseObject.getStatusCode());
    }
}
