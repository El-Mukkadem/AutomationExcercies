package com.automationExercises.utils.actions;

import com.automationExercises.utils.logs.logsManager;
import com.automationExercises.utils.waitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class browserActionUtils {
    private final WebDriver driver;
    private final waitUtils waitUtils;
    public browserActionUtils(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new waitUtils(driver);
    }

    //Maximize Window
    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    //Get Current URL
    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        logsManager.info("Current URL: " + url);
        return url;
    }

    //Navigate to URL
    public void navigateTo(String url) {
        driver.get(url);
        logsManager.info("Navigating to: " + url);
    }

    //Refresh page
    public void refreshPage() {
        driver.navigate().refresh();
    }

    //Close current page
    public void closeCurrentWindow() {
        driver.close();
    }

    //Open a new window
    public void openNewWindow() {
        driver.switchTo().newWindow(WindowType.WINDOW);
    }

    //close extension tab
    public void closeExtensionTab() {
        String currentWindowHandle = driver.getWindowHandle(); //0 1
        waitUtils.fluentWait().until(
                d ->
                {
                    return d.getWindowHandles().size() > 1; //wait until extension tab is opened
                }
        );
        for (String windowHandle : driver.getWindowHandles()) //extension tab is opened
        {
            if (!windowHandle.equals(currentWindowHandle))
                driver.switchTo().window(windowHandle).close(); //close the extension tab
        }
        driver.switchTo().window(currentWindowHandle); //switch back to the main window
        logsManager.info("Extension tab closed");
    }
}
