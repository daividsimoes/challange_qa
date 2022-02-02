package br.com.challangeqa.automation.test.hero.list;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:/features/hero/hero_list.feature"},
        glue = {"br.com.challangeqa.automation.test.hero.list"},
        plugin = {"pretty", "json:target/cucumber/report/hero_list.json"}
)
public class HeroListRunnetTest {
}
