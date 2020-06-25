package com.webservicetest.base;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


import java.io.IOException;
import java.util.Properties;

public class TestBase {

    protected RequestSpecification REQUEST;

    public TestBase(){
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("test1.properties"));
            RestAssured.baseURI = props.getProperty("api.uri");
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        REQUEST = RestAssured.given().contentType(ContentType.JSON);
    }

}
