package Test;

import Pages.LogOutPage;
import Pages.SignUpPage;
import Pages.UserMenuPage;
import Utilities.TestBase;
import Utilities.DataGenerator;
import Utilities.SessionData;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class SignUpTest extends TestBase {
    private LogOutPage logOutPage;
    private SignUpPage signUpPage;
    private UserMenuPage userMenuPage;
    private SoftAssert softAssert;

    @BeforeMethod
    public void pageSetUp() {
        logOutPage = new LogOutPage(driver);
        signUpPage = new SignUpPage(driver);
        userMenuPage = new UserMenuPage(driver);
        softAssert = new SoftAssert();
    }

    @AfterMethod(alwaysRun = true)
    public void assertAll() {
        if (softAssert != null) {
            softAssert.assertAll();
            softAssert = null;
        }
    }

    @Test()
    public void openSignUpForm() throws InterruptedException {
        Thread.sleep(5000);
        signUpPage.openSignUpForm();
        softAssert.assertEquals(
                signUpPage.getCreateAccountTitleText(),
                "CREATE ACCOUNT",
                "Sign-up page title text mismatch."
        );
    }

    @Test
    public void Register_new_account() throws InterruptedException {
        // Generate random but valid test data
        String userName = DataGenerator.username("user");
        String email = DataGenerator.email("qa", "mailinator.com");
        String pwd = DataGenerator.password(10);
        String first = DataGenerator.firstName();
        String last = DataGenerator.lastName();
        String phone = DataGenerator.phone();
        String city = DataGenerator.city();
        String addr = DataGenerator.address();
        String state = DataGenerator.state();
        String postal = DataGenerator.postalCode();

        signUpPage.openSignUpForm();
        Thread.sleep(2000);
        signUpPage.FillAC_COUNT_DETAILS(userName, email, pwd, pwd);
        signUpPage.Fill_PERSONAL_DETAILS(first, last, phone);
        signUpPage.Fill_ADDRESS_DETAILS("Jordan", city, addr, state, postal);
        Thread.sleep(3000);

        signUpPage.clickOnRegisterButton();
        Thread.sleep(2000);
        softAssert.assertEquals(
                signUpPage.getMenuUsername(),
                userName,
                "User name mismatch.");

        // Save credentials for subsequent tests (e.g., LoginTest)
        SessionData.setCredentials(userName, pwd);

        //logOutPage.logout();

    }
    @Test(dependsOnMethods = "Register_new_account")
    public void Register_With_Existing_UserName() throws InterruptedException {
        String existingUsername = SessionData.getUsername();
        String existingPassword = SessionData.getPassword();
        String first = DataGenerator.firstName();
        String last = DataGenerator.lastName();
        String phone = DataGenerator.phone();
        String city = DataGenerator.city();
        String addr = DataGenerator.address();
        String state = DataGenerator.state();
        String postal = DataGenerator.postalCode();

        if (existingUsername == null || existingPassword == null) {
            throw new SkipException("No previous account found. Run Register_new_account first.");
        }

        signUpPage.openSignUpForm();

        signUpPage.FillAC_COUNT_DETAILS(existingUsername, "duplicateEmail@test.com", existingPassword, existingPassword);
        Thread.sleep(2000);
        signUpPage.Fill_PERSONAL_DETAILS(first, last, phone);
        Thread.sleep(2000);
        signUpPage.Fill_ADDRESS_DETAILS("Jordan", city, addr, state, postal);

        signUpPage.clickOnRegisterButton();

        softAssert.assertEquals(
                signUpPage.getExistingUserNameMessage(),
                "User name already exists",
                "Expected error for existing username not shown."
        );
    }





}
