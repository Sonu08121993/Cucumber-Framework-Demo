package TestBase;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class BaseClass {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public Logger logger;
    public Properties p;
    public FileInputStream file;
    public String screenFilePath;
    public String parentWindow;
    public Actions ac;
    public Alert alert;



    public void setUp(String os, String browser) throws IOException {

        if (os.contains("Mac")) {
            file = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/data.properties");
        } else {
            file = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\data.properties");
        }
        p = new Properties();
        p.load(file);

        logger = LogManager.getLogger(this.getClass());

        if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            if (os.equalsIgnoreCase("Windows 11")) {
                capabilities.setPlatform(Platform.WIN11);
            } else if (os.equalsIgnoreCase("Mac")) {
                capabilities.setPlatform(Platform.MAC);
            } else if (os.equalsIgnoreCase("Linux")) {
                capabilities.setPlatform(Platform.LINUX);
            } else {
                System.out.println("no matching os found");
                return;
            }

            switch (browser.toLowerCase()) {
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;
                case "firefox":
                    capabilities.setBrowserName("firefox");
                    break;
                case "edge":
                    capabilities.setBrowserName("edge");
                    break;
                case "safari":
                    capabilities.setBrowserName("safari");
                    break;
                default:
                    System.out.println("invalid browser name");
                    return;
            }

            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        } else if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "safari":
                    driver = new SafariDriver();
                    break;
                default:
                    System.out.println("invalid browser name");
                    return;
            }
        }
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        driver.get(p.getProperty("frontendURL"));
        driver.manage().window().maximize();
        ac = new Actions(driver);
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }



    public WebElement waitForVisibility(WebElement e) {
        return wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void scrollToElement(WebElement element) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void click(WebElement e)  {
        waitForVisibility(e).click();
    }

    public void rightClick(WebElement e)
    {
        ac.moveToElement(waitForVisibility(e)).contextClick().perform();
    }

    public void dragAndDrop(WebElement source, WebElement target)
    {
        ac.dragAndDrop(waitForVisibility(source), waitForVisibility(target)).perform();
    }

    public void sendKeys(WebElement e, String s){
        waitForVisibility(e).sendKeys(s);
    }

    public String getText(WebElement e) {
        return waitForVisibility(e).getText();
    }

    public boolean elementIsClickable(WebElement e) {
        return waitForVisibility(e).isEnabled();
    }

    public boolean elementIsDisplayed(WebElement e) {
        return waitForVisibility(e).isDisplayed();
    }

    public void failed() {
        logger.error(new Exception().getStackTrace()[1].getMethodName() + " failed");
    }

    public void passed() {
        logger.info(new Exception().getStackTrace()[1].getMethodName() + " passed");
    }

    public void testStarting() {
        logger.info(new Exception().getStackTrace()[1].getMethodName() + " test starting");
    }

    public void failedException() {
        logger.error(new Exception().getStackTrace()[1].getMethodName() + " failed due to an exception");
    }

    public void mouseHover(WebElement e) {
        ac.moveToElement(waitForVisibility(e)).perform();
    }


    //screenshot
    public String captureScreenshot(String tName) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);

        String folderPath = System.getProperty("user.dir") + File.separator + "screenshots";

        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String filePath = folderPath + File.separator + tName + "_" + timeStamp + ".png";

        FileUtils.copyFile(sourceFile, new File(filePath));

        return filePath;
    }

    //Alert
    public void acceptAlert() {
       alert.accept();
    }
    public void dismissAlert() {
        alert.dismiss();
    }
    public String getAlertText()
    {
       return alert.getText();
    }
    public void sendTextToAlert(String text)
    {
        alert.sendKeys(text);
    }


    //Window handle
    public void storeParentWindow()
    {
        parentWindow = driver.getWindowHandle();
    }
    public void switchToWindowByTitle(String expectedTitle)
    {
        for (String window : driver.getWindowHandles())
        {
            driver.switchTo().window(window);
            if (driver.getTitle().equals(expectedTitle))
            {
                return;
            }
        }
        throw new RuntimeException("window with the title nt found: "+ expectedTitle);
    }
    public void closeAndSwitchToParentWindow()
    {
        driver.close();
        driver.switchTo().window(parentWindow);
    }


    //Frame handle
    public void switchToFrameByNameOrId(String nameOrId)
    {
        driver.switchTo().frame(nameOrId);
    }
    public void switchToParentFrame()
    {
        driver.switchTo().parentFrame();
    }
    public void switchToDefaultContent()
    {
        driver.switchTo().defaultContent();
    }

    //Dropdown Selection
    public void dropDownSelectByText(WebElement e, String option)
    {
        Select select = new Select(waitForVisibility(e));
        select.selectByVisibleText(option);
    }

    public String getTitle()
    {
        return driver.getTitle();
    }

    public String generateRandomString(int length)
    {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i =0; i<length; i++)
        {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
       return sb.toString();
    }



}
































