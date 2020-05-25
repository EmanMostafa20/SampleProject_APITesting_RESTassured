import org.testng.annotations.*;

import static io.restassured.RestAssured.given;

public class FirstHit {
    @Test
    public void testGetUser2Request()
    {
        given().baseUri("https://reqres.in/").and().pathParam("userID","2").when().get("api/users/{userID}").then().assertThat().statusCode(200).log().all();

    }
    @Test
    public void testGetAllUsersRequest()
    {
        given().baseUri("https://reqres.in/").when().get("api/users").then().assertThat().statusCode(200);
    }
    @Test
    public void testDeleteUserRequest()
    {
        given().
                baseUri("https://reqres.in/").
                and().pathParam("userID","3").
                when().
                delete("api/users/{userID}").then().assertThat().statusCode(204);
    }
    @Test
    public void testInvalidIDUserRequest()
    {
        given().baseUri("https://reqres.in/").and().pathParam("userID","23").when().get("api/users/{userID}").then().assertThat().statusCode(404);
    }
}
