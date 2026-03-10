package TestBase;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.jsoup.Connection;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.IOException;

import static TestBase.BaseClass.driver;

public class Hooks {
    BaseClass base = new BaseClass();

    @Before
    public void config() throws IOException {

        String os = System.getProperty("os");
        String browser = System.getProperty("browser");

        base.setUp(os, browser);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        driver.quit();
    }
}
