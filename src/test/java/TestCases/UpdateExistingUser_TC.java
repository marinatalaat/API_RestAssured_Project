package TestCases;

import Requests.UpdateUserRequests;
import ResponseModels.CreateOrUpdateUserResModel;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import static Helpers.JsonUtils.ValidateClassToResponseSchema;
import static Helpers.JsonUtils.getStringValueFromJsonFile;
import static Helpers.PropertiesLoader.readPropertyFile;
import static Helpers.ReportGeneration.*;

public class UpdateExistingUser_TC {


    String JsonSchema = "Schemas/UpdateUserSchema.json";
    String filePath = "RequestBody/CreateNewUserReqBody.json";
    CreateOrUpdateUserResModel createOrUpdateUserResModel;
    ExtentReports extentReports;
    ExtentTest test;
    static final Properties urlProps = readPropertyFile("ConfigData/CommonData.properties");


    @Test
    public void UpdateUser() throws IOException {
        ValidateClassToResponseSchema(createOrUpdateUserResModel, JsonSchema);

        Assert.assertNotNull(createOrUpdateUserResModel.updatedAt);
        Assert.assertEquals(createOrUpdateUserResModel.job, getStringValueFromJsonFile("job", filePath));
        Assert.assertEquals(createOrUpdateUserResModel.name, getStringValueFromJsonFile("name", filePath));
    }



    @BeforeClass
    public void beforeTest() throws IOException {
        extentReports = reportSetup(this.getClass().getName());
        createOrUpdateUserResModel = UpdateUserRequests.UpdateUserRequest(urlProps.getProperty("userID"), urlProps.getProperty("UpdateStatusCode"));}

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
