import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TaskOfDayTwoRESTassured {

    //attributes
    String baseURI="https://reqres.in/";
    String path="api/users/";


    //data providers
    @DataProvider(name="DataForPutUsers")
    public static Object[][] DataForPutUsers()
    {
        return new Object[][]{
                {2, "Eman", "Tester"},
                {3, "Esraa", "Developer"},
                {4, "Ahmed", "Manager"}
        };

    }

    @DataProvider(name="DataForListUsers")
    public static Object[][] DataForListUsers()
    {
        return  new Object[][]{
                {1,6},
                {2,6},
                {3,6}
        };



    }


    //tests

    /*-----------------------Testing PUT method using data provider (DataForPutUsers)-----------------------*/
    @Test(dataProvider = "DataForPutUsers")
    public  void testPutUsersWithDataprovider(int userID,String firstName,String job)
    {
     given().
             baseUri(baseURI).
             and().pathParam("userID",userID).
             and().body("{name:"+firstName+","+"job:"+job+"}").
             when().
             put(path+"{userID}").
             then().assertThat().statusCode(200);
    }

    /*-----------------------Testing request to list all users using data provider (DataForListUsers)-------------------*/
    @Test(dataProvider = "DataForListUsers")
    public void testListUsersRequestWithDataprovider(int pageNum,int per_pageNum)
    {
        given().
                baseUri(baseURI).
                and().queryParams("page",pageNum,"per_page",per_pageNum).
                when().
                get(path).
                then().
                assertThat().body("page",equalTo(pageNum)).body("per_page",equalTo(per_pageNum)).log().all();

    }

    /*-------------Testing request to list all users with query parmas hard coded in URL (without using data provider)-----------*/
    @Test
    public void testListUsersRequestHardCoded()
    {
        given().
                baseUri(baseURI).
                and().
                when().
                get(path+"?page=1&per_page=6").
                then().
                assertThat().body("page",equalTo(1)).body("per_page",equalTo(6)).log().all();

    }

    //Comments
        /*
        In my point of view, the main difference between using query parameters with data provider
        and putting query parameters hard coded in the URL is the parallelism that data provider gives us,
        on the other, to assert on many query parameters  hard coded it will be done with repeating the same test
        with different data.|LJY4WQ
        */
}
