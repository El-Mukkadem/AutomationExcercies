package com.automationExercises.tests.ui;

import com.automationExercises.driver.DriverSet;
import com.automationExercises.pages.components.SignupLoginPage;
import com.automationExercises.pages.components.SignupPage;
import com.automationExercises.pages.components.navigationBarComponent;
import com.automationExercises.tests.BaseTest;
import com.automationExercises.utils.dataReader.JsonReader;
import com.automationExercises.utils.logs.timeManager;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.automationExercises.apis.userManagementAPI;


public class registerTests extends BaseTest {
    String timestamp = timeManager.getSimpleTimeStamp();


//Tests
@Description("Verify user can sign up with valid data")
@Test
    public void signupTC(){
        new SignupLoginPage(driver).navigate()
                .enterSignupName(testData.getJsonData("name"))
                .enterSignupEmail(testData.getJsonData("email")+ timestamp + "@gmail.com")
                .clickSignupButton();
                new SignupPage(driver)
                .fillRegisterationForm(
                        testData.getJsonData("titleMale"),
                        testData.getJsonData("password"),
                        testData.getJsonData("day"),
                        testData.getJsonData("month"),
                        testData.getJsonData("year"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"),
                        testData.getJsonData("companyName"),
                        testData.getJsonData("address1"),
                        testData.getJsonData("address2"),
                        testData.getJsonData("country"),
                        testData.getJsonData("state"),
                        testData.getJsonData("city"),
                        testData.getJsonData("zipcode"),
                        testData.getJsonData("mobileNumber")
                                )
                .clickCreateAccountButton()
                .verifyAccountCreated();


    }
    @Description("Verify user cannot sign up with invalid data")
    @Test
    public void verifyErrorMessageWhenAccountCreatedBefore()
    {
        //precondition > create a user account
        new userManagementAPI().createRegisterUserAccount(
                        testData.getJsonData("name"),
                        testData.getJsonData("email") + timestamp  + "@gmail.com",
                        testData.getJsonData("password"),
                        testData.getJsonData("titleMale"),
                        testData.getJsonData("day"),
                        testData.getJsonData("month"),
                        testData.getJsonData("year"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"),
                        testData.getJsonData("companyName"),
                        testData.getJsonData("address1"),
                        testData.getJsonData("address2"),
                        testData.getJsonData("country"),
                        testData.getJsonData("state"),
                        testData.getJsonData("city"),
                        testData.getJsonData("zipcode"),
                        testData.getJsonData("mobileNumber")
                )
                .verifyUserCreatedSuccessfully();

        new SignupLoginPage(driver).navigate()
                .enterSignupName(testData.getJsonData("name"))
                .enterSignupEmail(testData.getJsonData("email") + timestamp  + "@gmail.com")
                .clickSignupButton()
                .verifyRegisterErrorMsg(testData.getJsonData("messages.error"));


        new userManagementAPI().deleteUserAccount(
                        testData.getJsonData("email") + timestamp + "@gmail.com",
                        testData.getJsonData("password"))
                .verifyUserDeletedSuccessfully();
    }

    //Configurations
    @BeforeClass
    public void beforeClass(){

        testData = new JsonReader("register-data");
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
