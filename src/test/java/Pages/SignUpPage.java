package Pages;


import Utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Pages.UserMenuPage;

import java.time.Duration;

public class SignUpPage extends TestBase {

    private UserMenuPage userMenuPage;

    public  SignUpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.userMenuPage = new UserMenuPage(driver);
    }



    @FindBy(linkText = "CREATE NEW ACCOUNT")
    WebElement createNewAccountLink;


    // Title text on the registration form
    @FindBy(xpath = "//*[normalize-space()='CREATE ACCOUNT']")
     WebElement createAccountTitle;

    @FindBy(name = "usernameRegisterPage")
     WebElement userName;

    @FindBy(name = "emailRegisterPage")
     WebElement email;

    @FindBy(name = "passwordRegisterPage")
     WebElement password;

    @FindBy(name = "confirm_passwordRegisterPage")
     WebElement confirmPassword;

    @FindBy(name = "first_nameRegisterPage")
     WebElement firstName;

    @FindBy(name = "last_nameRegisterPage")
     WebElement lastName;

    @FindBy(name = "phone_numberRegisterPage")
     WebElement phoneNumber;

    @FindBy(name = "countryListboxRegisterPage")
     WebElement country;

    @FindBy(name = "cityRegisterPage")
     WebElement city;

    @FindBy(name = "addressRegisterPage")
     WebElement address;

    @FindBy(name = "state_/_province_/_regionRegisterPage")
    WebElement state;

    @FindBy(name = "postal_codeRegisterPage")
     WebElement PostalCode;

    @FindBy(name = "i_agree")
     WebElement IagreeCheckBox;

    @FindBy(xpath = "//sec-sender[@a-value='REGISTER']")
     WebElement registerButton;

    @FindBy(xpath = "//span[contains(@class,'hi-user containMiniTitle')]")
     WebElement GetUserName;

    @FindBy(xpath = "//*[@id=\"loginMiniTitle\"]/label[3]")
    WebElement LogOut;

    @FindBy(xpath = "//label[contains(@class,'center block')]")
    WebElement ExistingUserNameMessage;

    public void openSignUpForm() throws InterruptedException {
        userMenuPage.openUserMenu();

        Thread.sleep(4000);
        click(createNewAccountLink);
    }

    public String getMenuUsername() throws InterruptedException {
        Thread.sleep(3000);
        try {
            return getText(GetUserName).trim();
        } catch (Exception e) {
            return "";
        }
    }


    public String getCreateAccountTitleText() {
        try {
            return getText(createAccountTitle).trim();
        } catch (Exception e) {
            return "";
        }
    }

    public void FillAC_COUNT_DETAILS(String userName, String email, String password, String confirmPassword){
        sendKey(this.userName, userName);
        sendKey(this.email, email);
        sendKey(this.password, password);
        sendKey(this.confirmPassword, confirmPassword);
    }

    public void Fill_PERSONAL_DETAILS(String firstName, String lastName,String phoneNumber){
        sendKey(this.firstName, firstName);
        sendKey(this.lastName, lastName);
        sendKey(this.phoneNumber, phoneNumber);
    }

    public void Fill_ADDRESS_DETAILS(String country, String city, String address,String state, String PostalCode) throws InterruptedException {
        selectByVisibleText(this.country, country);
        sendKey(this.city, city);
        sendKey(this.address, address);
        sendKey(this.state, state);
        sendKey(this.PostalCode, PostalCode);
        Thread.sleep(3000);
        click(this.IagreeCheckBox);
    }

    public void clickOnRegisterButton() throws InterruptedException {
        Thread.sleep(3000);
        click(this.registerButton);
    }


    public String getExistingUserNameMessage() {
        try {
            return getText(ExistingUserNameMessage).trim();
        } catch (Exception e) {
            return "";
        }
    }

}
