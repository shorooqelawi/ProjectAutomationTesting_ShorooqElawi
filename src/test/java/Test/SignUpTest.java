package Test;

import Pages.SignUpPage;
import Utilities.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SignUpTest extends TestBase {

    private SignUpPage signUpPage;
    private SoftAssert softAssert;

    @BeforeMethod
    public void pageSetUp() {

        signUpPage = new SignUpPage(driver);
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
    public void openSignUpForm() {
        signUpPage.openSignUpForm();
        softAssert.assertEquals(
                signUpPage.getCreateAccountTitleText(),
                "CREATE ACCOUNT",
                "Sign-up page title text mismatch."
        );
    }
}
