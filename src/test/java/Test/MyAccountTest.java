package Test;

import Pages.LoginPage;
import Pages.MyAccountPage;
import Pages.SignUpPage;
import Pages.UserMenuPage;
import Utilities.SessionData;
import Utilities.TestBase;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;

public class MyAccountTest extends TestBase {

    private MyAccountPage myAccountPage;
    private LoginPage loginPage;
    private SignUpPage signUpPage;
    private UserMenuPage userMenuPage;
    private SoftAssert softAssert;

    @BeforeMethod
    public void pageSetUp() {

        loginPage = new LoginPage(driver);
        myAccountPage = new MyAccountPage(driver);
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
    public void DeleteAccount() throws InterruptedException {
        String username = SessionData.getUsername();
        String password = SessionData.getPassword();


        if (username == null || password == null) {
            throw new SkipException("⚠️ No registered credentials found in SessionData. Run SignUpTest.Register_new_account first.");
        }

        loginPage.login(username, password);
        myAccountPage.openMyAccount();
        Thread.sleep(2000);
        myAccountPage.DeleteAccount();
        Thread.sleep(2000);
       softAssert.assertEquals(myAccountPage.getDeleteAccountMessage(), "Account deleted successfully", "Expected success message not shown.");
    }


    @Test(dependsOnMethods = "DeleteAccount")
    public void LoginWithDeletedaccount() throws InterruptedException {
        String username = SessionData.getUsername();
        String password = SessionData.getPassword();


        if (username == null || password == null) {
            throw new SkipException("⚠️ No registered credentials found in SessionData. Run SignUpTest.Register_new_account first.");
        }

        loginPage.login(username, password);
        Thread.sleep(2000);
        softAssert.assertEquals(loginPage.isLoginErrorDisplayed(), "Incorrect user name or password.", "Expected error message for wrong email not shown.");
    }
}
