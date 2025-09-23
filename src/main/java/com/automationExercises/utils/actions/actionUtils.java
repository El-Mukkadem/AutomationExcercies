package com.automationExercises.utils.actions;

import com.automationExercises.utils.logs.logsManager;
import com.automationExercises.utils.waitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class actionUtils {
    private final WebDriver driver;
    private waitUtils waitUtils;

    public actionUtils(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new waitUtils(driver);
    }

    //Click manager
    public void click(By locator) {
        waitUtils.fluentWait().until(d ->
                {
                    try {
                        WebElement element = d.findElement(locator);
                        scrollToElementJS(locator);
                        element.click();
                        logsManager.info("Element clicked: "+ locator);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }

        );
    }

    //Typing manager
    public void type(By locator, String text) {
        waitUtils.fluentWait().until(d ->
                {
                    try {
                        WebElement element = d.findElement(locator);
                        scrollToElementJS(locator);
                        element.clear();
                        element.sendKeys(text);
                        logsManager.info("Text entered: "+ text + " into element: "+ locator);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
        );
    }

    //Get text manager
    public String getText(By locator) {
        return waitUtils.fluentWait().until(d ->
                {
                    try {
                        WebElement element = d.findElement(locator);
                        scrollToElementJS(locator);
                        String msg = element.getText();
                        logsManager.info("Retrieved text: "+ msg+" into element: "+ locator);
                        return !msg.isEmpty() ? msg : null;
                    }catch (Exception e) {
                        return null;
                    }
                }
        );
    }

    //Upload a file
    public void uploadFile(By locator, String filePath) {
        String fileAbsolutePath = System.getProperty("user.dir") + File.separator + filePath;
        waitUtils.fluentWait().until(d ->
                {
                    try {
                        WebElement element = d.findElement(locator);
                        scrollToElementJS(locator);
                        element.sendKeys(fileAbsolutePath);
                        logsManager.info("File uploaded: "+ fileAbsolutePath+" into element: "+ locator);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
        );
    }
    //Find an element
    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }
    //Function to scroll to element using js
    public void scrollToElementJS(By locator) {
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(""" 
                     arguments[0].scrollIntoView({behaviour:"auto",block:"center",inline:"center"});""", findElement(locator));
    }
    //select from dropdown
    public actionUtils selectFromDropdown(By locator, String value) {
        waitUtils.fluentWait().until(d ->
                {
                    try {
                        WebElement element = d.findElement(locator);
                        scrollToElementJS(locator);
                        Select select = new Select(element);
                        select.selectByVisibleText(value);
                        logsManager.info("Selected value '" + value + "' from dropdown: " + locator);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
        );
        return this;
    }

}
