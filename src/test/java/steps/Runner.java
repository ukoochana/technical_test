package steps;

/**
 * Created by ukoochana on 28/11/2019.
 */

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        plugin = {"pretty", "html:target/cucumber-html-report","json:cucumber.json"},
        glue={"steps"}
)
public class Runner {
}
