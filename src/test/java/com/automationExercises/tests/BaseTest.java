package com.automationExercises.tests;

import com.automationExercises.driver.DriverSet;
import com.automationExercises.driver.WebDriverProvider;
import com.automationExercises.utils.dataReader.JsonReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

public class BaseTest implements WebDriverProvider {

    protected DriverSet driver;
    protected JsonReader testData;

    @Override
    public WebDriver getDriver() {
        return driver.getDriver();
    }
}
