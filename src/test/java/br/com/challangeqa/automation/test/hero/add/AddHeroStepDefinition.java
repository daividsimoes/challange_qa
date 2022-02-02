package br.com.challangeqa.automation.test.hero.add;

import br.com.challangeqa.automation.enums.Messages;
import br.com.challangeqa.automation.factory.hero.AddHeroFactory;
import br.com.challangeqa.automation.model.request.hero.AddHeroRequest;
import br.com.challangeqa.automation.model.response.hero.HeroResponse;
import br.com.challangeqa.automation.service.AddHeroService;
import br.com.challangeqa.automation.service.AuthenticationService;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.*;

public class AddHeroStepDefinition {

    private AuthenticationService authenticationService;
    private AddHeroService addHeroService;
    private AddHeroFactory addHeroFactory;
    private AddHeroRequest addHeroRequest;
    private HeroResponse heroResponse;
    private String token;
    private boolean isValidSchema;

    @Before("@init")
    public void init(){

        authenticationService = new AuthenticationService();
        addHeroService = new AddHeroService();
        addHeroFactory = new AddHeroFactory();
    }

    @Given("I have one hero performed")
    public void i_have_one_hero_performed() {

        addHeroRequest = addHeroFactory.buildHero(190, 150, 170);
    }

    @Given("I have a valid token")
    public void i_have_a_valid_token() {

        token = authenticationService.getToken();
    }

    @Given("I already have one hero added")
    public void i_already_have_one_hero_added() {

        addHeroRequest = addHeroFactory.buildHero(190, 150, 170);
        token = authenticationService.getToken();
        addHeroService.addHero(token, addHeroRequest);
    }

    @When("I call add hero API")
    public void i_call_add_hero_API() {

        heroResponse = addHeroService.addHero(token, addHeroRequest);
    }

    @When("I get API schema")
    public void i_get_API_schema() {

        isValidSchema = addHeroService.validateSchema();
    }

    @Then("should add hero successfully")
    public void should_add_hero_successfully() {

        assertNotNull(heroResponse.getId());
        assertNotEquals("", heroResponse.getId());

        assertEquals(addHeroRequest.getName(), heroResponse.getName());
        assertEquals(addHeroRequest.getSkills().getPower(), heroResponse.getSkills().getPower());
        assertEquals(addHeroRequest.getSkills().getVelocity(), heroResponse.getSkills().getVelocity());
        assertEquals(addHeroRequest.getSkills().getCombat(), heroResponse.getSkills().getCombat());
    }

    @Then("status code should be {int}")
    public void status_code_should_be(int code) {

        assertEquals(code, heroResponse.getStatusCode());
    }

    @Then("schema should be valid")
    public void schema_should_be_valid() {

        assertTrue(isValidSchema);
    }

    @Then("should return message error")
    public void should_return_message_error() {

        assertEquals(Messages.HERO_ALREADY_EXISTS.getMessage(), heroResponse.getMessage());
    }
}
