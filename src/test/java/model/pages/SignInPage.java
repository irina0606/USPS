package model.pages;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignInPage extends BasePage {

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "btn-submit")
    private WebElement signInButton;

    @FindBy(id = "response-msg")
    private WebElement errorText;

    @FindBy(id = "error-username")
    private WebElement usernameError;

    @FindBy(id = "error-password")
    private WebElement passwordError;

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public SignInPage fillUsername(char[] arr) throws InterruptedException {
        for (int i = 0; i < arr.length; i++) {
            String name = Character.toString(arr[i]);
            usernameField.sendKeys(name);
            Thread.sleep(200);
        }
        return this;
    }

    public SignInPage fillPassword(char[] arr) throws InterruptedException {
        for (int i = 0; i < arr.length; i++) {
            String password = Character.toString(arr[i]);
            passwordField.sendKeys(password);
            Thread.sleep(200);
        }
        return this;
    }

    public HomePage clickSignInButton() {
        signInButton.click();
        return new HomePage(getDriver());
    }

    public WebElement getErrorText() {
        return errorText;
    }

    public static String getMessage(String msg) {
        return msg;
    }

    public WebElement getUsernameError() {
        getWait().until(ExpectedConditions.textToBePresentInElement(usernameError, "Your Username must be at least 6 characters long."));
        return usernameError;
    }

    public WebElement getPasswordError() {
        getWait().until(ExpectedConditions.textToBePresentInElement(passwordError, "A password is required."));
        return passwordError;
    }

   /* public String getPasswordError1(){
        getWait().until(ExpectedConditions.textToBePresentInElement(passwordError, "We are unable to process your request at this time.  Please try again."));
        return passwordError.getText();
    }*/
}
