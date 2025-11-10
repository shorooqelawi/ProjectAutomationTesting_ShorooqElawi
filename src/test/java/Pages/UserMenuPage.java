package Pages;

import Utilities.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserMenuPage extends TestBase {

    public  UserMenuPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(id = "menuUserLink")
    WebElement userMenuLink;



    @FindBy(xpath = "//span[contains(@class,'hi-user containMiniTitle')]")
    WebElement miniUserTitle;

    public void openUserMenu() {
        click(userMenuLink);
    }

    public String getDisplayedUsername() {
        // When logged in, the mini title shows the username
        return getText(miniUserTitle).trim();    }




}
