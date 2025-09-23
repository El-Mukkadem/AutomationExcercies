package com.automationExercises.driver;

import com.automationExercises.utils.actions.actionUtils;
import com.automationExercises.utils.actions.alertActions;
import com.automationExercises.utils.actions.browserActionUtils;
import com.automationExercises.utils.actions.frameActionsUtils;
import com.automationExercises.utils.dataReader.propertyReader;
import com.automationExercises.utils.logs.logsManager;
import com.automationExercises.validations.validation;
import com.automationExercises.validations.verification;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;

public class DriverSet {
private final String browser = propertyReader.getProperty("browserType");
private ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public DriverSet () {
        browserSet browserType = browserSet.valueOf(browser.toUpperCase());
        logsManager.info("Starting driver for browser: "+ browserType);
        AbstractDriver abstractDriver = browserType.getDriver();
        WebDriver driver = ThreadGuard.protect(abstractDriver.createDriver());
        driverThreadLocal.set(driver);
    }

    public WebDriver getDriver () {
        return driverThreadLocal.get();
    }

    public void quitDriver () {
        driverThreadLocal.get().quit();
    }

    public actionUtils element() {
        return new actionUtils(getDriver());
    }
    public browserActionUtils browser() {
        return new browserActionUtils(getDriver());
    }
    public frameActionsUtils frame() {
        return new frameActionsUtils(getDriver());
    }
    public alertActions alert() {
        return new alertActions(getDriver());
    }
    //soft assertions
    public validation validation() {
        return new validation(getDriver());
    }
    // hard assertions
    public verification verification() {
        return new verification(getDriver());
    }
}
