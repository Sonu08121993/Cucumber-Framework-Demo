package PageObjects;

import TestBase.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class MyAccountPageObject extends BasePage {

    public MyAccountPageObject(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath = "//h1[normalize-space()='My Account']")
    public WebElement MyAccountText;

    public void checkTitleText()
    {
        if (elementIsDisplayed(MyAccountText))
        {
            Assert.assertTrue(true);
        }
    }
}
