package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class TestBase {
    public WebDriver driver;

    @Parameters("browser")
    @BeforeMethod()
    public void beforeMethod(@Optional("chrome")String browser){

        if(browser.equals("chrome")){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            options.addArguments("--start-maximized");
            //  options.addArguments("--headless=new");
            driver = new ChromeDriver(options);}
        else if(browser.equals("firefox")){
            driver = new FirefoxDriver();
        }
        driver.get("https://advantageonlineshopping.com/#/");
    }


    @AfterMethod(alwaysRun = true)
    public void QuitDriver(){
        if(driver != null){
            driver.quit();}
    }

    private void waitElementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    public void click(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (ElementClickInterceptedException e) {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".PopUp")));
            element.click();
        }
    }




    public void sendKey(WebElement element, String text) {
        waitElementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
    }

    public String getText(WebElement element) {
        waitElementToBeVisible(element);
        return element.getText();
    }

    public void selectByVisibleText(WebElement element, String text) {
        waitElementToBeVisible(element);
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }




}
