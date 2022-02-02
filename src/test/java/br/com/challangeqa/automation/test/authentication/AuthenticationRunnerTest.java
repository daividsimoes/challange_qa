package br.com.challangeqa.automation.test.authentication;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:/features/authentication"},
        glue = {"br.com.challangeqa.automation.test.authentication"},
        plugin = {"pretty", "json:target/cucumber/authentication.json"}
)
public class AuthenticationRunnerTest {
}
