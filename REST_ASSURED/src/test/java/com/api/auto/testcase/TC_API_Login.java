package com.api.auto.testcase;

import DriverIntance.DriverInstance;
import com.api.auto.utils.PropertiesFileUtils;
import io.restassured.RestAssured;
import static org.testng.Assert.*;
import io.restassured.http.ContentType;
import org.testng.annotations.*;
public class TC_API_Login extends DriverInstance{
    @BeforeClass
    public void setUp(){
        bodyRequest.put("account", PropertiesFileUtils.getProperty("account"));
        bodyRequest.put("password", PropertiesFileUtils.getProperty("password"));

        requestSpecification = RestAssured.given()
                .baseUri(BASE_URL)
                .basePath(LOGIN_PATH)
                .contentType(ContentType.JSON)
                .body(bodyRequest);

        response = requestSpecification.post();
    }

    @Test(priority = 0)
    public void PostSuccess(){
        assertEquals(response.getStatusCode(), 201);

        System.out.println("Post successfully");
    }
    @Test(priority = 1)
    public void SuccessMessage(){
        assertEquals((String) response.jsonPath().get("message"), "Đăng nhập thành công");

        System.out.println("Have success message");
    }
    @Test(priority = 2)
    public void Token(){
        assertNotNull(response.jsonPath().get("token"));
        PropertiesFileUtils.saveToken((String) response.jsonPath().get("token"));

        System.out.println("Have token and saved");
    }
    @Test(priority = 3)
    public void CheckAccount(){
        assertEquals((String) response.jsonPath().get("user.account"),
                PropertiesFileUtils.getProperty("account"));

        System.out.println("Correct account");
    }
    @Test(priority = 4)
    public void CheckPassword(){
        assertEquals((String) response.jsonPath().get("user.password"),
                PropertiesFileUtils.getProperty("password"));

        System.out.println("Correct password");
    }
    @Test(priority = 5)
    public void CheckType(){
        assertEquals((String) response.jsonPath().get("user.type"),
                "UNGVIEN");

        System.out.println("Correct type");
    }
    @AfterClass
    public void endTest(){
        bodyRequest.clear();
        System.out.println("Login successfully!!! \n");
    }
}
