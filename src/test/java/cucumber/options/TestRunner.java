 package cucumber.options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features",
                 glue= {"stepdefination"},
                 plugin= {"pretty","html:target/cucumber","json:target/jsonReports/cucumber-report.json","junit:target/cucujunit.xml"})//,tags="@DeletePlace")
public class TestRunner {

}
