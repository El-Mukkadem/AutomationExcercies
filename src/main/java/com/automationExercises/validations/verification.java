package com.automationExercises.validations;

import org.testng.Assert;
import org.openqa.selenium.WebDriver;

//Hard assertions
public class verification extends baseAssertion {

    public verification() {
        super();
    }
    public verification(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void assertTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }

    @Override
    protected void assertFalse(boolean condition, String message) {
        Assert.assertFalse(condition, message);
    }

    @Override
    protected void assertEquals(String actual, String expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }
}
