package test;

import model.pages.HomePage;
import model.pages.SignInPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class SignInPageTest extends BaseTest {

    private static final String USERNAME = "USPS_Irzhik";
    char[] nameSpelling = USERNAME.toCharArray();
    private static final String PASSWORD = "Test#2021";
    char[] passwordSpelling = PASSWORD.toCharArray();
    private static final String USERNAME_ERROR = "Your Username must be at least 6 characters long.";
    private static final String PASSWORD_ERROR = "A password is required.";
    private static final String ERROR_TEXT1 = "Our apologies that you are having issues with your login. We recommend that you restart the current browser and try logging in again........  We do not recognize your username and/or password.  Please try again.";
    private static final String ERROR_TEXT2 = "We are unable to process your request at this time.  Please try again.";

    @Test
    public void signIn_validCredentials() throws InterruptedException {

        SignInPage signInPage = new SignInPage(getDriver());
        new HomePage(getDriver())
                .clickSignInIcon()
                .fillUsername(nameSpelling)
                .fillPassword(passwordSpelling)
                .clickSignInButton();

        getDriver().navigate().refresh();

        new SignInPage(getDriver())
                .fillUsername(nameSpelling)
                .fillPassword(passwordSpelling)
                .clickSignInButton();

        if (signInPage.getErrorText().getText().equalsIgnoreCase(ERROR_TEXT1)) {
             getWebDriverWait().until(ExpectedConditions.textToBePresentInElement(signInPage.getErrorText(), ERROR_TEXT1));
            Assert.assertEquals(ERROR_TEXT1, SignInPage.getMessage(signInPage.getErrorText().getText()));
        } else if (signInPage.getErrorText().getText().equalsIgnoreCase(ERROR_TEXT2)) {
             getWebDriverWait().until(ExpectedConditions.textToBePresentInElement(signInPage.getErrorText(), ERROR_TEXT2));
            Assert.assertEquals(ERROR_TEXT2, SignInPage.getMessage(signInPage.getErrorText().getText()));
        }
    }

    @Test
    public void signIn_EmptyUN() throws InterruptedException {

        SignInPage signInPage = new SignInPage(getDriver());
        new HomePage(getDriver())
                .clickSignInIcon()
                .fillPassword(passwordSpelling)
                .clickSignInButton();

        getWebDriverWait().until(ExpectedConditions.textToBePresentInElement(signInPage.getUsernameError(), USERNAME_ERROR));
        Assert.assertEquals(USERNAME_ERROR, signInPage.getUsernameError().getText());
    }

    @Test
    public void signIn_EmptyPW() throws InterruptedException {

        SignInPage signInPage = new SignInPage(getDriver());
        new HomePage(getDriver())
                .clickSignInIcon()
                .fillUsername(nameSpelling)
                .clickSignInButton();

        getWebDriverWait().until(ExpectedConditions.textToBePresentInElement(signInPage.getPasswordError(), PASSWORD_ERROR));
        Assert.assertEquals(PASSWORD_ERROR, signInPage.getPasswordError().getText());
    }
}



