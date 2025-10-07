package com.automation.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public abstract class BaseTest {

    protected static ExtentReports extentReports;
    protected static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        ExtentSparkReporter spark = new ExtentSparkReporter("reports/extent-report.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(spark);
        ConfigReader.init();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method) {
        DriverFactory.initDriver();
        extentTest.set(extentReports.createTest(method.getName()));
        String url = ConfigReader.get("baseUrl");
        getDriver().get(url);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // Screenshot is attached by listener; leave here in case future custom actions needed
        }
        DriverFactory.quitDriver();
    }

    protected WebDriver getDriver() {
        return DriverFactory.getDriver();
    }
}



