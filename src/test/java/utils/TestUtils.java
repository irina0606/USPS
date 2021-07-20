package utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class TestUtils {

    public static void click(WebDriverWait wait, WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public static void scrollAndClick(WebDriver driver, WebDriverWait wait, WebElement element) {
        jsScroll(driver, element);
        click(wait, element);
    }

    public static void jsClick(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click()", element);
    }

    public static void jsScroll(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
    }

    public static void fill(WebDriverWait wait, WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        if (element.toString().toLowerCase().contains("date") || element.getAttribute("name").contains("date")){
            click(wait, element);
            element.clear();
        }
        if (!element.getAttribute("value").isEmpty()) {
            element.clear();
        }
        element.sendKeys(text);
        wait.until(d -> element.getAttribute("value").equals(text));
    }

    public static void inputKeys(WebDriver driver, WebElement element, String keys) {
        if (!"input".equals(element.getTagName())) {
            throw new RuntimeException(element + " is not input");
        }
        fill(new WebDriverWait(driver, 10), element, keys);
    }

    public static String createUUID() {
        return UUID.randomUUID().toString();
    }

    public static String getAlphanumeric(int count) {
        return RandomStringUtils.randomAlphanumeric(count);
    }

    public static String getGMTDate() {
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("dd/MM/yyyy");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormatGmt.format(new Date());
    }

    public static boolean isFocusOnField(WebDriver driver, WebElement element) {
        return driver.switchTo().activeElement().equals(element);
    }

    public static int getRandomInteger() {
        Random r = new Random();
        return r.nextInt(Integer.MAX_VALUE);
    }

    public static int getRandomInteger(int maxValue) {
        Random r = new Random();
        return r.nextInt(maxValue);
    }

    public static String randomInteger(){
        return String.valueOf(getRandomInteger());
    }

    public static String randomInteger(int maxValue){
        return String.valueOf(getRandomInteger(maxValue));
    }

    public static String getRandomInteger(int min, int max) {
        return String.valueOf(ThreadLocalRandom.current().nextInt(min, max + 1));
    }

    public static String randomString(int stringLength){
        if (stringLength < 36) {
            return createUUID().substring(0, stringLength);
        }
        return createUUID();
    }

    public static String randomDecimal(){
        return String.format("%.2f", getRandomInteger() * 0.01);
    }

    public static String randomDecimal(int maxValue){
        return String.format("%.2f", getRandomInteger(maxValue) * 0.01);
    }

    public static String currentDate(){
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }

    public static String currentDateTime(){
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    }

    private static class MovingExpectedCondition implements ExpectedCondition<WebElement> {

        private By locator;
        private WebElement element = null;
        private Point location = null;

        public MovingExpectedCondition(WebElement element) {
            this.element = element;
        }

        public MovingExpectedCondition(By locator) {
            this.locator = locator;
        }



        @Override
        public WebElement apply(WebDriver driver) {
            if (element == null) {
                try {
                    element = driver.findElement(locator);
                } catch (NoSuchElementException e) {
                    return null;
                }
            }
            if (element.isDisplayed()) {
                Point location = element.getLocation();
                if (location.equals(this.location)) {
                    return element;
                }
                this.location = location;
            }
            return null;
        }
    }

    public static ExpectedCondition<WebElement> movingIsFinished(WebElement element) {
        return new MovingExpectedCondition(element);
    }

    public static ExpectedCondition<WebElement> movingIsFinished(By locator) {
        return new MovingExpectedCondition(locator);
    }
}