package Pages;

import Utilities.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestBase {

    private UserMenuPage userMenuPage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.userMenuPage = new UserMenuPage(driver);
    }

    // Login mini-form elements
    @FindBy(name = "username")
    WebElement loginUserName;

    @FindBy(name = "password")
    WebElement loginPassword;

    @FindBy(id = "sign_in_btn")
    WebElement signInButton;
    
    @FindBy(id = "signInResultMessage")
    WebElement errorMessage;

    public void login(String username, String password) throws InterruptedException {
        // Open login mini form, fill and sign in
        userMenuPage.openUserMenu();
        Thread.sleep(5000);
        sendKey(this.loginUserName, username);
        Thread.sleep(3000);
        sendKey(this.loginPassword, password);
        Thread.sleep(3000);
        click(this.signInButton);
    }

    public void openLoginForm() {
        userMenuPage.openUserMenu();
    }

    public String menuUsername() {
        return userMenuPage.getDisplayedUsername();
    }
    
    public String isLoginErrorDisplayed() {
        try {
            return getText(errorMessage).trim();
        } catch (Exception e) {
            return "";
        }
        
    }
}
