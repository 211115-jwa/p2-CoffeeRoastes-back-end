package p2CoffeeRoastersvanquishbackend.cucumberConfig;





import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * To run cucumber test.
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", plugin = {"pretty", "json:target/cucumber-report.json"})
public class CucumberTest {

}