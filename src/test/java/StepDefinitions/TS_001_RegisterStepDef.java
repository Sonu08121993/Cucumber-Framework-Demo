package StepDefinitions;

import PageObjects.HomePageObject;
import PageObjects.MyAccountPageObject;
import PageObjects.RegisterPageObject;
import TestBase.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class TS_001_RegisterStepDef {

    HomePageObject hPage;
    RegisterPageObject rPage;
    MyAccountPageObject myAccPage;

    @Given("I am on opencart homepage")
    public void I_am_on_opencart_homepage()
    {
        hPage = new HomePageObject(BaseClass.driver);
        rPage = new RegisterPageObject(BaseClass.driver);
        myAccPage = new MyAccountPageObject(BaseClass.driver);
        hPage.getHomepageTitle();
    }

    @Given("I Clicked on My Account Drop down")
    public void I_Clicked_on_My_Account_Drop_down()
    {
        hPage.clickOnMyAccountDropdown();
    }
    @Given("I Clicked on Register option")
    public void I_Clicked_on_Register_option()
    {
        hPage.clickOnRegisterOption();
    }
    @Given("I am on Register page")
    public void I_am_on_Register_page()
    {
        rPage.getRegisterPageTitle();
    }
    @Given("I Entered new Account Details into the Mandatory Fields")
    public void I_Entered_new_Account_Details_into_the_Mandatory_Fields()
    {
        rPage.inputFirstname();
        rPage.inputLastname();
        rPage.inputEmail();
        rPage.inputPassword();
        rPage.checkPrivacyPolicy();
    }
    @When("I Clicked on Continue button")
    public void I_Clicked_on_Continue_button()
    {
        rPage.clickToRegisterContinue();
    }
    @When("I Clicked on Continue button that is displayed in the Account Success page")
    public void I_Clicked_on_Continue_button_that_is_displayed_in_the_Account_Success_page()
    {
        rPage.clickToAccountSuccessContinue();
    }
    @Then("I should be taken to Account page")
    public void I_should_be_taken_to_Account_page()
    {
        myAccPage.checkTitleText();
    }



    



















}
