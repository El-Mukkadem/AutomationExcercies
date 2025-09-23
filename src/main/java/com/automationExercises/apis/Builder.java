package com.automationExercises.apis;

import com.automationExercises.utils.dataReader.propertyReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class Builder {
    private static String baseURI = propertyReader.getProperty("baseUrlApi");
private Builder(){}
    public static RequestSpecification getUserManagementAPI(Map<String, ?> formParams) {

        return new RequestSpecBuilder()
                .setBaseUri(baseURI)
                .setContentType(ContentType.URLENC)
                .addFormParams(formParams)
                .build();
    }
}
