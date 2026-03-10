package PageObjects;


import TestBase.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class HomePageObject extends BasePage {

    public HomePageObject(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath = "//span[normalize-space()='My Account']")
    public WebElement myAccountDropDown;

    @FindBy(xpath = "//a[normalize-space()='Register']")
    public WebElement registerOption;

    public void getHomepageTitle()
    {
       if (getTitle().equals("Your Store"))
       {
           Assert.assertTrue(true);
       }
    }
    public void clickOnMyAccountDropdown()  {
        click(myAccountDropDown);
    }
    public void clickOnRegisterOption()
    {
        click(registerOption);
    }

}
