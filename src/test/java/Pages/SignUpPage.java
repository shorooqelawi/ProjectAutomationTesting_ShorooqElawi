package Pages;

import Utilities.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage extends TestBase {

    public  SignUpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "menuUserLink")
    private WebElement userMenuLink;

    @FindBy(xpath = "//a[contains(., 'CREATE NEW ACCOUNT')]")
    private WebElement createNewAccountLink;

    // Title text on the registration form
    @FindBy(xpath = "//*[normalize-space()='CREATE ACCOUNT']")
    private WebElement createAccountTitle;

    @FindBy(name = "usernameRegisterPage")
    private WebElement userName;

    @FindBy(name = "emailRegisterPage")
    private WebElement email;

    @FindBy(name = "passwordRegisterPage")
    private WebElement password;

    @FindBy(name = "confirm_passwordRegisterPage")
    private WebElement confirmPassword;

    public void openSignUpForm() {
        click(userMenuLink);
        click(createNewAccountLink);
    }

    public boolean isSignUpFormDisplayed() {
        try {
            return createAccountTitle.isDisplayed() &&
                    "CREATE ACCOUNT".equals(createAccountTitle.getText().trim());
        } catch (Exception e) {
            return false;
        }
    }

    public String getCreateAccountTitleText() {
        try {
            return getText(createAccountTitle).trim();
        } catch (Exception e) {
            return "";
        }
    }
}
