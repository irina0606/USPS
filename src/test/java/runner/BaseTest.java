package runner;

import io.github.bonigarcia.wdm.WebDriverManager;
import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Listeners(TestOrder.class)
public abstract class BaseTest {

    public static final String URL = "https://www.usps.com/";

    private WebDriver driver;
    private WebDriverWait webDriverWait;

    static {
            WebDriverManager.chromedriver().setup();
    }

    private WebDriver createBrowser() {

        Map<String, Object> chromePreferences = new HashMap<>();
        chromePreferences.put("profile.default_content_settings.geolocation", 2);
        chromePreferences.put("credentials_enable_service", false);
        chromePreferences.put("password_manager_enabled", false);
        chromePreferences.put("safebrowsing.enabled", "true");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", chromePreferences);
        chromeOptions.addArguments("--window-size=1920,1080");

        return new ChromeDriver(chromeOptions);
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected WebDriverWait getWebDriverWait() {
        if (webDriverWait == null) {
            webDriverWait = new WebDriverWait(driver, 10);
        }

        return webDriverWait;
    }

    @BeforeMethod
    protected void beforeMethod() {
        driver = createBrowser();
        driver.get(URL);
    }

    @AfterMethod
    protected void afterMethod() {
        //driver.quit();
        webDriverWait = null;
    }
}