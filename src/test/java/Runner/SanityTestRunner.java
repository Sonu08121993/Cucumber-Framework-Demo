package Runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = "src/main/Feature",
        glue = {"StepDefinitions"},
        tags = "@sanity",
        plugin ={"pretty", "html:target/cucumber-reports", "json:target/cucumber-json"},
        monochrome = true,
        publish = true
)
public class SanityTestRunner extends AbstractTestNGCucumberTests {

    @DataProvider
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
