package com.automationExercises.validations;


import com.automationExercises.utils.logs.logsManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

//Soft assertions
public class validation extends baseAssertion {
    private static SoftAssert softAssert = new SoftAssert();
    private static boolean used = false;
    public validation(){}//flag
    public validation(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void assertTrue(boolean condition, String message) {
        used = true;
        softAssert.assertTrue(condition, message);
    }

    @Override
    protected void assertFalse(boolean condition, String message) {
        used = true;
        softAssert.assertFalse(condition, message);
    }

    @Override
    protected void assertEquals(String actual, String expected, String message) {
        used = true;
        softAssert.assertEquals(actual, expected, message);
    }

    public static void assertAll(ITestResult testResult){
        if(!used) return;
        try {
            softAssert.assertAll();
        }catch (AssertionError e){
            logsManager.error("Assertion failed: ",e.getMessage());
            throw e;
        }finally {
            softAssert = new SoftAssert();
        }
    }
}
