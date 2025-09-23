package com.automationExercises.pages.components;

import com.automationExercises.driver.DriverSet;
import com.automationExercises.utils.dataReader.propertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SignupLoginPage {
    private final DriverSet driver;
    private final String signupLoginEndpoint = "/login";
    public navigationBarComponent navigationBarComponent;

    public SignupLoginPage(DriverSet driver) {
        this.driver = driver;
        navigationBarComponent = new navigationBarComponent(driver);
    }

    //locators
    private final By loginEmail = By.cssSelector("[data-qa=\"login-email\"]");
    private final By loginPassword = By.cssSelector("[data-qa=\"login-password\"]");
    private final By loginButton = By.cssSelector("[data-qa=\"login-button\"]");
    private final By signupName = By.cssSelector("[data-qa=\"signup-name\"]");
    private final By signupEmail = By.cssSelector("[data-qa=\"signup-email\"]");
    private final By signupButton = By.cssSelector("[data-qa=\"signup-button\"]");
    private final By signupLabel = By.cssSelector(".signup-form > h2");
    private final By loginError = By.cssSelector(".login-form  p");
    private final By registerError = By.cssSelector(".signup-form p");

    //actions
    @Step("Navigate to Register/Login Page")
    public SignupLoginPage navigate() {
        driver.browser().navigateTo(propertyReader.getProperty("baseUrlWeb")+signupLoginEndpoint);
        return this;
    }
    @Step("Enter name {email} in login field")
    public  SignupLoginPage enterLoginEmail(String email) {
        driver.element().type(loginEmail, email);
        return this;
    }
    @Step("Enter password {password} in login field")
    public SignupLoginPage enterLoginPassword(String password) {
        driver.element().type(loginPassword, password);
        return this;
    }
    @Step("Click login button")
    public SignupLoginPage clickLoginButton() {
        driver.element().click(loginButton);
        return this;
    }

    @Step("Enter name {name} in signup field")
    public SignupLoginPage enterSignupName(String name) {
        driver.element().type(signupName, name);
        return this;
    }
    @Step("Enter email {email} in signup field")
    public SignupLoginPage enterSignupEmail(String email) {
        driver.element().type(signupEmail, email);
        return this;
    }
    @Step("Click signup button")
    public SignupLoginPage clickSignupButton() {
        driver.element().click(signupButton);
        return new SignupLoginPage(driver);
    }


    //validations
    @Step("Verify new user signup visible")
    public SignupLoginPage verifyNewUserSignupVisible() {
        driver.verification().isElementVisible(signupLabel);
        return this;
    }
    @Step("Verify login error message {errorExpected}")
    public SignupLoginPage verifyLoginErrorMsg (String errorExpected)
    {
        String errorActual = driver.element().getText(loginError);
        driver.verification().Equals(errorActual,errorExpected, "Login error message is not as expected");
        return this;
    }
    @Step("Verify register error message {errorExpected}")
    public SignupLoginPage verifyRegisterErrorMsg (String errorExpected) {
        String errorActual = driver.element().getText(registerError);
        driver.verification().Equals(errorActual, errorExpected, "Register error message is not as expected");
        return this;
    }
}
