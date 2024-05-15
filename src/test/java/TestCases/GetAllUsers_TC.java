package TestCases;

import Requests.GetTheUserDataRequests;
import ResponseModels.UsersApiResponseModel;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import static Helpers.JsonUtils.ValidateClassToResponseSchema;
import static Helpers.PropertiesLoader.readPropertyFile;
import static Helpers.ReportGeneration.*;

public class GetAllUsers_TC {

    static final Properties urlProps = readPropertyFile("ConfigData/CommonData.properties");
    UsersApiResponseModel usersApiResponseModel;
    String JsonSchema = "Schemas/UsersResponseSchema.json";
    ExtentReports extentReports;
    ExtentTest test;


    @DataProvider(name = "user info to validate on")
    public Object[][] userInfo(){
        return new Object[][]{
                {3,"emma.wong@reqres.in","Emma","Wong","https://reqres.in/img/faces/3-image.jpg"}
        };
    }


    @Test(dataProvider = "user info to validate on")
    public void GetUsersInFirstPage(int id, String email, String firstName, String lastName, String avatar) throws JsonProcessingException {
        usersApiResponseModel = GetTheUserDataRequests.getAllUsersDataInFirstPage(urlProps.getProperty("GetStatusCode"));
        ValidateClassToResponseSchema(usersApiResponseModel, JsonSchema);

        Assert.assertEquals(usersApiResponseModel.data.get(id-1).id, id);
        Assert.assertEquals(usersApiResponseModel.data.get(id-1).email, email);
        Assert.assertEquals(usersApiResponseModel.data.get(id-1).first_name, firstName);
        Assert.assertEquals(usersApiResponseModel.data.get(id-1).last_name, lastName);
        Assert.assertEquals(usersApiResponseModel.data.get(id-1).avatar, avatar);
    }

    @DataProvider(name = "user info to validate on in Second page")
    public Object[][] userInfoSecondPage(){
        return new Object[][]{
                {12,"rachel.howell@reqres.in","Rachel","Howell","https://reqres.in/img/faces/12-image.jpg"}
        };
    }

    @Test(dataProvider = "user info to validate on in Second page")
    public void GetUsersInSecondPage(int id, String email, String firstName, String lastName, String avatar) throws JsonProcessingException {
        usersApiResponseModel = GetTheUserDataRequests.getAllUsersDataInSecondPage(urlProps.getProperty("GetStatusCode"));
        ValidateClassToResponseSchema(usersApiResponseModel, JsonSchema);

        Assert.assertEquals(usersApiResponseModel.data.get((id/2)-1).id, id);
        Assert.assertEquals(usersApiResponseModel.data.get((id/2)-1).email, email);
        Assert.assertEquals(usersApiResponseModel.data.get((id/2)-1).first_name, firstName);
        Assert.assertEquals(usersApiResponseModel.data.get((id/2)-1).last_name, lastName);
        Assert.assertEquals(usersApiResponseModel.data.get((id/2)-1).avatar, avatar);
    }






    @BeforeClass
    public void beforeTest(){
        extentReports = reportSetup(this.getClass().getName());
    }

    @BeforeMethod
    public void beforeMethod(Method method){
        test = extentReports.createTest(method.getName());
    }

    @AfterMethod
    public void afterMethod(ITestResult result) throws IOException {
        setTestStatus(result, test);
        extentReports.flush();
    }
    @AfterClass
    public void AfterTest() throws IOException {
        openReportINDefaultBrowser();
    }
}
