package com.automationExercises.apis;

import com.automationExercises.utils.dataReader.propertyReader;
import com.automationExercises.utils.logs.logsManager;
import com.automationExercises.validations.verification;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class userManagementAPI {
RequestSpecification requestSpecification;
Response response;
verification verification;

//Constructor
public userManagementAPI() {
    requestSpecification = RestAssured.given();
    verification = new verification();

}

//endpoints
    private static final String createUserEndpoint = "/createAccount";
    private static final String deleteUserEndpoint = "/deleteAccount";

//API Methods
@Step("Create a new user account with full details")
public userManagementAPI createRegisterUserAccount(String name, String email, String pass,String title,String birth_date, String birth_month,String birth_year, String firstName, String lastName
        , String company,String address1, String address2,String country,String zipcode,String state,String city,String mobile_number)
{
    Map<String, String> formParams = new HashMap<>();
    formParams.put("name", name);
    formParams.put("email", email);
    formParams.put("password", pass);
    formParams.put("title", title);
    formParams.put("birth_date", birth_date);
    formParams.put("birth_month", birth_month);
    formParams.put("birth_year", birth_year);
    formParams.put("firstname", firstName);
    formParams.put("lastname", lastName);
    formParams.put("company", company);
    formParams.put("address1", address1);
    formParams.put("address2", address2);
    formParams.put("country", country);
    formParams.put("zipcode", zipcode);
    formParams.put("state", state);
    formParams.put("city", city);
    formParams.put("mobile_number", mobile_number);
    response =  requestSpecification.spec(Builder.getUserManagementAPI(formParams))
            .post(createUserEndpoint);
    logsManager.info(response.asPrettyString());
    return this;
}

    @Step("Create a new user account with minimal details")
    public userManagementAPI createRegisterUserAccount(String name, String email, String pass, String firstName, String lastName)
    {
        Map <String, String> formParams = new HashMap<>();
        formParams.put("name", name);
        formParams.put("email", email);
        formParams.put("password", pass);
        formParams.put("title", "Mr");
        formParams.put("birth_date", "1");
        formParams.put("birth_month", "January");
        formParams.put("birth_year", "1990");
        formParams.put("firstname", firstName);
        formParams.put("lastname", lastName);
        formParams.put("company", "company");
        formParams.put("address1", "address1");
        formParams.put("address2", "address2");
        formParams.put("country", "India");
        formParams.put("zipcode", "123456");
        formParams.put("state", "state");
        formParams.put("city", "city");
        formParams.put("mobile_number", "1234567890");
        response =  requestSpecification.spec(Builder.getUserManagementAPI(formParams))
                .post(createUserEndpoint);
        logsManager.info(response.asPrettyString());
        return this;
    }

    @Step("Delete user account by email")
    public userManagementAPI deleteUserAccount(String email,String password) {
        Map<String, String> formParams = new HashMap<>();
        formParams.put("email", email);
        formParams.put("password", password);
        response= requestSpecification.spec(Builder.getUserManagementAPI(formParams))
                .delete(deleteUserEndpoint);
        logsManager.info(response.asPrettyString());
        return this;
    }
    //validations
    @Step("Verify that user is created successfully")
    public userManagementAPI verifyUserCreatedSuccessfully() {
        verification.Equals(response.jsonPath().get("message"), "User created!",
                "User is not created successfully");
        return this;

    }
    @Step("Verify that user is deleted successfully")
    public userManagementAPI verifyUserDeletedSuccessfully() {
        verification.Equals(response.jsonPath().get("message"), "Account deleted!",
                "User is not deleted successfully");
        return this;
    }
}
