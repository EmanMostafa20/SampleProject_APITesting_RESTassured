import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class DayFour {

    //attributes
    String baseURI="https://reqres.in/";
    String path="api/users/";
    SoftAssert softAssert=new SoftAssert();

    @Test
    public void testJSONresponse()
    {
        Response extractedItem= given().baseUri(baseURI).
                and().
                when().
                get(path).
                then().extract().response();

        JsonPath jpath =extractedItem.jsonPath();
        System.out.println((String) jpath.getString("data[0].id"));
        softAssert.assertEquals(jpath.getInt("data[0].id"),1,"ID is not correct!");
        softAssert.assertEquals(jpath.getString("data[0].first_name"),"George","name is not correct!");

    }


    @Test
    public void testChainingAPI()
    {

        //list all users
        Response AllUsers_response= given().baseUri(baseURI).
                and().
                when().
                get(path).
                then().extract().response();

        JsonPath ALlUsers_jpath =AllUsers_response.jsonPath();

        System.out.println("Hey from the first response!");
        System.out.println("ID here is :"+(String) ALlUsers_jpath.getString("data[0].id"));
        System.out.println("First name here is :"+(String) ALlUsers_jpath.getString("data[0].first_name"));
        softAssert.assertEquals(ALlUsers_jpath.getInt("data[0].id"),2,"ID is not correct!");
        softAssert.assertEquals(ALlUsers_jpath.getString("data[0].first_name"),"George","name is not correct!");

       int idFromAll= ALlUsers_jpath.getInt("data[0].id");
        System.out.println("ID here is :"+idFromAll);

        //certain user
        Response UserID_response= given().baseUri(baseURI).pathParam("userID",idFromAll).
                and().
                when().
                        get(path+"{userID}").
                        then().
                        extract().response();
         JsonPath user_jpath = UserID_response.jsonPath();
        softAssert.assertEquals(user_jpath.getInt("data.id"),1,"ID is not correct!");
        System.out.println("Hey from the second response!");
        System.out.println("ID here is :"+(String) user_jpath.getString("data.id"));
        System.out.println("First name here is :"+(String) user_jpath.getString("data.first_name"));
        softAssert.assertAll();

    }


    @Test
    public void testExtractFromResponse()
    {
       // Map<String,String> extractedItem=
       /* String extractedItem=
            given().baseUri(baseURI).
                 //   and(). body("{\n\"name\":\"Eman\" \n \"job\":\"Tester\"}").
                    when().
                    get(path).
                    then().extract().response().asString();
        System.out.println(extractedItem);*/
       // System.out.println(extractedItem.header("Content-Type"));
        Response extractedItem= given().baseUri(baseURI).
                          and(). body("{\n\"name\":\"Eman\" \n \"job\":\"Tester\"}").
                          when().
                        post(path).
                        then().extract().response();
       // System.out.println(extractedItem.body().asString());
        JsonPath jpath =extractedItem.jsonPath();
        System.out.println(jpath.get("id").toString());

    }

}
