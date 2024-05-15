package Helpers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ReportGeneration {

    private static String testName;

    public static ExtentReports reportSetup(String testCaseName){
        ExtentReports extentReport = new ExtentReports();
        ExtentHtmlReporter report = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/" + testCaseName + ".html");
        report.config().setReportName("Test Automation detailed report");
        report.config().setTheme(Theme.DARK);
        report.config().enableTimeline(true);
        report.config().setCSS(".r-img {width: 60%;}");
        extentReport.attachReporter(report);
        testName = testCaseName;
       return extentReport;
    }

    public static void openReportINDefaultBrowser() throws IOException {
        File file = new File(System.getProperty("user.dir")+"/Reports/"+testName+".html");
        Desktop.getDesktop().browse(file.toURI());
    }

    public static ExtentTest setTestStatus(ITestResult result, ExtentTest test) throws IOException {
        if(result.getStatus() == ITestResult.FAILURE){
            test.log(Status.FAIL, "Test is Failed and the reason: " + result.getThrowable().getMessage());
        }
        if(result.getStatus() == ITestResult.SUCCESS){
            test.log(Status.PASS, "Test is Passed");
        }
        if(result.getStatus() == ITestResult.SKIP){
            test.log(Status.SKIP, " Test is Skipped");
        }
        return test;
    }
}
