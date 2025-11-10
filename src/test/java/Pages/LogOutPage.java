package Pages;

import Utilities.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogOutPage extends TestBase {

    private UserMenuPage userMenuPage;
    public LogOutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.userMenuPage = new UserMenuPage(driver);
    }
    @FindBy(xpath = "(//label[contains(@class,'option roboto-medium')])[3]")
    WebElement logoutLink;


    public void logout() throws InterruptedException {
        userMenuPage.openUserMenu();
        Thread.sleep(1000);
        click(logoutLink);
    }

}
