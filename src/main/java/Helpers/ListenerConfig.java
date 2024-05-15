package Helpers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static Helpers.ReportGeneration.*;

public class ListenerConfig implements ITestListener {
    ExtentReports extentReport;
    ExtentTest extentTest;


    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extentReport.createTest(result.getMethod().getConstructorOrMethod().getName() );
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS, "Test is Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.log(Status.FAIL, "Test is Failed and the reason: " + result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.log(Status.SKIP, " Test is Skipped");
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        extentTest.log(Status.FAIL, "Test is Failed With Timeout and the thrown error is: " + result.getThrowable().getMessage());
    }


    @Override
    public void onStart(ITestContext context) {
        extentReport = reportSetup(context.getCurrentXmlTest().getParameter("ReportName"));
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReport.setSystemInfo("env", "prod");
        extentReport.flush();
        try {
            openReportINDefaultBrowser();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
