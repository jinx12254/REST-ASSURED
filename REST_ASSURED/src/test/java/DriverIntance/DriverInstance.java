package DriverIntance;

import com.api.auto.utils.PropertiesFileUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
public class DriverInstance {
    protected String BASE_URL = PropertiesFileUtils.getProperty("BASE_URL");
    protected String LOGIN_PATH = PropertiesFileUtils.getProperty("LOGIN_PATH");
    protected String CREATE_PATH = PropertiesFileUtils.getProperty("CREATE_WORK");
    protected Map<String, Object> bodyRequest = new HashMap<String, Object>();
    protected RequestSpecification requestSpecification;
    protected Response response;
}
