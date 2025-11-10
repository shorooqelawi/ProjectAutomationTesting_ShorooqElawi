package Test;

import Pages.LoginPage;
import Pages.SignUpPage;
import Pages.UserMenuPage;
import Utilities.TestBase;
import Utilities.SessionData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.SkipException;
import org.testng.asserts.SoftAssert;

public class LoginTest extends TestBase {

    private LoginPage loginPage;
    private SignUpPage signUpPage;
    private UserMenuPage userMenuPage;
    private SoftAssert softAssert;

    @BeforeMethod
    public void pageSetUp() {

        loginPage = new LoginPage(driver);
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

    @Test
    public void login() throws InterruptedException {
        String username = SessionData.getUsername();
        String password = SessionData.getPassword();


        if (username == null || password == null) {
            throw new SkipException("⚠️ No registered credentials found in SessionData. Run SignUpTest.Register_new_account first.");
        }

        loginPage.login(username, password);

        softAssert.assertEquals(
                loginPage.menuUsername(),
                username,
                "User name mismatch after login."
        );
    }

    @Test
    public void loginWrongPassword() throws InterruptedException {
        String username = SessionData.getUsername();
        String wrongPassword = "WrongPass123"; // كلمة مرور خاطئة للاختبار

        if (username == null) {
            throw new SkipException("⚠️ No registered username found in SessionData. Run SignUpTest.Register_new_account first.");
        }

        loginPage.login(username, wrongPassword);
        Thread.sleep(2000);
        softAssert.assertEquals(loginPage.isLoginErrorDisplayed(),"Incorrect user name or password.","Expected error message for wrong email not shown.");
    }

    @Test
    public void loginWrongEmail() throws InterruptedException {
        String wrongUsername = "wrongemail@test.com";
        String password = SessionData.getPassword();

        if (password == null) {
            throw new SkipException("⚠️ No registered password found in SessionData. Run SignUpTest.Register_new_account first.");
        }

        loginPage.login(wrongUsername, password);
        Thread.sleep(2000);
        softAssert.assertEquals(loginPage.isLoginErrorDisplayed(),"Incorrect user name or password.","Expected error message for wrong email not shown.");
    }


}
