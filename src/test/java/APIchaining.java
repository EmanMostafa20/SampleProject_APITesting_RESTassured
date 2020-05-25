import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class APIchaining
{
    //attributes
    String baseURI="https://reqres.in/";
    String path="api/users/";
    SoftAssert softAssert= new SoftAssert() ;
    int jsonIndex =0;

    //tests

    /*-----------------test list all users request--------------------*/
    @Test

    public void test_listAllUsers()
    {
        Requests allUsersRequest= new Requests(baseURI,path);
         allUsersRequest.makeAllUsersRequest();
         allUsersRequest.setUserIDfromAllUsersRequest(jsonIndex);

        softAssert.assertEquals(
                allUsersRequest.aLlUsers_jpath.getInt("data["+ jsonIndex +"].id"),
                Requests.userIDfromAllUsersRequest,
                "Wrong ID!");
        softAssert.assertEquals(
                allUsersRequest.aLlUsers_jpath.getString("data["+ jsonIndex +"].first_name"),
                "George",
                "Wrong name!");//Janet
        softAssert.assertAll();
         System.out.println("ID from all users:"+ Requests.userIDfromAllUsersRequest);
        System.out.println("name from all users is :"+  allUsersRequest.aLlUsers_jpath.getString("data["+jsonIndex+"].first_name"));
    }

    /*-----------------test get a certain user request--------------------*/
    @Test(dependsOnMethods={"test_listAllUsers"} )
    public void test_certainUser()
    {
        Requests certainUserRequest= new Requests(baseURI,path);

        certainUserRequest.makeCertainUserRequest();

        softAssert.assertEquals(
                certainUserRequest.certainUser_jpath.getInt("data.id"),
                Requests.userIDfromAllUsersRequest,
                "Wrong ID!");
        softAssert.assertEquals(
                certainUserRequest.certainUser_jpath.getString("data.first_name"),
                "George",
                "Wrong name!");
        softAssert.assertAll();
        System.out.println("ID from certain user:"+ Requests.userIDfromAllUsersRequest);
        System.out.println("name from certain user is :"+  certainUserRequest.certainUser_jpath.getString("data.first_name"));

    }

}
