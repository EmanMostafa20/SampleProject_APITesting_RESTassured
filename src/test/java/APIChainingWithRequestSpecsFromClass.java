import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class APIChainingWithRequestSpecsFromClass {
    //attributes
    Response userListResponse, userPostResponse;
    RequestsWithSpecs requestSpecBuilder;
    JsonPath allUsersJpath;
    SoftAssert softAssert;

    //set ups
    @BeforeClass
    public void init()
    {
        requestSpecBuilder =new RequestsWithSpecs();
        userListResponse= requestSpecBuilder.getUserListRequest();
        userPostResponse= requestSpecBuilder.postUserRequest(2);


    }

    //tests
    @Test
    public void testGetAllusers()
    {
        allUsersJpath=userListResponse.jsonPath();

        softAssert.assertEquals(allUsersJpath.getInt("data[0].id"),1,"ID is not correct!");
        softAssert.assertEquals(allUsersJpath.getString("data[0].first_name"),"George","name is not correct!");
        softAssert.assertAll();
    }

    @Test
    public  void testAllusersStatusCode()
    {
        userListResponse.then().assertThat().statusCode(200);
    }
    //and so on with all assertions on response

    @Test
    public void testPostUser()
    {
     userPostResponse.then().assertThat().statusCode(201);
    }
}
