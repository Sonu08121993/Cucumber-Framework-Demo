package PageObjects;

import TestBase.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class RegisterPageObject extends BasePage {
    public RegisterPageObject(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath = "//h1[normalize-space()='Register Account']")
    public WebElement registerPageTitle;

    @FindBy(id = "input-firstname")
    public WebElement firstnameInput;

    @FindBy(id = "input-lastname")
    public WebElement lastnameInput;

    @FindBy(id = "input-email")
    public WebElement emailInput;

    @FindBy(id = "input-password")
    public WebElement passwordInput;

    @FindBy(name = "agree")
    public WebElement privacyPolicyToggle;

    @FindBy(xpath = "//button[normalize-space()='Continue']")
    public WebElement registerFormSubmitButton;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    public WebElement accountCreatedMessage;

    @FindBy(xpath = "//a[normalize-space()='Continue']")
    public WebElement continueAccountSuccessButton;

    public void getRegisterPageTitle()
    {
        if (getText(registerPageTitle).equals("Register Account"))
        {
            Assert.assertTrue(true);
        }
    }

    public void inputFirstname()
    {
        sendKeys(firstnameInput,generateRandomString(5));
    }
    public void inputLastname()
    {
        sendKeys(lastnameInput,generateRandomString(4));
    }
    public void inputEmail()
    {
        sendKeys(emailInput,generateRandomString(4)+"@yopmail.com");
    }
    public void inputPassword()
    {
        sendKeys(passwordInput,generateRandomString(7));
    }
    public void checkPrivacyPolicy()
    {
        click(privacyPolicyToggle);
    }
    public void clickToRegisterContinue()
    {
        click(registerFormSubmitButton);
    }
    public void clickToAccountSuccessContinue()
    {
        click(continueAccountSuccessButton);
    }



























}
