package Pages;

import Utilities.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage extends TestBase {

    private UserMenuPage userMenuPage;
    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.userMenuPage = new UserMenuPage(driver);
    }

    @FindBy(xpath = "(//label[contains(@class,'option roboto-medium')])[2]")
     WebElement MyAccount;

    @FindBy(className = "deleteBtnText")
    WebElement DeleteAccount;

    @FindBy(xpath = "//div[@class='deletePopupBtn deleteRed']")
    WebElement YesButton;

    @FindBy(xpath = "//div[contains(@class,'successfulDeleteMessage')]//p[text()='Account deleted successfully']")
    WebElement deleteAccountMessage;

    public void openMyAccount() {
        userMenuPage.openUserMenu();
        click(MyAccount);
    }

    public void DeleteAccount() throws InterruptedException {
        click(DeleteAccount);
        Thread.sleep(2000);
        click(YesButton);
    }

    public String getDeleteAccountMessage() {
        return getText(deleteAccountMessage);
    }
}
