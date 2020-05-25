import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.*;

import static io.restassured.RestAssured.given;

public class APIChainingWithRequestSpecsInside {
    //attributes
    RequestSpecification requestSpecs;

    //set ups
    @BeforeClass
    public void init()
    {
        requestSpecs= new RequestSpecBuilder().setBaseUri("https://reqres.in/").setContentType(ContentType.JSON).build();

    }

    //tests
    @Test
    public void testTrial()
    {
        given().spec(requestSpecs).
                when().
                get("api/users/2").then().
                assertThat().statusCode(200);
    }
}
