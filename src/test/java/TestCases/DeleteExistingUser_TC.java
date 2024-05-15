package TestCases;

import Requests.DeleteUserRequests;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import static Helpers.PropertiesLoader.readPropertyFile;
import static Helpers.ReportGeneration.*;

public class DeleteExistingUser_TC {


    ExtentReports extentReports;
    ExtentTest test;
    private static final Properties urlProps = readPropertyFile("ConfigData/CommonData.properties");


    @Test
    public void DeleteUser() throws IOException {
        Assert.assertEquals(String.valueOf(DeleteUserRequests.deleteUserRequest(urlProps.getProperty("userID"))), urlProps.getProperty("DeleteStatusCode"));
    }



    @BeforeClass
    public void beforeTest() throws IOException {
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
