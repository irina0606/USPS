package model.pages;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utils.TestUtils;

import java.util.List;

public class FindLocationsPage extends BasePage {
    public FindLocationsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (id = "city-state-input")
    private WebElement inputZip;

    @FindBy (id = "post-offices-select")
    private WebElement dropdownPO_button;

    @FindBy (xpath = "//body/div/div/div/div/div/div/div[1]/div[1]/ul[1]")
    private WebElement dropdownMenu;

    @FindBy (xpath = "//body/div/div/div/div/div/div/div[1]/div[1]/ul[1]/")
    private WebElement po_type;


    public FindLocationsPage fillZip (String zip) {
        inputZip.click();
        inputZip.sendKeys(zip);
        return this;
    }

    public FindLocationsPage selectPO (String namePO) {
        dropdownPO_button.click();
        getWait().until(ExpectedConditions.visibilityOf(dropdownMenu));
        Select anyPO = new Select(po_type);
        anyPO.selectByVisibleText(namePO);
        //TestUtils.jsClick(getDriver(), namePO);
        return this;
    }

}
