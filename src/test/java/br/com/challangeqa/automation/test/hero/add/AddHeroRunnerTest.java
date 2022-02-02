package br.com.challangeqa.automation.test.hero.add;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:/features/hero/add_hero.feature"},
        glue = {"br.com.challangeqa.automation.test.hero.add"},
        plugin = {"pretty", "json:target/cucumber/report/add_hero.json"}
)
public class AddHeroRunnerTest {
}
