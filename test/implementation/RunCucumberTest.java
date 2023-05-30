package implementation;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@CucumberOptions(
        monochrome = true,
        tags = "@tags",
        //plugin = {"pretty", "html:target/cucumber-report.html"},
        features = {"test/resources"},
        glue = {"implementation.hietetlen.cucumber"}
)
@RunWith(Cucumber.class)
public class RunCucumberTest {
}