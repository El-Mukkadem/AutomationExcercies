package com.automationExercises.tests.ui;

import com.automationExercises.driver.DriverSet;
import com.automationExercises.pages.components.SignupLoginPage;
import com.automationExercises.pages.components.navigationBarComponent;
import com.automationExercises.tests.BaseTest;
import com.automationExercises.utils.actions.actionUtils;
import com.automationExercises.utils.dataReader.JsonReader;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.automationExercises.apis.userManagementAPI;
import com.automationExercises.utils.logs.timeManager;



public class loginTests extends BaseTest {
    String timestamp = timeManager.getSimpleTimeStamp();

    @Description("Verify user can login with valid credentials")
    @Test
    public void validLoginTC()
    {
        new userManagementAPI().createRegisterUserAccount(
                        testData.getJsonData("name"),
                        testData.getJsonData("email") + timestamp + "@gmail.com",
                        testData.getJsonData("password"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"))
                .verifyUserCreatedSuccessfully();

        new SignupLoginPage(driver).navigate()
                .enterLoginEmail(testData.getJsonData("email") + timestamp + "@gmail.com")
                .enterLoginPassword(testData.getJsonData("password"))
                .clickLoginButton()
                .navigationBarComponent
                .verifyUserLabel(testData.getJsonData("name"));

        new userManagementAPI().deleteUserAccount(
                        testData.getJsonData("email") + timestamp + "@gmail.com",
                        testData.getJsonData("password"))
                .verifyUserDeletedSuccessfully();
    }

    @Description("Verify user cannot login with invalid email")
    @Test
    public void inValidLoginUsingInvalidEmailTC()
    {
        new userManagementAPI().createRegisterUserAccount(
                        testData.getJsonData("name"),
                        testData.getJsonData("email") + timestamp + "@gmail.com",
                        testData.getJsonData("password"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"))
                .verifyUserCreatedSuccessfully();

        new SignupLoginPage(driver).navigate()
                .enterLoginEmail(testData.getJsonData("email")  + "@gmail.com")
                .enterLoginPassword(testData.getJsonData("password"))
                .clickLoginButton()
                .verifyLoginErrorMsg(testData.getJsonData("messages.error"));

        new userManagementAPI().deleteUserAccount(
                        testData.getJsonData("email") + timestamp + "@gmail.com",
                        testData.getJsonData("password"))
                .verifyUserDeletedSuccessfully();
    }

    @Description("Verify user cannot login with invalid password")
    @Test
    public void inValidLoginUsingInvalidPasswordTC()
    {
        new userManagementAPI().createRegisterUserAccount(
                        testData.getJsonData("name"),
                        testData.getJsonData("email") + timestamp + "@gmail.com",
                        testData.getJsonData("password"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"))
                .verifyUserCreatedSuccessfully();

        new SignupLoginPage(driver).navigate()
                .enterLoginEmail(testData.getJsonData("email") + timestamp + "@gmail.com")
                .enterLoginPassword(testData.getJsonData("password")+timestamp)
                .clickLoginButton()
                .verifyLoginErrorMsg(testData.getJsonData("messages.error"));

        new userManagementAPI().deleteUserAccount(
                        testData.getJsonData("email") + timestamp + "@gmail.com",
                        testData.getJsonData("password"))
                .verifyUserDeletedSuccessfully();
    }
    //Configurations
    @BeforeClass
    protected void preCondition() {
        testData = new JsonReader("login-data");
    }
    @BeforeMethod
    public void setUp() {
        driver = new DriverSet();
        new navigationBarComponent(driver).navigate();
        //driver.browser().closeExtensionTab();
    }

    @AfterMethod
    public void tearDown() {
        driver.quitDriver();
    }
}
