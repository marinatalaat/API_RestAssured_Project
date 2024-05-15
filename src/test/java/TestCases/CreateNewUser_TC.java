package TestCases;

import Requests.CreateNewUserRequests;
import ResponseModels.CreateOrUpdateUserResModel;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import static Helpers.JsonUtils.*;
import static Helpers.PropertiesLoader.readPropertyFile;
import static Helpers.ReportGeneration.*;

public class CreateNewUser_TC {

    static final Properties urlProps = readPropertyFile("ConfigData/CommonData.properties");
    String JsonSchema = "Schemas/CreateNewUserSchema.json";
    String filePath = "RequestBody/CreateNewUserReqBody.json";
    CreateOrUpdateUserResModel createOrUpdateUserResModel;
    ExtentReports extentReports;
    ExtentTest test;


    @Test
    public void CreateNewUser() throws IOException {
        ValidateClassToResponseSchema(createOrUpdateUserResModel, JsonSchema);

        Assert.assertNotNull(createOrUpdateUserResModel.id);
        Assert.assertNotNull(createOrUpdateUserResModel.createdAt);
        Assert.assertEquals(createOrUpdateUserResModel.job, getStringValueFromJsonFile("job", filePath));
        Assert.assertEquals(createOrUpdateUserResModel.name, getStringValueFromJsonFile("name", filePath));
    }



    @BeforeClass
    public void beforeTest() throws IOException {
        extentReports = reportSetup(this.getClass().getName());
        createOrUpdateUserResModel = CreateNewUserRequests.createNewUserRequest(urlProps.getProperty("PostStatusCode"));
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
