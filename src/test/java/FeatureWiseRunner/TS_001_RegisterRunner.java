package FeatureWiseRunner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

@CucumberOptions(features = "src/main/Feature/TS_001_Register.feature",
        glue = {"StepDefinitions","TestBase"},
        plugin ={"pretty", "html:target/cucumber-reports",
                "json:target/cucumber-json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true,
        publish = true
)
public class TS_001_RegisterRunner extends AbstractTestNGCucumberTests {

    @Parameters({"os", "browser"})
    @BeforeClass
    public void setUp(String os, String browser) {
        System.setProperty("os", os);
        System.setProperty("browser", browser);
    }

    @DataProvider(parallel = true)
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
