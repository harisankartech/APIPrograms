package Assertions;

import io.restassured.response.Response;
import org.testng.Assert;

public class AssertActions {

    public void VerifyResponseBody(String actual,String expected){
        Assert.assertEquals(actual,expected);
    }

    public void VerifyResponseBody(int actual,int expected){
        Assert.assertEquals(actual,expected);
    }

    public void VerifyStatusCode(@org.jetbrains.annotations.NotNull Response response, Integer expected){
        Assert.assertEquals(response.getStatusCode(),expected);
    }
}
