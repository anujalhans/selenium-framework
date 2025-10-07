package com.automation.utils;

import com.automation.base.DriverFactory;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public final class ScreenshotUtils {

    private ScreenshotUtils() {}

    public static String takeBase64Screenshot() {
        TakesScreenshot ts = (TakesScreenshot) DriverFactory.getDriver();
        return Base64.encodeBase64String(ts.getScreenshotAs(OutputType.BYTES));
    }
}



