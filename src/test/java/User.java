import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class User {
    Properties props;
@BeforeTest
    public void setUp() throws IOException {
        props=new Properties();
        FileInputStream fs=new FileInputStream("./src/test/resources/config.properties");
        props.load(fs);

    }


    @Test(priority = 1)
    public void login() throws ConfigurationException {


        RestAssured.baseURI=props.getProperty("baseUrl");



//        RestAssured.baseURI="http://dmoney.roadtocareer.net";
        Response res=given().contentType("application/json")
                .body("{\n" +
                        "    \"emailOrPhoneNumber\":\"01686606909\",\n" +
                        "    \"password\":\"1234\"\n" +
                        "}")
                .when()
                .post("/user/login");

//        System.out.println(res.asString());

        JsonPath jsonPath=res.jsonPath();
        String token=jsonPath.get("token").toString();
        Utils.setEnvironment("token",token);

    }
    @Test(priority = 2)
    public void createUser()  {


        RestAssured.baseURI=props.getProperty("baseUrl");



//        RestAssured.baseURI="http://dmoney.roadtocareer.net";
        Response res=given().contentType("application/json")
                .header("Authorization",props.get("token"))
                .header("X-AUTH-SECRET-KEY","ROADTOSDET")
                .body("{\n" +
                        "    \"name\":\"Test Customer 3\",\n" +
                        "    \"email\":\"xyz1@gmail.com\",\n" +
                        "    \"password\":\"12324\",\n" +
                        "    \"phone_number\":\"01846744446\",\n" +
                        "    \"nid\":\"123434789\",\n" +
                        "    \"role\":\"Customer\"\n" +
                        "}")
                .when()
                .post("/user/create");

        System.out.println(res.asString());





    }
}
