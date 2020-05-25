import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class MoreValidationsClass {

    @DataProvider(name="Data")
    public static  Object[][] Data()
    {
        return  new Object[][]{
                {1,"George","Bluth"},
                {2,"Janet","Weaver"},
                {3,"Emma","Wong"}
        };
    }

    @Test
    public void testPostUser()
    {
        given().
                baseUri("https://reqres.in/").
                when().
                body("{\n\"name\":\"Eman\" \n \"job\":\"Tester\"}")
                .post("api/users")
                .then()
                .assertThat().statusCode(201);
    }
    @Test
    public void testWithParams()
    {
        given().
                baseUri("https://reqres.in/").
                and().pathParam("userID","2").
                when().
                get("api/users/{userID}").
                then().
                assertThat().statusCode(200);
    }

    @Test(dataProvider = "Data")
    public void testWithParamsFromDataprovider(int userID, String fName,String lName)
    {
      given().
              baseUri("https://reqres.in/").
              and().pathParam("userID",userID).
              when().
              get("api/users/{userID}").
              then().
              assertThat().
              body("data.id",equalTo(userID)).and().
              body("data.first_name",equalTo(fName)).and().
              body("data.last_name",equalTo(lName));
    }

    @Test
    public void testWithQparamsConcat()
    {

    }
}
