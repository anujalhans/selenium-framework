package com.automation.listeners;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.automation.base.BaseTest;
import com.automation.utils.ScreenshotUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends BaseTest implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        extentTest.get().log(Status.INFO, "Starting test: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String base64 = ScreenshotUtils.takeBase64Screenshot();
        extentTest.get().fail(result.getThrowable(),
                MediaEntityBuilder.createScreenCaptureFromBase64String(base64, "Failure Screenshot").build());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().skip("Test skipped");
    }

    @Override
    public void onStart(ITestContext context) { }

    @Override
    public void onFinish(ITestContext context) { }
}



