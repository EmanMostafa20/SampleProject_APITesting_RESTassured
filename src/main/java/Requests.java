import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Requests {

    //attributes
   private String baseURI;
   private String path;
   private Response AllUsers_response;
   private Response UserID_response;
   static int userIDfromAllUsersRequest;
   JsonPath aLlUsers_jpath,certainUser_jpath;

    //constructor
    Requests(String baseURI,String path)
    {
        this.baseURI=baseURI;
        this.path=path;
    }


    //methods
    public void makeAllUsersRequest()
    {
        AllUsers_response= given().baseUri(baseURI).
                and().
                when().
                get(path).
                then().extract().response();
    }


    public void setUserIDfromAllUsersRequest(int index)
    {
        aLlUsers_jpath =AllUsers_response.jsonPath();
      userIDfromAllUsersRequest=aLlUsers_jpath.getInt("data["+index+"].id");
    }


    public void makeCertainUserRequest()
    {
        UserID_response= given().baseUri(baseURI).pathParam("userID",userIDfromAllUsersRequest).
                and().
                when().
                get(path+"{userID}").
                then().
                extract().response();
        certainUser_jpath= UserID_response.jsonPath();
    }


}
