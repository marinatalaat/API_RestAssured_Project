package Helpers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ReportGeneration {

    private static String reportName;

    public static ExtentReports reportSetup(String FileName){
        ExtentReports extentReport = new ExtentReports();
        ExtentHtmlReporter report = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/" + FileName + ".html");
        report.config().setReportName("Test Automation detailed report");
        report.config().setTheme(Theme.DARK);
        report.config().enableTimeline(false);
        extentReport.attachReporter(report);
        reportName = FileName;
       return extentReport;
    }

    public static void openReportINDefaultBrowser() throws IOException {
        System.out.println(reportName  + "  report name ");
        File file = new File(System.getProperty("user.dir")+"/Reports/"+reportName+".html");
        Desktop.getDesktop().browse(file.toURI());
    }
}
