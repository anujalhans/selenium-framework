package com.automation.utils;

import com.automation.base.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public final class ActionUtils {

    private ActionUtils() {}

    private static Actions actions() {
        WebDriver driver = DriverFactory.getDriver();
        return new Actions(driver);
    }

    public static void moveTo(WebElement element) {
        actions().moveToElement(element).perform();
    }

    public static void click(WebElement element) {
        actions().moveToElement(element).click().perform();
    }
}



