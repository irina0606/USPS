package model.pages;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(id = "login-register-header")
    private WebElement signInIcon;

    @FindBy(id = "anchor-login")
    private WebElement HiNameIcon;

    @FindBy(xpath = "//a[@href='#'][normalize-space()='Hi, Test']")
    private static WebElement authorizedName;

    @FindBy(xpath = "//*[@id='g-navigation']/div/nav/ul/li[1]/a[2]")
    private WebElement quickToolsButton;

    @FindBy(xpath = "//*[@role='menu']/li")
    private List<WebElement> quickToolsMenuBar;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public SignInPage clickSignInIcon(){
        signInIcon.click();
        return new SignInPage(getDriver());
    }

    public String getAuthorizedName(){
        String name = authorizedName.getText();
        return name;
    }

    public HomePage clickHiNameButton(){
        return this;
    }

    public WebElement moveToQuickToolsButton(){
        return quickToolsButton;
    }

    public WebElement getMenuOption(String string){
        getActions().moveToElement(moveToQuickToolsButton());
        getActions().build().perform();
        for (int i=0; i<quickToolsMenuBar.size(); i++){
            String name=quickToolsMenuBar.get(i).getText();
            if(name.equalsIgnoreCase(string)) {
                return quickToolsMenuBar.get(i);
            }
        }
        return null;
    }


}
