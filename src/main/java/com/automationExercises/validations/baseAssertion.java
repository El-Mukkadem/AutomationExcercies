package com.automationExercises.validations;

import com.automationExercises.utils.actions.actionUtils;
import com.automationExercises.utils.fileUtils;
import com.automationExercises.utils.waitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public abstract class baseAssertion {

    protected WebDriver driver;
    protected waitUtils waitManager;
    protected actionUtils elementActions;

    protected baseAssertion() {

    }

    protected baseAssertion(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new waitUtils(driver);
        this.elementActions = new actionUtils(driver);
    }

    protected abstract void assertTrue(boolean condition, String message);

    protected abstract void assertFalse(boolean condition, String message);

    protected abstract void assertEquals(String actual, String expected, String message);

    public baseAssertion Equals(String actual, String expected, String message) {
        assertEquals(actual, expected, message);
        return this;
    }

    public void isElementVisible(By locator) {
        boolean flag = waitManager.fluentWait().until(driver1 ->
        {
            try {
                driver1.findElement(locator).isDisplayed();
                return true;
            } catch (Exception e) {
                return false;
            }
        });
        assertTrue(flag, "Element is not visible: " + locator);
    }

    // verify page url
    public void assertPageUrl(String expectedUrl) {
        String actualUrl = driver.getCurrentUrl();
        assertEquals(actualUrl, expectedUrl, "URL does not match. Expected: " + expectedUrl + ", Actual: " + actualUrl);
    }

    // verify page title
    public void assertPageTitle(String expectedTitle) {
        String actualTitle = driver.getTitle();
        assertEquals(actualTitle, expectedTitle, "Title does not match. Expected: " + expectedTitle + ", Actual: " + actualTitle);
    }

    //verify that file exists
    public void assertFileExists(String fileName, String message) {
        waitManager.fluentWait().until(
                d -> fileUtils.isFileExists(fileName)
        );
        assertTrue(fileUtils.isFileExists(fileName), message);
    }
}
