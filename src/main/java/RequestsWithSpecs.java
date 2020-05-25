import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RequestsWithSpecs {
    //attributes
    RequestSpecification requestSpecs;
    Response response;
    Payload body;

    //constructor
    RequestsWithSpecs()
    {
        requestSpecs=given().baseUri("https://reqres.in/");
    }

    public Response getUserListRequest()
    {
        response=requestSpecs.params("page","1","per_page","6").request(Method.GET,"https://reqres.in/");
        return response;
    }

    public Response getUserRequest(int id)
    {
        response=requestSpecs.pathParam("userID",id).params("page","1","per_page","6").request(Method.GET,"https://reqres.in/{UserID}");
        return response;
    }

    public Response postUserRequest(int id)
    {
        response=given().pathParam("userID",id).params("page","1","per_page","6").body(body.setPayload()).when().post("https://reqres.in/{UserID}");
        return response;
    }
}
