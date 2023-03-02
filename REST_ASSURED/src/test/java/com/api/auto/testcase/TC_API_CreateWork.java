package com.api.auto.testcase;

import DriverIntance.DriverInstance;
import com.api.auto.utils.PropertiesFileUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.*;
import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
public class TC_API_CreateWork extends DriverInstance {
    @BeforeClass
    public void setUp(){
        bodyRequest.put("nameWork",PropertiesFileUtils.getProperty("nameWork"));
        bodyRequest.put("experience",PropertiesFileUtils.getProperty("experience"));
        bodyRequest.put("education",PropertiesFileUtils.getProperty("education"));

        requestSpecification = RestAssured.given()
                .baseUri(BASE_URL)
                .basePath(CREATE_PATH)
                .header("token", PropertiesFileUtils.getToken())
                .contentType(ContentType.JSON)
                .body(bodyRequest);

        response = requestSpecification.post();
    }
    @Test(priority = 6)
    public void PostSuccess(){
        assertEquals(response.getStatusCode(), 201);

        System.out.println("Created successfully");
    }
    @Test(priority = 7)
    public void CheckIdResponse(){
        assertNotNull(response.jsonPath().get("id"));

        System.out.println("Have id");
    }
    @Test(priority = 8)
    public void CheckWorkName(){
        assertEquals((String) response.jsonPath().get("nameWork"),
                PropertiesFileUtils.getProperty("nameWork"));

        System.out.println("Work's name correct");
    }
    @Test(priority = 9)
    public void CheckExperience(){
        assertEquals((String) response.jsonPath().get("experience"),
                PropertiesFileUtils.getProperty("experience"));

        System.out.println("Experience correct");
    }
    @Test(priority = 10)
    public void CheckEducation(){
        assertEquals((String) response.jsonPath().get("education"),
                PropertiesFileUtils.getProperty("education"));

        System.out.println("Education correct");
    }
    @AfterClass
    public void endTest(){
        baseURI = null;
        basePath = null;
        bodyRequest.clear();

        System.out.print("Done test!!!");
    }
}
