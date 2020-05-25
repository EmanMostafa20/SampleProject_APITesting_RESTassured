import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JSONServerTests {

    //attributes
    String baseURI="http://localhost:3000";


    @Test
    public void testJSONserver()
    {
        Response extractedItem= given().baseUri(baseURI).
                and().
                when().
                get("/locations").
                then().extract().response();
        // System.out.println(extractedItem.body().asString());
        JsonPath jpath =extractedItem.jsonPath();
        System.out.println((String) jpath.get("address_components[0].long_name[0]"));//locations.address_components[0].long_name[0]
    }


    @Test
    public void testJsonSchema()
    {
      given().when().get(baseURI+"/ice_creams").then().body(matchesJsonSchemaInClasspath("schema_example.json"));
    }
}
